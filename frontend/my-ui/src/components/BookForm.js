import React, { useState } from 'react';
import axios from 'axios';

export default function BookForm() {
  const [book, setBook] = useState({
    id: "",
    title: "",
    description: "",
    category: "",
    type: "ONE_TIME",
    gradeRange: "",
    minAge: 6,
    maxAge: 10,
    price: 0,
    nextSessionDate: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook({ ...book, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8081/api/books", book);
    alert("Book saved successfully");
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add Book</h2>
      {Object.keys(book).map((key) => (
        <div key={key}>
          <label>{key}:</label>
          <input type="text" name={key} value={book[key]} onChange={handleChange} />
        </div>
      ))}
      <button type="submit">Save</button>
    </form>
  );
}
