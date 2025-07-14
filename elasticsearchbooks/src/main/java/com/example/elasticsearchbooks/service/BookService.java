package com.example.elasticsearchbooks.service;

import com.example.elasticsearchbooks.model.Book;
import com.example.elasticsearchbooks.repository.BookRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import static org.elasticsearch.index.query.QueryBuilders.*;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public List<Book> search(String keyword, String category, String type,
                             Integer minAge, Integer maxAge,
                             Double minPrice, Double maxPrice,
                             String dateFrom, String sort,
                             int page, int size) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // Full-text search
        if (keyword != null && !keyword.isEmpty()) {
            boolQuery.must(QueryBuilders.multiMatchQuery(keyword, "title", "description"));
        } else {
            boolQuery.must(QueryBuilders.matchAllQuery());
        }

        if (category != null) boolQuery.filter(QueryBuilders.termQuery("category", category));
        if (type != null) boolQuery.filter(QueryBuilders.termQuery("type", type));
        if (minAge != null) boolQuery.filter(QueryBuilders.rangeQuery("minAge").gte(minAge));
        if (maxAge != null) boolQuery.filter(QueryBuilders.rangeQuery("maxAge").lte(maxAge));
        if (minPrice != null) boolQuery.filter(QueryBuilders.rangeQuery("price").gte(minPrice));
        if (maxPrice != null) boolQuery.filter(QueryBuilders.rangeQuery("price").lte(maxPrice));
        if (dateFrom != null) boolQuery.filter(QueryBuilders.rangeQuery("nextSessionDate").gte(dateFrom));

        Sort sorting = Sort.by("nextSessionDate").ascending();
        if ("priceAsc".equals(sort)) sorting = Sort.by("price").ascending();
        if ("priceDesc".equals(sort)) sorting = Sort.by("price").descending();

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(PageRequest.of(page, size))
                .withSort(sorting)
                .build();

        SearchHits<Book> hits = elasticsearchOperations.search(searchQuery, Book.class);
        return hits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}