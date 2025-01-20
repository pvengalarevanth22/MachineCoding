package com.movies.bookmyshow.repositories;

import com.movies.bookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
   Booking save(Booking booking);
}
