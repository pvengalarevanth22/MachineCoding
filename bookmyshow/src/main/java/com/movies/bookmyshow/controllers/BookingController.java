package com.movies.bookmyshow.controllers;

import com.movies.bookmyshow.dtos.BookMovieRequestDto;
import com.movies.bookmyshow.dtos.BookMovieResponseDto;
import com.movies.bookmyshow.dtos.ResponseStatus;
import com.movies.bookmyshow.models.Booking;
import com.movies.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto) {
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        Booking booking;
        try{
            booking=bookingService.bookMovie(bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowId(),
                    bookMovieRequestDto.getShowSeatIds());
            bookMovieResponseDto.setStatus(ResponseStatus.SUCCESS);
            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getAmount());
        }
        catch(Exception e){
            bookMovieResponseDto.setStatus(ResponseStatus.FAILURE);
        }
        return bookMovieResponseDto;
    }
}
