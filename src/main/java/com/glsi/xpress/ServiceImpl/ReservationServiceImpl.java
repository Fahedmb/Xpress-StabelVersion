package com.glsi.xpress.ServiceImpl;

import com.glsi.xpress.Repository.ReservationRepository;
import com.glsi.xpress.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.glsi.xpress.Entity.Reservations;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservations addReservation(Reservations reservation){
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservations availableReservation(Long bookId, boolean isNotified) {
        return reservationRepository.findFirstByBookIdAndIsAvailableFalseAndIsNotified(bookId,isNotified);
    }

}
