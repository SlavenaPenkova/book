package com.tinqin.academy.persistence.seeders;

import com.tinqin.academy.persistence.filereader.BookModel;
import com.tinqin.academy.persistence.filereader.FileReader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class BookSeederVersionFileReader implements ApplicationRunner{

        @Value("${spring.datasource.url}")
        private String jdbcUrl;

        @Value("${spring.datasource.username}")
        private String postgresUsername;

        @Value("${spring.datasource.password}")
        private String postgresPassword;


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

           // FileReader fileReader = FileReader.loadFile("rest/src/main/resources/book.csv", 2);
            FileReader fileReader = FileReader.loadFile("book.csv", 2);
            List<BookModel> batch = fileReader.getBatch();

            while(!batch.isEmpty()) {
            for (BookModel book : batch) {
                ps.setInt(1, book.getPages());
                ps.setDouble(2, book.getPrice());
                ps.setString(3, book.getTitle());
                ps.setString(4, book.getAuthorFirstName());
                ps.setString(5, book.getAuthorLastName());

                ps.addBatch();
                ps.clearParameters();
            }

            ps.executeBatch();
            batch = fileReader.getBatch();
        }
    }
}
