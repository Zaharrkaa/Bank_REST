package com.example.bankcards.controller;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping()
    public ResponseEntity<List<CardDto>> getAllCards() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCard(@RequestBody @Valid CardDto cardDto, BindingResult bindingResult)
    {
        if (!bindingResult.hasErrors()) {
            cardService.save(cardDto);
            return ResponseEntity.ok("Card created successfully");
        }
        StringBuilder stringBuilder = new StringBuilder();
        bindingResult.getAllErrors().forEach(error -> stringBuilder.append(error.getDefaultMessage()).append("\n"));
        String errorMessage = stringBuilder.toString();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @PatchMapping("/activate/{cardNumber}")
    public ResponseEntity<String> activateCard(@PathVariable String cardNumber) {
        cardService.activate(cardNumber);
        return ResponseEntity.ok("Card activated successfully");
    }
    @PatchMapping("/block/{cardNumber}")
    public ResponseEntity<String> blockCard(@PathVariable String cardNumber) {
        cardService.block(cardNumber);
        return ResponseEntity.ok("Card blocked successfully");
    }

    @DeleteMapping("/delete/{number}")
    public ResponseEntity<String> deleteCard(@PathVariable String number) {
        cardService.delete(number);
        return ResponseEntity.ok("Card deleted successfully");
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(CardNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
    }
}
