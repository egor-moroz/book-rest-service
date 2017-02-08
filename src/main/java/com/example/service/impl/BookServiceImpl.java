package com.example.service.impl;

import com.example.model.Book;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by EgorPC on 07.02.2017.
 */

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book add(Book obj) {
        return bookRepository.saveAndFlush(obj);
    }

    @Override
    public Book edit(Book obj) {
        return bookRepository.saveAndFlush(obj);
    }

    @Override
    public void delete(Book obj) {
        bookRepository.delete(obj);
    }

    @Override
    public Book get(Integer integer) {
        return bookRepository.findOne(integer);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
