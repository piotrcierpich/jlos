package pl.kololos.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kololos.api.models.Page;
import pl.kololos.api.services.PageService;

@Controller
public class IndexController {
    private final PageService pageService;

    public IndexController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name + " and who else");
        return "greeting";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("index", pageService.getIndex());
        return "index";
    }

    @GetMapping("/uchwaly")
    public String resolutions(Model model) {
        Page resolutions = pageService.getPageByKind("uchwaly");
        model.addAttribute("article", resolutions);
        return "article";
    }

    @GetMapping("/historia")
    public String history(Model model) {
        Page history = pageService.getPageByKind("historia");
        model.addAttribute("article", history);
        return "article";
    }

    @GetMapping("/galeria")
    public String gallery(Model model, @RequestParam(required = false) Integer page) {
        model.addAttribute("items", pageService.getGalleries(page));
        return "list";
    }

    @GetMapping("/kontakt")
    public String contact(Model model) {
        Page contact = pageService.getPageByKind("kontakt");
        model.addAttribute("article", contact);
        return "article";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}