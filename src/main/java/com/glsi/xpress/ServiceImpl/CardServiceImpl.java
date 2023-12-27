package com.glsi.xpress.ServiceImpl;

import com.glsi.xpress.Entity.Card;
import com.glsi.xpress.Repository.CardRepository;
import com.glsi.xpress.Repository.UserRepository;
import com.glsi.xpress.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public Card findByCardNumber(Long cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    // update card
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public boolean isCardValid(Card card) {
        //check if card expiration date is more than the current date
        return card.getExpirationDate().isAfter(LocalDateTime.now());
    }

    @Override
    public String getCardType(Card card) {
        //check what type of card is it
        return card.getCardType();
    }

    @Override
    public java.util.List<Card> getAllCards() {
        return cardRepository.findAll();
    }
}
