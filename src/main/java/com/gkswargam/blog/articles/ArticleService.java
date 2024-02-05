package com.gkswargam.blog.articles;

import com.gkswargam.blog.articles.dtos.CreateArticleRequest;
import com.gkswargam.blog.articles.dtos.UpdateArticleRequest;
import com.gkswargam.blog.users.UserService;
import com.gkswargam.blog.users.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticlesRepository articlesRepository;
    private final UsersRepository usersRepository;

    public ArticleService(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        return articlesRepository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
    }

    public ArticleEntity createArticle(CreateArticleRequest request, Long authorId) {
        var author = usersRepository.findById(authorId).orElseThrow(() -> new UserService.UserNotFoundException(authorId));

        return articlesRepository.save(ArticleEntity.builder()
                .title(request.getTitle())
                .slug(request.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                .body(request.getBody())
                .subTitle(request.getSubTitle())
                .author(author)
                .build());
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest request) {
        var article = articlesRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));

        if(request.getTitle() != null) {
            article.setTitle(request.getTitle());
            article.setSlug(request.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }

        if(request.getBody() != null) {
            article.setBody(request.getBody());
        }

        if(request.getSubTitle() != null) {
            article.setSubTitle(request.getSubTitle());
        }

        return articlesRepository.save(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("Article with slug: " + slug + " not found");
        }

        public ArticleNotFoundException(Long articleId) {
            super("Article with id: " + articleId + " not found");
        }
    }
}
