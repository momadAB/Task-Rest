package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RController {
  private final List<ContactJSON> contacts = new ArrayList<>();

  @PostMapping("/addContact")
  public String addContact(@RequestBody ContactJSON inputContactJSON) {
    // Check if contact already exists
    for (ContactJSON contact : contacts) {
      if (contact.getEmail().equals(inputContactJSON.getEmail())) {
        return "Contact already exists with this email!";
      }
    }
    // If contact does not already exist, add it
    contacts.add(inputContactJSON);
    return "Contact added successfully!";
  }

  @GetMapping("/getContactDetails")
  public ContactJSON getContactDetails(@RequestParam String name) {
    // Check if contact exists and return JSON if it does

    for (ContactJSON contact : contacts) {
      if (contact.getName().equals(name)) {
        return contact;
      }
    }
    // Return error message if contact does not exist
    // I assume that the message would reveal too much information and be insecure,
    //  but its just for proof of concept.
    throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Contact with name '" + name + "' not found.");
  }
}
