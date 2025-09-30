package com.example.bankcards.controller;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.UserDto;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public AdminController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/createCard")
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto) {
        cardService.save(cardDto);
        return ResponseEntity.ok(cardDto);
    }

    @PatchMapping("/activate/{cardNumber}")
    public ResponseEntity<String> activateCard(@PathVariable String cardNumber) {
        System.out.println(cardNumber);
        cardService.activate(cardNumber);
        return ResponseEntity.ok("Card activated successfully");
    }
    @PatchMapping("/block/{cardNumber}")
    public ResponseEntity<String> blockCard(@PathVariable String cardNumber) {
        cardService.block(cardNumber);
        return ResponseEntity.ok("Card blocked successfully");
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.ok("User deleted successfully");
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(defaultValue = "false") String withCards) {
        if (withCards.equals("true")) {
            return ResponseEntity.ok(userService.findAllWithCards());
        }
        return ResponseEntity.ok(userService.findAllWithoutCards());
    }

    @GetMapping("/allCards")
    public ResponseEntity<List<CardDto>> getAllCards() {
        return ResponseEntity.ok(cardService.findAll());
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
