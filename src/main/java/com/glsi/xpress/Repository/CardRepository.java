package com.glsi.xpress.Repository;

import com.glsi.xpress.Entity.Card;
import com.glsi.xpress.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(Long cardNumber);

    Card findByUser(User user);
}
