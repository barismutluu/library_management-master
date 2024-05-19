package org.example.librarymanagement.dto.request.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.librarymanagement.entities.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {

    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private int publicationYear;

    @NotNull
    @NotEmpty
    private int stock;

    @NotNull
    @NotEmpty
    private Author author;
}
