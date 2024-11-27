package com.tinqin.academy.persistence.repositories;

import com.tinqin.academy.persistence.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @Query("SELECT a from Author a WHERE a.id in ?1")
       List<Author> findAuthorsById(List<UUID> listOfIds );
}
