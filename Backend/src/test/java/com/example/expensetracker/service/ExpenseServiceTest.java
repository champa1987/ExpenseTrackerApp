package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllExpenses() {
        // Arrange
        List<Expense> mockExpenses = Arrays.asList(
                new Expense(100.0, "Rent", LocalDate.of(2024, 12, 1)),
                new Expense(50.0, "Utilities", LocalDate.of(2024, 12, 5))
        );
        when(expenseRepository.findAll()).thenReturn(mockExpenses);

        // Act
        List<Expense> expenses = expenseService.getAllExpenses();

        // Assert
        assertEquals(2, expenses.size());
        assertEquals("Rent", expenses.get(0).getDescription());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void testAddExpense() {
        // Arrange
        Expense expense = new Expense(200.0, "Groceries", LocalDate.now());
        Expense savedExpense = new Expense(200.0, "Groceries", LocalDate.now());
        savedExpense.setId(1L);
        when(expenseRepository.save(expense)).thenReturn(savedExpense);

        // Act
        Expense result = expenseService.addExpense(expense);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Groceries", result.getDescription());
        verify(expenseRepository, times(1)).save(expense);
    }
}
