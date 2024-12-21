// src/components/ExpenseForm.js
import React, { useState } from 'react';

const ExpenseForm = ({ onAddExpense }) => {
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (amount && description && date) {
      onAddExpense({ amount, description, date });
      setAmount('');
      setDescription('');
      setDate('');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input 
        type="number" 
        placeholder="Amount" 
        value={amount} 
        onChange={(e) => setAmount(e.target.value)} 
        required 
      />
      <input 
        type="text" 
        placeholder="Description" 
        value={description} 
        onChange={(e) => setDescription(e.target.value)} 
        required 
      />
      <input 
        type="date" 
        value={date} 
        onChange={(e) => setDate(e.target.value)} 
        required 
      />
      <button type="submit">Add Expense</button>
    </form>
  );
};

export default ExpenseForm;
