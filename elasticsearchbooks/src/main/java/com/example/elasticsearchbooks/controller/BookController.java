package com.example.elasticsearchbooks.controller;

import com.example.elasticsearchbooks.model.Book;
import com.example.elasticsearchbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable String id) {
        return bookService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        bookService.deleteById(id);
    }

    @GetMapping("/search")
    public List<Book> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(defaultValue = "nextSessionDate") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return bookService.search(keyword, category, type, minAge, maxAge,
                minPrice, maxPrice, dateFrom, sort, page, size);
    }
}
