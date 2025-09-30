package com.example.bankcards.service;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.util.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;

    @Autowired
    public CardService(CardRepository cardRepository, CardMapper cardMapper, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(CardDto cardDto) {
        Optional<User> user = userRepository.findByUsername(cardDto.getOwnerName());
        if (user.isPresent()) {
            Card card = cardMapper.toCard(cardDto);
            card.setOwner(user.get());
            cardRepository.save(card);
        }
        else throw new UserNotFoundException();

    }

    @Transactional
    public void activate(String number) {
        Optional<Card> card = cardRepository.findByNumber(number);
        if (card.isPresent()) {
            Card cardToActivate = card.get();
            cardToActivate.setStatus(Status.ACTIVE);
        }
        else throw new CardNotFoundException();
    }

    @Transactional
    public void block(String number) {
        Optional<Card> card = cardRepository.findByNumber(number);
        if (card.isPresent()) {
            Card cardToActivate = card.get();
            cardToActivate.setStatus(Status.BLOCKED);
        }
        else throw new CardNotFoundException();
    }

    public List<CardDto> findAll() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream().map(cardMapper::toCardDto).toList();
    }
}
