package com.glsi.xpress.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //when the loan was created
    private LocalDateTime startingTime;
    //when the loan time is over
    private LocalDateTime finishingTime;
    //when the book was supposed to be returned
    private LocalDateTime returnTime;
    //when the book was actually returned
    private boolean isReturned;
    //if the loan was renewed
    private boolean isRenewed;
    //if the loan is overdue
    private boolean isOverdue;
    //how many times the loan was renewed
    private int renewCount=0 ;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // isOverdue is true if the finishing time is before the current time
    public boolean isOverdue() {
        return this.finishingTime.isBefore(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", startingTime=" + startingTime +
                ", finishingTime=" + finishingTime +
                ", isRenewed=" + isRenewed +
                ", isOverdue=" + isOverdue +
                ", renewCount=" + renewCount +
                ", card=" + card +
                ", book=" + book +
                '}';
    }
}
