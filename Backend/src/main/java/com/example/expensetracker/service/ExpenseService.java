package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;       // Import the Expense class
import com.example.expensetracker.repository.ExpenseRepository;  // Import the ExpenseRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    public ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
