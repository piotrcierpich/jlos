package pl.kololos.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.kololos.api.models.admin.Article;
import pl.kololos.api.models.admin.ArticleUpdate;
import pl.kololos.api.models.admin.Articles;
import pl.kololos.api.models.admin.Pagination;
import pl.kololos.api.services.AdminService;
import pl.kololos.api.services.ArticlesPaginationService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final ArticlesPaginationService articlesPaginationService;


    @GetMapping()
    public String index() {
        return "admin";
    }

    @GetMapping("/aktualnosci")
    public String news(@RequestParam(value = "page", required = false) Integer pageParam, Model model) {
        int page = pageParam == null ? 0 : pageParam;
        Articles articles = adminService.getNews(page);
        model.addAttribute("articles", articles);
        Pagination pagination = articlesPaginationService.getForArticles(page);
        model.addAttribute("pagination", pagination);
        return "adminList";
    }

    @GetMapping("/aktualnosci/{id}")
    public String newsArticle(@PathVariable Long id, Model model) {
        Optional<Article> article = adminService.getArticleById(id);
        if(article.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find article");
        }
        model.addAttribute(article.get());
        return "adminArticle";
    }

    //TODO change id to actual generated link
    @PostMapping("/aktualnosci/{id}")
    public String updateNewsArticle(@PathVariable Long id, @ModelAttribute ArticleUpdate articleUpdate, Model model) {
        Article updatedArticle = adminService.updateArticle(id, articleUpdate);
        model.addAttribute(updatedArticle);
        return "adminArticle";
    }

    @GetMapping("/aktualnosci/nowa")
    public String createArticle(Model model) {
        Article newArticle = new Article();
        model.addAttribute(newArticle);
        return "adminArticle";
    }

    @PostMapping("/aktualnosci/nowa")
    public String createArticle(@ModelAttribute ArticleUpdate articleUpdate, ModelMap model) {
        Article article = adminService.saveArticle(articleUpdate);
        model.addAttribute(new Article());
        return "adminArticle";
    }

    @GetMapping("/galeria")
    public String gallery() {
        return "adminList";
    }

    @GetMapping("/{articleKind}")
    public String pageArticle(@PathVariable String articleKind, Model model) {
        Article article = adminService.getArticleByKind(articleKind);
        model.addAttribute(article);
        return "adminArticle";
    }

    @PostMapping("/{articleKind}")
    public String updatePageArticle(@PathVariable String articleKind, @ModelAttribute ArticleUpdate articleUpdate, Model model) {
        Article pageArticle = adminService.getArticleByKind(articleKind);
        model.addAttribute(pageArticle);
        return "adminArticle";
    }
}
