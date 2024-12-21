package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.ExpenseRepository;  // Add the repository import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;  // Inject the repository

    // Add a new expense (POST /expenses)
    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        try {
            // Save the expense to the database
            Expense savedExpense = expenseRepository.save(expense);
            System.out.println("Expense added: " + savedExpense);  // Log the added expense
            return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();  // Log the error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Fetch all expenses (GET /expenses)
    @GetMapping
    public List<Expense> getAllExpenses() {
        System.out.println("Fetching all expenses from database.");
        return expenseRepository.findAll();  // Fetch expenses from the database
    }

    // Delete an expense (DELETE /expenses/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        try {
            // Check if the expense exists before deleting
            if (expenseRepository.existsById(id)) {
                expenseRepository.deleteById(id);  // Delete the expense from the database
                return new ResponseEntity<>("Expense deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Expense not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log the error
            return new ResponseEntity<>("Error deleting expense.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
