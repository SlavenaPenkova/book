package com.tinqin.academy.persistence.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Order(1)
public class AuthorsSeeder implements ApplicationRunner {
    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_AUTHOR_QUERY_TEMPLATE = """
            INSERT INTO authors (id, first_name, last_name)
            VALUES """;

    private final List<String> authors = List.of(
            "Ivan Vazov",
            "Hristo Botev",
            "Gabor Mate",
            "Daniel Mate");

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String names = authors
                .stream()
                .map(author -> author.split(" "))
                .map(authorNames -> String.format("(gen_random_uuid(), '%s', '%s')", authorNames[0], authorNames[1]))
                .collect(Collectors.joining(", "));

        String query = INSERT_AUTHOR_QUERY_TEMPLATE + names;

        jdbcTemplate.execute(query);
    }
}
