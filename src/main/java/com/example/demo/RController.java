package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RController {
  private final List<ContactJSON> contacts = new ArrayList<>();

  @PostMapping("/addContact")
  public ResponseEntity<String> addContact(@RequestBody ContactJSON inputContactJSON) {
    // Check if contact already exists
    for (ContactJSON contact : contacts) {
      if (contact.getEmail().equals(inputContactJSON.getEmail())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Contact already exists with this email!");
      }
    }
    // If contact does not already exist, add it
    contacts.add(inputContactJSON);
    return ResponseEntity.status(HttpStatus.CREATED).body("Contact added successfully!");
  }

  @GetMapping("/getContactDetails")
  public ResponseEntity<ContactJSON> getContactDetails(@RequestParam String name) {
    // Check if contact exists and return JSON if it does
    for (ContactJSON contact : contacts) {
      if (contact.getName().equals(name)) {
        return ResponseEntity.status(HttpStatus.OK).body(contact);
      }
    }
    // Return error message if contact does not exist
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }
}
