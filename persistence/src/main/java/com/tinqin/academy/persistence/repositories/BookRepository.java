package com.tinqin.academy.persistence.repositories;

import com.tinqin.academy.persistence.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

}
