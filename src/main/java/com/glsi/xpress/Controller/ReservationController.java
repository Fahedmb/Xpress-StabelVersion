package com.glsi.xpress.Controller;

import com.glsi.xpress.Service.ReservationService;
import com.glsi.xpress.Entity.Reservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    //add reservation


}
