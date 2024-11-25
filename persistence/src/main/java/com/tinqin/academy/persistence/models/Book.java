package com.tinqin.academy.persistence.models;

import com.tinqin.academy.persistence.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
public class Book {

@Id
@GeneratedValue(strategy = GenerationType.UUID)
@Column(name = "id", nullable = false)
private UUID id;

@ManyToOne(fetch = FetchType.EAGER)
    private Author author;

@Column(name = "title", nullable = false)
private String title;

@Column(name = "pages", nullable = false)
private String pages;

@Column(name = "price", nullable = false)
private BigDecimal price;

@Column(name = "price_rental", nullable = false)
private Double pricePerRental;

@Column(name = "stock", nullable = false)
private Integer stock;

@Column(name = "createdAt", nullable = false)
private LocalDateTime createdAt;

@Column(name = "updateOn", nullable = false)
private LocalDateTime updateOn;

@Column(name = "isDeleted")
private Boolean isDeleted;

@Column(name = "book_status", nullable = false)
@Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

}
