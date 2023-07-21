package pl.kololos.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kololos.api.models.Gallery;
import pl.kololos.api.models.Page;
import pl.kololos.api.models.admin.*;
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
        model.addAttribute("articles", posts);
        Pagination pagination = postsPaginationService.getForArticles(page);
        model.addAttribute("pagination", pagination);
        return "adminList";
    }

    @GetMapping("/aktualnosci/{id}")
    public String newsArticle(@PathVariable Long id, Model model) {
        Optional<Post> post = adminService.getPostById(id);
        if(post.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find article");
        }
        model.addAttribute(post.get());
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
    public String gallery(@RequestParam(value = "page", required = false) Integer pageParam, Model model) {
        int page = pageParam == null ? 0 : pageParam;
        Posts posts = adminService.getGalleries(page);
        model.addAttribute("articles", posts);
        Pagination pagination = postsPaginationService.getForArticles(page);
        model.addAttribute("pagination", pagination);
        return "adminList";
    }

    @GetMapping("/galeria/nowa")
    public String createGallery(Model model) {
        Post newPost = new Post();
        model.addAttribute(newPost);
        return "adminGalleryNew";
    }

    @PostMapping("/galeria/nowa")
    public String createGallery(@ModelAttribute GalleryUpdate galleryUpdate, ModelMap model) {
        adminService.saveGallery(galleryUpdate);
        return "redirect:/admin/galeria";
    }

    @GetMapping("/galeria/{id}")
    public String galleryById(
            @PathVariable Long id,
            Model model) {
        GalleryInfo galleryById = adminService.getGalleryById(id);
        model.addAttribute("gallery", galleryById);
        return "adminGalleryImagesList";
    }

    @PostMapping("/galeria/{id}")
    public String galleryFileUpload(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/admin/galeria/" + id;
    }

    @GetMapping("/{articleKind}")
    public String pageArticle(@PathVariable String articleKind, Model model) {
        Optional<Page> page = adminService.getPageByKind(articleKind);
        if(page.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find page");
        }
        model.addAttribute("post", page.get());
        return "adminArticle";
    }

    @PostMapping("/{articleKind}")
    public String updatePageArticle(@PathVariable String articleKind, @ModelAttribute ContentUpdate contentUpdate, Model model) {
        Page page = adminService.updatePage(articleKind, contentUpdate);
        model.addAttribute("post", page);
        return "adminArticle";
    }
}
