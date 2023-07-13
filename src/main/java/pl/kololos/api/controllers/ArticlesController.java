package pl.kololos.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kololos.api.services.PageService;

@Controller
@RequestMapping("aktualnosci")
@RequiredArgsConstructor
public class ArticlesController {
    private final PageService pageService;
    @GetMapping
    public String articles(Model model) {
        model.addAttribute("articles", pageService.getArticles());
        return "listFull";
    }

    @GetMapping("/{link}")
    public String articleByName(@PathVariable String link, Model model) {
        model.addAttribute("article", pageService.getArticleByLink(link));
        return "article";
    }
}
