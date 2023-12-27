package com.glsi.xpress.Entity;

import com.glsi.xpress.Entity.Enum.BookCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private LocalDateTime publishmentDate;
    private Long isbn;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    @Lob
    private byte[] BookCover;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishmentDate=" + publishmentDate +
                ", isbn=" + isbn +
                ", quantity=" + quantity +
                ", category=" + category +
                ", BookCover=" + Arrays.toString(BookCover) +
                '}';
    }
}
