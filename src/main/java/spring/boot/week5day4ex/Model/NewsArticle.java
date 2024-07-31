package spring.boot.week5day4ex.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message = "Id should be not null")
    private int id;
    @NotEmpty
    @Size(max = 100,message = "Title shouldn't be empty and max characters 100")
    private String title;

    @NotEmpty
    @Size(min = 4,max = 20,message = "author shouldn't be empty and must be between 4 to 20 characters")
    private String author;

    @NotEmpty
    @Size(min = 200,message = "content shouldn't be empty and Must be more than 200 characters")
    private String content;
    @NotEmpty
    @Pattern(regexp = "^(politics|sports|technology)$" ,message = "category must be either 'politics' or 'sports' or 'technology'")
    private String category;

    @NotEmpty
    private String imageUrl;

    @AssertFalse
    private boolean isPublished;

    @NotNull
    @FutureOrPresent
    private LocalDate publishDate;

}
