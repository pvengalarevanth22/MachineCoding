package com.movies.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseClass {
    @ManyToOne
    private Show show;

    @ManyToOne
    private SeatType seatType;
    private int price;
}
