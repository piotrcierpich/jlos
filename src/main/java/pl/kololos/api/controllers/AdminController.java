package pl.kololos.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.kololos.api.models.admin.Post;
import pl.kololos.api.models.admin.ContentUpdate;
import pl.kololos.api.models.admin.Posts;
import pl.kololos.api.models.admin.Pagination;
import pl.kololos.api.services.AdminService;
import pl.kololos.api.services.PostsPaginationService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final PostsPaginationService postsPaginationService;


    @GetMapping()
    public String index() {
        return "admin";
    }

    @GetMapping("/aktualnosci")
    public String news(@RequestParam(value = "page", required = false) Integer pageParam, Model model) {
        int page = pageParam == null ? 0 : pageParam;
        Posts posts = adminService.getPosts(page);
        model.addAttribute("posts", posts);
        Pagination pagination = postsPaginationService.getForArticles(page);
        model.addAttribute("pagination", pagination);
        return "adminList";
    }

    @GetMapping("/aktualnosci/{id}")
    public String newsArticle(@PathVariable Long id, Model model) {
        Optional<Post> article = adminService.getPostById(id);
        if(article.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find article");
        }
        model.addAttribute(article.get());
        return "adminArticle";
    }

    //TODO change id to actual generated link
    @PostMapping("/aktualnosci/{id}")
    public String updateNewsArticle(@PathVariable Long id, @ModelAttribute ContentUpdate contentUpdate, Model model) {
        Post updatedPost = adminService.updatePost(id, contentUpdate);
        model.addAttribute(updatedPost);
        return "adminArticle";
    }

    @GetMapping("/aktualnosci/nowa")
    public String createArticle(Model model) {
        Post newPost = new Post();
        model.addAttribute(newPost);
        return "adminArticle";
    }

    @PostMapping("/aktualnosci/nowa")
    public String createPost(@ModelAttribute ContentUpdate contentUpdate, ModelMap model) {
        Post post = adminService.savePost(contentUpdate);
        model.addAttribute(new Post());
        return "adminArticle";
    }

    @GetMapping("/galeria")
    public String gallery() {
        return "adminList";
    }

    @GetMapping("/{articleKind}")
    public String pageArticle(@PathVariable String articleKind, Model model) {
        Post post = adminService.getArticleByKind(articleKind);
        model.addAttribute(post);
        return "adminArticle";
    }

    @PostMapping("/{articleKind}")
    public String updatePageArticle(@PathVariable String articleKind, @ModelAttribute ContentUpdate contentUpdate, Model model) {
        Post pagePost = adminService.getArticleByKind(articleKind);
        model.addAttribute(pagePost);
        return "adminArticle";
    }
}