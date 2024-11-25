package com.tinqin.academy.persistence.repositories;

import com.tinqin.academy.persistence.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
