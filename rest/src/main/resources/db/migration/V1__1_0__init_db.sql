CREATE TABLE authors
(
    id         UUID         NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_authors PRIMARY KEY (id)
);

CREATE TABLE books
(
    id           UUID                        NOT NULL,
    title        VARCHAR(255)                NOT NULL,
    pages        VARCHAR(255)                NOT NULL,
    price        DECIMAL                     NOT NULL,
    price_rental DECIMAL,
    stock        INTEGER                     NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_on    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted   BOOLEAN,
    book_status  VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE books_authors
(
    author_id UUID NOT NULL,
    book_id   UUID NOT NULL
);

CREATE TABLE books_by_seeder
(
    id           UUID                        NOT NULL,
    author_id    UUID,
    title        VARCHAR(255)                NOT NULL,
    pages        VARCHAR(255)                NOT NULL,
    price        DECIMAL                     NOT NULL,
    price_rental DECIMAL,
    stock        INTEGER                     NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_on    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted   BOOLEAN,
    book_status  VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_books_by_seeder PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         UUID NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    is_admin   BOOLEAN,
    is_blocked BOOLEAN,
    password   VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE books_by_seeder
    ADD CONSTRAINT FK_BOOKS_BY_SEEDER_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES authors (id);

ALTER TABLE books_authors
    ADD CONSTRAINT fk_booaut_on_author FOREIGN KEY (author_id) REFERENCES authors (id);

ALTER TABLE books_authors
    ADD CONSTRAINT fk_booaut_on_book FOREIGN KEY (book_id) REFERENCES books (id);