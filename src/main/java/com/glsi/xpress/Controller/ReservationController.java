package com.glsi.xpress.Controller;

import com.glsi.xpress.Entity.*;
import com.glsi.xpress.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private CardService cardService;

    //add reservation
    @PostMapping("/books/reservation/{id}")
    public ResponseEntity<Reservations> addReservation(@PathVariable Long id, Authentication authentication) {
        //get card
        Optional<Card> card = cardService.getCardByUser(userService.getUserByEmail(authentication.getName()));
        Optional<Book> book = bookService.getBookById(id);
        book.get().setQuantity(book.get().getQuantity()-1);
        bookService.saveBook(book.get());
        Loan loan = new Loan();
        loan.setBook(book.get());
        loan.setUser(user.get());
        loan.setloanDate(Date.valueOf(LocalDate.now()));
        loan.setReturnDate(Date.valueOf(LocalDate.now().plusDays(7)));
        loan.setReturned(false);
        loanService.newloan(loan);
        return ResponseEntity.ok(reservationService.addReservation(reservation));

}
