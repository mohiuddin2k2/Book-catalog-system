The Book Catalog System is a full-stack web application designed to manage and search books using Elasticsearch for powerful search capabilities and Spring Boot for backend services. The frontend is built in React.js for seamless user interaction.
To enable users to:

Add, view, update, and delete book records.

Search books using full-text, filters (age, price, type, category), and date.

Sort results by price or next session date.

Use a responsive frontend to interact with APIs.
🧰 Technology Stack

Backend:

Java 11+

Spring Boot

Spring Data Elasticsearch (7.17)

Elasticsearch (zip installation)

Frontend:

React.js (with Axios)

Node.js & npm

Tools:

IntelliJ IDEA

Postman

GitHub
Features

Full CRUD operations:

Create, Read, Update, Delete book records

Full-text search:

Search by title and description using Elasticsearch

Filters:

minAge / maxAge

price range

exact match: category, type (ONE_TIME, COURSE, CLUB)

future session dates

Sorting:

by nextSessionDate (default)

by priceAsc / priceDesc

Pagination:

via page & size

React UI:

Submit form to add books

View book list
