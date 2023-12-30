package com.glsi.xpress.Service;

import com.glsi.xpress.Entity.Reservations;

public interface ReservationService {

    //add reservation
    Reservations addReservation(Reservations reservation);

    //available reservation
    Reservations availableReservation(Long bookId, boolean isNotified);

}
