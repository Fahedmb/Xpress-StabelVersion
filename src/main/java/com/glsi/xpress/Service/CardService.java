package com.glsi.xpress.Service;

import com.glsi.xpress.Entity.Card;

import java.util.List;

public interface CardService {

    Card getCardById(Long id);
    Card findByCardNumber(Long cardNumber);
    void deleteCard(Long id);

    Card createCard(Card card);

    // update card
    Card updateCard(Card card);

    // check if card expiration date is valid
    boolean isCardValid(Card card);

    // check what type of card is it
    String getCardType(Card card);


    List<Card> getAllCards();
}
