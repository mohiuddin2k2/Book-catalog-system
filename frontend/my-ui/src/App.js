import React from 'react';
import BookForm from './components/BookForm';
import BookList from './components/BookList';

function App() {
  return (
    <div>
      <h1>Elasticsearch Book Catalog</h1>
      <BookForm />
      <hr />
      <BookList />
    </div>
  );
}

export default App;
