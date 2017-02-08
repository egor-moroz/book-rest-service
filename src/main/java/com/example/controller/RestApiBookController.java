package com.example.controller;



import com.example.model.Book;
import com.example.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<Book>> listAllBooks(){
        List<Book> books = bookService.getAll();
        if(books.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder){
        logger.info("Creating Book : {}",book );
        bookService.add(book);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/rest/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<String>(httpHeaders,HttpStatus.CREATED);
    }

}
