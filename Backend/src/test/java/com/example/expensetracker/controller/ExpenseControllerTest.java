package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseControllerTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseController expenseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddExpense() {
        // Arrange
        Expense expense = new Expense(100.0, "Groceries", LocalDate.now());
        Expense savedExpense = new Expense(100.0, "Groceries", LocalDate.now());
        savedExpense.setId(1L);

        when(expenseRepository.save(expense)).thenReturn(savedExpense);

        // Act
        ResponseEntity<Expense> response = expenseController.addExpense(expense);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void testGetAllExpenses() {
        // Arrange
        List<Expense> mockExpenses = Arrays.asList(
                new Expense(50.0, "Dinner", LocalDate.now()),
                new Expense(30.0, "Snacks", LocalDate.now())
        );
        when(expenseRepository.findAll()).thenReturn(mockExpenses);

        // Act
        List<Expense> expenses = expenseController.getAllExpenses();

        // Assert
        assertEquals(2, expenses.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void testDeleteExpense_Success() {
        // Arrange
        Long expenseId = 1L;
        when(expenseRepository.existsById(expenseId)).thenReturn(true);

        // Act
        ResponseEntity<String> response = expenseController.deleteExpense(expenseId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Expense deleted successfully.", response.getBody());
        verify(expenseRepository, times(1)).deleteById(expenseId);
    }

    @Test
    void testDeleteExpense_NotFound() {
        // Arrange
        Long expenseId = 1L;
        when(expenseRepository.existsById(expenseId)).thenReturn(false);

        // Act
        ResponseEntity<String> response = expenseController.deleteExpense(expenseId);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Expense not found.", response.getBody());
        verify(expenseRepository, never()).deleteById(expenseId);
    }
}
