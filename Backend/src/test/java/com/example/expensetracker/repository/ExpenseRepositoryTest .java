package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    void testSaveAndFindById() {
        // Arrange
        Expense expense = new Expense(100.0, "Test Expense", LocalDate.now());
        Expense savedExpense = expenseRepository.save(expense);

        // Act
        Expense foundExpense = expenseRepository.findById(savedExpense.getId()).orElse(null);

        // Assert
        assertNotNull(foundExpense);
        assertEquals("Test Expense", foundExpense.getDescription());
    }

    @Test
    void testFindAll() {
        // Arrange
        Expense expense1 = new Expense(50.0, "Expense 1", LocalDate.now());
        Expense expense2 = new Expense(75.0, "Expense 2", LocalDate.now());
        expenseRepository.save(expense1);
        expenseRepository.save(expense2);

        // Act
        List<Expense> expenses = expenseRepository.findAll();

        // Assert
        assertEquals(2, expenses.size());
    }
}
