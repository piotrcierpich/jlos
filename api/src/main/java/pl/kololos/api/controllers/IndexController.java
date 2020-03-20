package pl.kololos.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kololos.api.utils.ResourceFileReader;

@Controller
public class IndexController {
    private final String longText;
    private final String shortText;

    public IndexController() {
        longText = ResourceFileReader.readFileContent("/longText.txt");
        shortText = ResourceFileReader.readFileContent("/shortText.txt");
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name + " and who else");
        return "greeting";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mainPageText", longText);
        model.addAttribute("shortPageText", shortText);
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