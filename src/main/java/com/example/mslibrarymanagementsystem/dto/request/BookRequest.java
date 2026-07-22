package com.example.mslibrarymanagementsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(
            regexp = "^(97(8|9))?\\d{9}(\\d|X)$",
            message = "Invalid ISBN format"
    )
    private String isbn;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100)
    private String title;

    @NotNull(message = "Published year is required")
    @Positive(message = "Published year must be positive")
    private Integer publishedYear;

    @NotBlank(message = "Genre cannot be blank")
    @Size(max = 50)
    private String genre;

    @NotNull(message = "Author id is required")
    private Long authorId;
}