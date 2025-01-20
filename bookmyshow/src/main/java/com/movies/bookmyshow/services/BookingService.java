package com.movies.bookmyshow.services;

import com.movies.bookmyshow.models.*;
import com.movies.bookmyshow.repositories.BookingRepository;
import com.movies.bookmyshow.repositories.ShowRepository;
import com.movies.bookmyshow.repositories.ShowSeatRepository;
import com.movies.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private UserRepository userRepository;

    private ShowRepository showRepository;

    private ShowSeatRepository showSeatRepository;

    private BookingRepository bookingRepository;

    private PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository
    , BookingRepository bookingRepository,PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) {

        /* ---TAKE LOCK HERE FOR CODING PURPOSE.
            1. Get user details by userId from DB.
            2. Get show details by showId from DB.
                   ---- Start lock here -----
            3. Get list of showSeats details by showIds from DB.
            4. check if seats are available or not.
            5. If it is not available throw error.
            6. If it is available change the status to blocked and update the time stamp.
            7. Update the show seats in DB.
                   ---- End lock here -----
            8. return.
         ---END LOCK HER FOR CODING PURPOSE */

        Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User user=userOptional.get();

        Optional<Show> showOptional=showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("Show not found");
        }
        Show show=showOptional.get();

        List<ShowSeat> showSeats=showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat:showSeats){
            if(!(showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(),
                                    new Date().toInstant()).toMinutes()<15))){
                throw new RuntimeException("Show seat not available");
            }
        }

        List<ShowSeat> savedShowSeats=new ArrayList<>();
        for(ShowSeat showSeat:showSeats){
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            showSeatRepository.save(showSeat);
            savedShowSeats.add(showSeat);
        }

        Booking booking=new Booking();

        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeats(savedShowSeats);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeats,show));
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);

        Booking savedBooking=bookingRepository.save(booking);

        return savedBooking;
    }
}
