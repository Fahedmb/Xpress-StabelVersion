package com.glsi.xpress.ServiceImpl;

import com.glsi.xpress.Entity.Card;
import com.glsi.xpress.Entity.Enum.CardType;
import com.glsi.xpress.Entity.Loan;
import com.glsi.xpress.Exceptions.LoanCannotBeRenewed;
import com.glsi.xpress.Repository.LoanRepository;
import com.glsi.xpress.Service.CardService;
import com.glsi.xpress.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan renewLoan(Long loanId) throws LoanCannotBeRenewed {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        if (loan.getRenewCount() < 3) {
            loan.setRenewCount(loan.getRenewCount() + 1);
            loan.setRenewed(true);
            // get the card from the loan
            Card card = loan.getCard();
            // if the card is a VIP card, set the finishing time to 4 weeks after the starting time
            if (card.getCardType().equals("VIP_CARD")) {
                loan.setFinishingTime(loan.getStartingTime().plusWeeks(4));
            }
            // if the card is a LOYALTY card, set the finishing time to 2 weeks after the starting time
            else if (card.getCardType().equals("LOYALTY_CARD")) {
                loan.setFinishingTime(loan.getStartingTime().plusWeeks(2));
            }
            // else set the finishing time to 1 week after the starting time
            else {
                loan.setFinishingTime(loan.getStartingTime().plusWeeks(1));
            }

        } else {
            // Handle the case where the loan cannot be renewed further by throwing LoanCannotBeRenewed exception
            throw new LoanCannotBeRenewed("Loan cannot be renewed further");

        }
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public void deleteLoans(Long id) {

    }

    @Override
    public Boolean existsByLoanId(Long loanId) {
        return null;
    }

    // Additional methods as needed
}