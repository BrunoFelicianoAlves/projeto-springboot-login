package projeto_spring_boot.projeto_spring.controller;

import java.security.Principal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import projeto_spring_boot.projeto_spring.model.Usuario;
import projeto_spring_boot.projeto_spring.repository.UsuarioRepository;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "/usuario-login";
    }

    @GetMapping("/cadastro")
    public String cadastroForm() {
        return "/usuario-cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRole("USER");
        usuarioRepository.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/private/home")
    public String home() {
        return "private/home";
    }
    
//    @GetMapping("/logout")
//    public String logout() {
//        return "/login";
//    }
    
//    @GetMapping("/private/perfil")
//    public String perfil() {
//    	return "private/perfil";
//    }
    
    @GetMapping("/private/perfil")
    public String perfil(@RequestParam(value = "senhaAlterada", required = false) String senhaAlterada,
                         Principal principal,
                         Model model) {
        // Só para exemplo: usando o username logado
        model.addAttribute("username", principal.getName());

        // Adiciona o parâmetro (true ou null) para o Thymeleaf
        model.addAttribute("senhaAlterada", senhaAlterada != null);

        return "private/perfil-altera-senha";
    }
    
    @GetMapping("/private/alterar-senha")
    public String alteraSenha() {
        return "private/perfil-altera-senha";
    }
}