package projeto_spring_boot.projeto_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/gerenciar")
    public String gerenciarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll());
        return "/private/servicos/gerenciar-filmes";
    }

    @GetMapping("/editar/{id}")
    public String editarFilmeForm(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        model.addAttribute("filme", filme);
        return "private/servicos/form-filmes"; // o mesmo formulário de cadastro
    }

    @PostMapping("/editar/{id}")
    public String salvarEdicao(@PathVariable Long id, @ModelAttribute Filme filmeAtualizado) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setImagemUrl(filmeAtualizado.getImagemUrl());
        filme.setVideoUrl(filmeAtualizado.getVideoUrl());

        filmeRepository.save(filme);
        return "redirect:/filmes/gerenciar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirFilme(@PathVariable Long id) {
        filmeRepository.deleteById(id);
        return "redirect:/filmes/gerenciar";
    }

}