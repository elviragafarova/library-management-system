package com.example.mslibrarymanagementsystem.service;

import com.example.mslibrarymanagementsystem.criteria.PageCriteria;
import com.example.mslibrarymanagementsystem.dto.request.BookRequest;
import com.example.mslibrarymanagementsystem.dto.response.BookResponse;
import com.example.mslibrarymanagementsystem.entity.AuthorEntity;
import com.example.mslibrarymanagementsystem.entity.BookEntity;
import com.example.mslibrarymanagementsystem.entity.MemberEntity;
import com.example.mslibrarymanagementsystem.exceptions.*;
import com.example.mslibrarymanagementsystem.mapper.BookMapper;
import com.example.mslibrarymanagementsystem.repository.AuthorRepository;
import com.example.mslibrarymanagementsystem.repository.BookRepository;
import com.example.mslibrarymanagementsystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final AuthorRepository authorRepository;

    public void createBook(BookRequest bookRequest) {
        var author = fetchAuthorIfExists(bookRequest.getAuthorId());
        var book = BookMapper.toEntity(bookRequest);

        book.setAuthor(author);
        book.setAvailable(true);

        bookRepository.save(book);
    }

    public Page<BookResponse> getBooks(PageCriteria pageCriteria) {
        var pageable = PageRequest.of(
                pageCriteria.getPage(),
                pageCriteria.getCount(),
                Sort.by(Sort.Direction.ASC, "id"));
        Page<BookEntity> books = bookRepository.findAll(pageable);
        return books.map(BookMapper::toResponse);
    }

    public BookResponse getBookById(Long id) {
        var book = fetchBookIfExists(id);
        return BookMapper.toResponse(book);
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        var book = fetchBookIfExists(id);
        var author = fetchAuthorIfExists(request.getAuthorId());
        BookMapper.updateBook(book, request);
        book.setAuthor(author);
        var updatedBook = bookRepository.save(book);
        return BookMapper.toResponse(updatedBook);
    }

    public void borrowBook(Long bookId, Long memberId) {
        var book = fetchBookIfExists(bookId);
        var member = fetchMemberIfExists(memberId);

        if (!book.isAvailable()) {
            throw new BookAlreadyBorrowedException("Book is already borrowed.");
        }

        book.setBorrowedBy(member);
        book.setAvailable(false);

        var borrowedBook = bookRepository.save(book);
        BookMapper.toResponse(borrowedBook);
    }

    public void returnBook(Long bookId) {
        var book = fetchBookIfExists(bookId);
        if (book.isAvailable()) {
            throw new BookAlreadyReturnedException("Book is already returned.");
        }
        book.setBorrowedBy(null);
        book.setAvailable(true);
        var borrowedBook = bookRepository.save(book);
        BookMapper.toResponse(borrowedBook);
    }

    public void deleteBook(Long id) {
        var book = fetchBookIfExists(id);
        bookRepository.delete(book);
    }

    private BookEntity fetchBookIfExists(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    private MemberEntity fetchMemberIfExists(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));
    }

    private AuthorEntity fetchAuthorIfExists(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
    }
}