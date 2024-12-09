package com.tinqin.academy.core.converters;

import com.tinqin.academy.api.models.BookModel;
import com.tinqin.academy.persistence.models.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookModel implements Converter<Book, BookModel> {

    @Override
    public BookModel convert(Book source) {
        return BookModel
                .builder()
                .id(source.getId())
                .title(source.getTitle())
                .pages(Integer.valueOf(source.getPages()))
                .price(source.getPrice().doubleValue())
                .createdAt(source.getCreatedAt())
                .stock(source.getStock())
                .build();
    }
}
