package pl.kololos.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kololos.api.models.admin.Article;
import pl.kololos.api.models.admin.ArticleUpdate;
import pl.kololos.api.models.admin.Articles;
import pl.kololos.api.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public String index() {
        return "admin";
    }

    @GetMapping("/aktualnosci")
    public String news(Model model) {
        Articles articles = adminService.getNews(8);
        model.addAttribute("articles", articles);
        return "adminList";
    }

    @GetMapping("/aktualnosci/{id}")
    public String newsArticle(@PathVariable Integer id, Model model) {
        Article article = adminService.getArticleById(id);
        model.addAttribute(article);
        return "adminArticle";
    }

    //TODO change id to actual generated link
    @PostMapping("/aktualnosci/{id}")
    public String updateNewsArticle(@PathVariable Integer id, @ModelAttribute ArticleUpdate articleUpdate, Model model) {
        Article article = adminService.getArticleById(id);
        model.addAttribute(article);
        return "adminArticle";
    }

    @GetMapping("/aktualnosci/nowa")
    public String createArticle(Model model) {
        Article article = adminService.getArticleById(0);
        model.addAttribute(article);
        return "adminArticle";
    }

    @PostMapping("/aktualnosci/nowa")
    public String createArticle(@ModelAttribute ArticleUpdate articleUpdate, Model model) {
        Article article = adminService.saveArticle(articleUpdate);
        model.addAttribute(article);
        return "redirect:/aktualnosci/0";
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
