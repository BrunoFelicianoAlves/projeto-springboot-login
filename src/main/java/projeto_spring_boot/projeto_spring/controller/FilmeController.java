package projeto_spring_boot.projeto_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projeto_spring_boot.projeto_spring.model.Filme;
import projeto_spring_boot.projeto_spring.repository.FilmeRepository;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }
    
    @GetMapping("/novo")
    public String novoFilmeForm(Model model) {
        model.addAttribute("filme", new Filme());
        return "private/servicos/form-filmes";
    }

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        return "/private/servicos/lista-filmes"; // o HTML adaptado para listar dinamicamente
    }

    @PostMapping
    public String salvarFilme(@ModelAttribute Filme filme) {
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }
}