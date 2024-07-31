package spring.boot.week5day4ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week5day4ex.Api.ApiNewsArticle;
import spring.boot.week5day4ex.Model.NewsArticle;
import spring.boot.week5day4ex.Service.ServiceNewsArticle;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/newsArticle")
@RequiredArgsConstructor
public class ControllerNewsArticle {

    private final ServiceNewsArticle serviceNewsArticle;


    @GetMapping("/get")
    public ResponseEntity getNewsArticle() {
        ArrayList<NewsArticle> newsArticles = serviceNewsArticle.getNewsArticles();
        return ResponseEntity.status(200).body(newsArticles);
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@Valid @RequestBody NewsArticle newsArticle, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        serviceNewsArticle.addNewsArticles(newsArticle);
        return ResponseEntity.status(200).body(new ApiNewsArticle("News Article Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@Valid @RequestBody NewsArticle newsArticle, @PathVariable int id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (serviceNewsArticle.updateNewsArticles(newsArticle, id)) {
            return ResponseEntity.status(200).body(new ApiNewsArticle("News Article is Updated"));
        } else return ResponseEntity.status(400).body(new ApiNewsArticle("News Article Not Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable int id) {
        if (serviceNewsArticle.deleteNewsArticle(id)) {
            return ResponseEntity.status(200).body(new ApiNewsArticle("News Article is removed"));
        } else return ResponseEntity.status(400).body(new ApiNewsArticle("News Article Not Deleted"));
    }

    @PutMapping("/publishNews/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable int id) {
        if (serviceNewsArticle.publishNewsArticle(id)) {
            return ResponseEntity.status(200).body(new ApiNewsArticle("News Article Published"));
        } else return ResponseEntity.status(400).body(new ApiNewsArticle("Not found Published news"));
    }

    @GetMapping("/getAllPublish")
    public ResponseEntity getAllpublishingNewsArticles() {
        if (serviceNewsArticle.getAllPublishedNewsArticles().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiNewsArticle("There is no News Article is Published"));
        } else return ResponseEntity.status(200).body(serviceNewsArticle.getAllPublishedNewsArticles());
    }
    @GetMapping("/getCategory/{category}")
    public ResponseEntity getNewsArticlesbyCategory(@PathVariable String category) {
        if (serviceNewsArticle.getNewsArticlesbyCategory(category).isEmpty()) {
            return ResponseEntity.status(400).body(new ApiNewsArticle("category not found"));
        }
        else return ResponseEntity.status(200).body(serviceNewsArticle.getNewsArticlesbyCategory(category));
    }
}
