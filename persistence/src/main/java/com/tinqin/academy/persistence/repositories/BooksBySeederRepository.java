package com.tinqin.academy.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tinqin.academy.persistence.models.BooksBySeeder;

import java.util.UUID;

@Repository
public interface BooksBySeederRepository extends JpaRepository<BooksBySeeder, UUID> {
}
