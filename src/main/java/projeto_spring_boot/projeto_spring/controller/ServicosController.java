package projeto_spring_boot.projeto_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private/servicos")
public class ServicosController {

    @GetMapping("/videos")
    public String videos() {
        return "private/servicos/videos";
    }

    @GetMapping("/mobile")
    public String mobile() {
        return "private/servicos/mobile";
    }

    @GetMapping("/consultoria")
    public String consultoria() {
        return "private/servicos/consultoria";
    }
}