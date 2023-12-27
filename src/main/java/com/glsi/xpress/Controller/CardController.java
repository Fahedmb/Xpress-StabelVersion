package com.glsi.xpress.Controller;

import com.glsi.xpress.Entity.Card;
import com.glsi.xpress.Entity.User;
import com.glsi.xpress.Service.CardService;
import com.glsi.xpress.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = "http://localhost:3000")
public class CardController {

    private final CardService cardService;
    @Autowired
    private UserService userService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public Card createUser(@RequestBody Card card) {
        // get the user id from the request body
        Long user_id = card.getUser().getId();
        // get the user from the database
        User user = userService.getUserById(user_id);
        // set the user of the card to the user from the database
        card.setUser(user);
        // card created at is the current date
        card.setCreatedAt(java.time.LocalDateTime.now());
        // if the card type is not specified, set it to MEMBER_CARD
        if (card.getCardType() == null) {
            card.setCardType("MEMBER_CARD");
        }
        // set the expiration date to 1 year after the creation date
        card.setExpirationDate(card.getCreatedAt().plusYears(1));
        // if the card type is VIP_CARD, set the expiration date to 2 years after the creation date
        if (card.getCardType().equals("VIP_CARD")) {
            card.setExpirationDate(card.getCreatedAt().plusYears(2));
        }
        return cardService.createCard(card);
    }

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping("/cardNumber")
    public Card getCardByCardNumber(@RequestParam Long cardNumber) {
        return cardService.findByCardNumber(cardNumber);
    }

    @GetMapping
    public java.util.List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @PostMapping("/extend/{id}")
    //get the id of the card to extend
    public Card extendCard(@PathVariable Long id) {
        // get the card from the database
        Card card = cardService.getCardById(id);
        // if the card is valid, extend the expiration date by 1 year if teh card type is MEMBER_CARD, LIBRARIAN_CARD or STUDENT_CARD
        // extend the expiration date by 2 years if the card type is VIP_CARD
        if (cardService.isCardValid(card)) {
            if (card.getCardType().equals("MEMBER_CARD")
                    || card.getCardType().equals("LIBRARIAN_CARD")
                    || card.getCardType().equals("STUDENT_CARD")) {
                card.setExpirationDate(card.getExpirationDate().plusYears(1));
            } else if (card.getCardType().equals("VIP_CARD")) {
                card.setExpirationDate(card.getExpirationDate().plusYears(2));
            }
        }
        //return the card
        return cardService.createCard(card);
    }

    @PutMapping("/upgradetovip/{id}")
    //get the id of the card to upgrade
    public Card upgradeCard(@PathVariable Long id) {

        // get the card from the database
        Card card = cardService.getCardById(id);
        System.out.println(card);
        // if the card is valid, upgrade the card to VIP_CARD if the Member has borrowed more than 100 books
        if (cardService.isCardValid(card)) {
            if (card.getUser().getBooksBorrowed() > 100) {
                card.setCardType("VIP_CARD");
                card.setExpirationDate(card.getExpirationDate().plusYears(2));
            }
        }// if the borrowed books are less than 10 display a message
        else {
            System.out.println("You have to borrow more than 10 books to upgrade your card");
        }
        //return the card
        return cardService.createCard(card);
    }

    @PutMapping("/upgradetoloyal/{id}")
    //get the id of the card to upgrade
    public Card upgradeToLoyalCard(@PathVariable Long id) {

        // get the card from the database
        Card card = cardService.getCardById(id);
        System.out.println(card);
        // if the card is valid, upgrade the card to LOYAL_CARD if the Member has borrowed more than 10 books
        if (cardService.isCardValid(card)) {
            if (card.getUser().getBooksBorrowed() > 20) {
                card.setCardType("LOYAL_CARD");
                card.setExpirationDate(card.getExpirationDate().plusYears(2));
            }
        }// if the borrowed books are less than 10 display a message
        else {
            System.out.println("You have to borrow more than 10 books to upgrade your card");
        }
        //return the card
        return cardService.createCard(card);
    }
}
