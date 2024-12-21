// src/components/ExpenseTable.js
import React from 'react';

const ExpenseTable = ({ expenses, onDeleteExpense }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Amount</th>
          <th>Description</th>
          <th>Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {expenses.map((expense, index) => (
          <tr key={index}>
            <td>{expense.amount}</td>
            <td>{expense.description}</td>
            <td>{expense.date}</td>
            <td>
              <button onClick={() => onDeleteExpense(expense.id)}>Delete</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ExpenseTable;
