package com.glsi.xpress.Controller;

import com.glsi.xpress.Entity.Book;
import com.glsi.xpress.Entity.Card;
import com.glsi.xpress.Exceptions.LoanCannotBeRenewed;
import com.glsi.xpress.Service.BookService;
import com.glsi.xpress.Service.CardService;
import com.glsi.xpress.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.glsi.xpress.Entity.Loan;
import com.glsi.xpress.Service.LoanService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "http://localhost:3000")
public class LoansController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CardService cardService;

    //create a loan
    @PostMapping
    public Loan createLoans(@RequestBody Loan loan) {
        //get the card number from the request body
        Long cardNumber = loan.getCard().getCardNumber();
        //get teh card from the database
        Card card = cardService.findByCardNumber(cardNumber);
        //set the card of the loan to the card from the database
        loan.setCard(card);
        //get the book id from the request body
        Long bookId = loan.getBook().getId();
        //get the book from the database
        Optional<Book> book = bookService.getBookById(bookId);
        loan.setBook(book.get());
        book.get().setQuantity(book.get().getQuantity()-1);
        bookService.saveBook(book.get());

        //check if the card is expired, if it is, throw an exception
        if (card.getExpirationDate().isBefore(java.time.LocalDateTime.now())) {
            throw new IllegalStateException("Card is expired.");
        }
        //check if the book is available, if it is not, throw an exception
        if (loan.getBook().getQuantity() == 0) {
            throw new IllegalStateException("Book is not available.");
        }
        //set the starting time to the current date
        loan.setStartingTime(java.time.LocalDateTime.now());
        //if the card type is VIP_CARD, set the finishing time to 4 weeks after the starting time
        // if the card type is LOYALTY_CARD, set the finishing time to 2 weeks after the starting time
        // else set the finishing time to 1 week after the starting time
        if (card.getCardType().equals("VIP_CARD")) {
            loan.setFinishingTime(loan.getStartingTime().plusWeeks(4));
        } else if (card.getCardType().equals("LOYALTY_CARD")) {
            loan.setFinishingTime(loan.getStartingTime().plusWeeks(2));
        } else {
            loan.setFinishingTime(loan.getStartingTime().plusWeeks(1));
        }
        //set the isRenewed attribute to false
        loan.setRenewed(false);
        //set the isOverdue attribute to false
        loan.setOverdue(false);
        //set the renewCount attribute to 0
        loan.setRenewCount(0);
        //set the isReturned attribute to false
        loan.setReturned(false);
        //set the returnTime attribute to null
        loan.setReturnTime(null);
        //set the book quantity to the book quantity - 1
        //book.get().setQuantity(book.get().getQuantity() - 1);
        //return the loan
        return loanService.createLoan(loan);
    }

    @GetMapping("/{id}")
    public Loan getLoansById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }


    /*
    @PutMapping("/{loansId}")
    public Loan updateLoans(@PathVariable Long loansId, @RequestBody Loan loan) {
        loan.setId(loansId);
        return loanService.createLoan(loan);
    }*/

    @DeleteMapping("/{id}")
    public void deleteLoans(@PathVariable Long id) {
        loanService.deleteLoans(id);
    }

    @GetMapping("/existsByLoanId/{loanId}")
    public Boolean existsByLoanId(@PathVariable Long loanId) {
        return  loanService.existsByLoanId(loanId);
    }


    @GetMapping("/renewLoan/{loanId}")
    public Loan renewLoan(@PathVariable Long loanId) throws LoanCannotBeRenewed {
        return loanService.renewLoan(loanId);
    }



}
