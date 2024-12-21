import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ExpenseForm from './ExpenseForm';
import ExpenseTable from './ExpenseTable';
import './Expenses.css';

const Expenses = () => {
  const [expenses, setExpenses] = useState([]);

  // Fetch expenses from backend API when component mounts
  useEffect(() => {
    axios.get('http://localhost:8080/expenses')  // Correct backend URL
      .then(response => {
        console.log('Fetched expenses:', response.data);
        setExpenses(response.data); // Set the fetched data in state
      })
      .catch(error => console.error('Error fetching expenses:', error));
  }, []);

  const handleAddExpense = (expense) => {
    axios.post('http://localhost:8080/expenses', expense)  // Backend URL for posting data
      .then(response => {
        setExpenses((prevExpenses) => [...prevExpenses, response.data]); // Add new expense
      })
      .catch(error => console.error('Error adding expense:', error));
  };

  const handleDeleteExpense = (id) => {
    axios.delete(`http://localhost:8080/expenses/${id}`)  // Backend URL for deleting data
      .then(() => {
        setExpenses((prevExpenses) => prevExpenses.filter(expense => expense.id !== id)); // Remove deleted expense
      })
      .catch(error => console.error('Error deleting expense:', error));
  };

  return (
    <div className="expenses-container">
      <h1 style={{ display: 'flex', alignItems: 'center' }}>
        <img 
          src="/images/money-sixteen_nine.JPEG" 
          alt="Champa's Expense Tracker" 
          style={{ width: '80px', marginRight: '15px' }} // Adjust image size and margin
        />
        Champa's Expense Tracker
      </h1>
      {/* Pass the handleAddExpense function to ExpenseForm */}
      <ExpenseForm onAddExpense={handleAddExpense} />
      
      {/* Pass expenses and delete function to ExpenseTable */}
      <ExpenseTable expenses={expenses} onDeleteExpense={handleDeleteExpense} />
    </div>
  );
};

export default Expenses;
