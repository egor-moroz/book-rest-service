package com.example.repository;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EgorPC on 07.02.2017.
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

}
