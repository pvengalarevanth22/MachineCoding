package com.movies.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseClass{
    private String number;
    private int rowVal;
    private int columnVal;

    @ManyToOne
    private SeatType seatType;
}
