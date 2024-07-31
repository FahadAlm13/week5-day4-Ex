package spring.boot.week5day4ex.Service;

import org.springframework.stereotype.Service;
import spring.boot.week5day4ex.Model.NewsArticle;

import java.util.ArrayList;

@Service
public class ServiceNewsArticle {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    public void addNewsArticles(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);
    }

    public boolean updateNewsArticles(NewsArticle newsArticle, int id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id) {
                newsArticles.set(i, newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle(int id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id) {
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publishNewsArticle(int id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id) {
                newsArticles.get(i).setPublished(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getAllPublishedNewsArticles() {
        ArrayList<NewsArticle> getPublish = new ArrayList<>();
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).isPublished()) {
                getPublish.add(newsArticles.get(i));
            }
        }
        return getPublish;

    }


    public ArrayList<NewsArticle> getNewsArticlesbyCategory(String category) {
        ArrayList<NewsArticle> getCategory = new ArrayList<>();
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getCategory().equals(category)) {
                getCategory.add(newsArticles.get(i));
            }
        }
        return getCategory;
    }
}



