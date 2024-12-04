package com.tinqin.academy.persistence.seeders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class BooksSeeder implements ApplicationRunner {
    //private final FileReaderFactory fileReaderFactory;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String postgresUsername;

    @Value("${spring.datasource.password}")
    private String postgresPassword;

    @AllArgsConstructor
    @Builder
    @Getter
    private static class BookTemplate {
        private final String title;
        private final Integer pages;
        private final Double price;
        private final String authorFirstName;
        private final String authorLatName;
    }

    private final List<BookTemplate> books = List.of(
            BookTemplate.builder()
                    .title("Pod igoto")
                    .pages(300)
                    .price(25.0)
                    .authorFirstName("Ivan")
                    .authorLatName("Vazov")
                    .build(),
            BookTemplate.builder()
                    .title("Balad for Georg Henig")
                    .pages(150)
                    .price(15.0)
                    .authorFirstName("Viktor")
                    .authorLatName("Paskov")
                    .build()
    );
    String BOOKS_QUERY = """
            INSERT INTO books_by_seeder (id, created_at, is_deleted, pages, price, stock, title, book_status,update_on, author_id)
            VALUES (gen_random_uuid(),
                    now(),
                    false,
                    ?,
                    ?,
                    0,
                    ?,
                    'PUBLISHED',
                    now(),
                    (SELECT id
                     FROM authors
                     WHERE first_name = ?
                        AND last_name = ?))
            """;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection connection = DriverManager.getConnection(jdbcUrl, postgresUsername, postgresPassword);

        PreparedStatement ps = connection.prepareStatement(BOOKS_QUERY);

        for (BookTemplate book : books) {
            ps.setInt(1, book.getPages());
            ps.setDouble(2, book.getPrice());
            ps.setString(3, book.getTitle());
            ps.setString(4, book.getAuthorFirstName());
            ps.setString(5, book.getAuthorLatName());

            ps.addBatch();
            ps.clearParameters();
        }

        ps.executeBatch();
    }
}