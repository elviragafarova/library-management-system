package com.example.mslibrarymanagementsystem.controller;

import com.example.mslibrarymanagementsystem.criteria.PageCriteria;
import com.example.mslibrarymanagementsystem.dto.request.BookRequest;
import com.example.mslibrarymanagementsystem.dto.response.BookResponse;
import com.example.mslibrarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createBook(@Valid @RequestBody BookRequest bookRequest) {
        bookService.createBook(bookRequest);
    }

    @GetMapping
    public Page<BookResponse> getBooks(PageCriteria pageCriteria) {
        return bookService.getBooks(pageCriteria);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{bookId}/borrow/{memberId}")
    @ResponseStatus(NO_CONTENT)
    public void borrowBook(@PathVariable Long bookId,
                           @PathVariable Long memberId) {
        bookService.borrowBook(bookId, memberId);
    }

    @PutMapping("/{bookId}/return")
    @ResponseStatus(NO_CONTENT)
    public void returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id,
                                   @Valid @RequestBody BookRequest bookRequest) {
        return bookService.updateBook(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}