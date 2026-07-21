package com.example.mslibrarymanagementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private Integer publishedYear;
    private Long authorId;
}