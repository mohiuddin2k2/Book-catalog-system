import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function BookList() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8081/api/books")
      .then((res) => setBooks(res.data));
  }, []);

  return (
    <div>
      <h2>Book List</h2>
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            {book.title} - ₹{book.price} - {book.category}
          </li>
        ))}
      </ul>
    </div>
  );
}
