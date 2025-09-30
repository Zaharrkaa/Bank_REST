package com.example.bankcards.util;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class CardGenerator {

    public Card generateCard() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(1, 10));
        for (int i = 0; i < 15; i++) {
            sb.append(random.nextInt(0, 10));
        }
        String cardNumber = sb.toString();
        LocalDate expiryDate = LocalDate.now().plusYears(5);
        int cvv = random.nextInt(100, 1000);
        return new Card(cardNumber, expiryDate, cvv, Status.ACTIVE, 0);
    }
}
