package pl.kololos.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name + " and who else");
        return "greeting";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/aktualnosci")
    public String news() {
        return "listFull";
    }

    @GetMapping("/uchwaly")
    public String resolutions() {
        return "article";
    }

    @GetMapping("/historia")
    public String history() {
        return "article";
    }

    @GetMapping("/galeria")
    public String gallery() {
        return "list";
    }

    @GetMapping("/kontakt")
    public String contact() {
        return "article";
    }
}