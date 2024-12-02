package com.tinqin.academy.persistence.models;

import com.tinqin.academy.persistence.enums.BookStatus;
import io.vavr.control.Try;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
public class Book {

    @Builder
    public Book(String title, List<Author> author, String pages, BigDecimal price) {
        this.title = title;
        this.pages = pages;
        this.price = price;
        this.author = author;

        this.pricePerRental = BigDecimal.valueOf(0.0);
        this.stock = 0;
        this.isDeleted = false;
        this.bookStatus = BookStatus.PUBLISHED;
    }



@Id
@GeneratedValue(strategy = GenerationType.UUID)
@Column(name = "id", nullable = false)
private UUID id;

//@ManyToOne(fetch = FetchType.EAGER)
//    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> author;

@Column(name = "title", nullable = false)
private String title;

@Column(name = "pages", nullable = false)
private String pages;

@Column(name = "price", nullable = false)
private BigDecimal price;

@Column(name = "price_rental")//, nullable = false)
private BigDecimal pricePerRental;

@Column(name = "stock", nullable = false)
private Integer stock;

@CreationTimestamp
@Column(name = "createdAt", nullable = false)
private LocalDateTime createdAt;

@CreationTimestamp
@Column(name = "updateOn", nullable = false)
private LocalDateTime updateOn;

@Column(name = "isDeleted")
private Boolean isDeleted;

@Column(name = "book_status", nullable = false )
@Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

}
