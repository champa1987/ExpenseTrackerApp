package com.example.expensetracker;

import com.example.expensetracker.model.Expense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Additional query methods can be added here if needed
}
