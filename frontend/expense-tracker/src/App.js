import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ExpenseForm from './components/ExpenseForm';
import Expenses from './components/Expenses'; // Updated to use the 'Expenses' component

const App = () => {
  const [expenses, setExpenses] = useState([]);

  useEffect(() => {
    // Fetch expenses from the backend API
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
        // Use a functional update to ensure you're working with the latest state
        setExpenses(prevExpenses => [...prevExpenses, response.data]);
      })
      .catch(error => console.error('Error adding expense:', error));
  };

  const handleDeleteExpense = (id) => {
    axios.delete(`http://localhost:8080/expenses/${id}`)  // Updated URL
      .then(() => {
        setExpenses(prevExpenses => prevExpenses.filter(expense => expense.id !== id));
      })
      .catch(error => console.error('Error deleting expense:', error));
  };

  return (
    <div>
      <Expenses 
        expenses={expenses} 
        onAddExpense={handleAddExpense} 
        onDeleteExpense={handleDeleteExpense} 
      />
    </div>
  );
};

export default App;
