package com.example.bankcards.service;


import com.example.bankcards.dto.CardDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Test
    public void createCard(){
        User user = new User("test", "qwerty", "ROLE_USER");
        String number = "1234567812345678";
        String ownerName = user.getUsername();
        LocalDate expiryDate = LocalDate.now().plusYears(3);
        String cvv = "133";
        Status status = Status.ACTIVE;
        Integer balance = 0;
        Card card = new Card(number, expiryDate, cvv, status, balance);
        when(cardRepository.save(card)).thenReturn(card);
        Card card1 = cardRepository.save(card);
        Assertions.assertEquals(card1.getNumber(), card.getNumber());
    }
}
