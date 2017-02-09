package com.example.controller;


import com.example.Util.CustomError;
import com.example.model.Book;
import com.example.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by EgorPC on 07.02.2017.
 */
@RestController
@RequestMapping("/rest")
public class RestApiBookController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiBookController.class);

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> books = bookService.getAll();
        if (books.isEmpty()) {
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Book : {}", book);
        bookService.add(book);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/rest/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable("id") long id) {
        logger.info("Get book with id {}", id);
        Book book = bookService.get(id);
        if (book == null) {
            logger.error("Book with id {} not found.", id);
            return new ResponseEntity<CustomError>(new CustomError("Can not be found. Book with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        logger.info("Update book with id {}", id);
        Book currentBook = bookService.get(id);
        if (currentBook == null) {
            logger.error("Book with id {} not found.", id);
            return new ResponseEntity<CustomError>(new CustomError("Can not be updated. Book with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        currentBook.setTitle(book.getTitle());
        currentBook.setDescription(book.getDescription());
        currentBook.setNbOfPage(book.getNbOfPage());
        currentBook.setPrice(book.getPrice());
        bookService.edit(currentBook);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
        logger.info("Deleting book with id : {}", id);
        Book book = bookService.get(id);
        if (book == null) {
            logger.error("Can not be removed. Book with id {} not found", id);
            return new ResponseEntity<CustomError>(new CustomError("Can not be removed. Book with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        bookService.delete(book);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteAllBooks() {
        logger.info("Deleting all books");
        bookService.deleteAll();
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }
}
