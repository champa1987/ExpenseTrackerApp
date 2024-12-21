// src/App.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ExpenseForm from './components/ExpenseForm';
import ExpenseTable from './components/ExpenseTable';

const App = () => {
  const [expenses, setExpenses] = useState([]);

  useEffect(() => {
    // Fetch expenses from backend API (updated URL)
    axios.get('http://localhost:8080/expenses')  // Update to the correct backend URL
      .then(response => {
	 console.log('Fetched expenses:', response.data);
        setExpenses(response.data);
      })
      .catch(error => console.error('Error fetching expenses:', error));
  }, []);

  const handleAddExpense = (expense) => {
    axios.post('http://localhost:8080/expenses', expense)  // Updated URL
      .then(response => {
        setExpenses([...expenses, response.data]);
      })
      .catch(error => console.error('Error adding expense:', error));
  };

  const handleDeleteExpense = (id) => {
    axios.delete(`http://localhost:8080/expenses/${id}`)  // Updated URL
      .then(() => {
        setExpenses(expenses.filter(expense => expense.id !== id));
      })
      .catch(error => console.error('Error deleting expense:', error));
  };

  return (
    <div>
      <ExpenseForm onAddExpense={handleAddExpense} />
      <ExpenseTable expenses={expenses} onDeleteExpense={handleDeleteExpense} />
    </div>
  );
};

export default App;
