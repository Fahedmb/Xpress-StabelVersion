package com.glsi.xpress.DTO;
import com.glsi.xpress.Entity.Enum.BookCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;

public class BookDTO {
    private String title;
    private String author;
    private LocalDateTime publishmentDate;
    private Long isbn;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    @Lob
    private byte[] BookCover;

}
