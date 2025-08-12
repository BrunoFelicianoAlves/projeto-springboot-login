package projeto_spring_boot.projeto_spring.controller;

import java.security.Principal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projeto_spring_boot.projeto_spring.model.Usuario;
import projeto_spring_boot.projeto_spring.repository.UsuarioRepository;

@Controller
public class UsuarioAlteraSenhaController {

	 private final UsuarioRepository usuarioRepository;
	 private final PasswordEncoder passwordEncoder;

//	private UsuarioRepository usuarioRepository;
//	private PasswordEncoder passwordEncoder;

//	 Usuario usuario = new Usuario();

	// Injeção via construtor
	public UsuarioAlteraSenhaController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/private/alterar-senha")
	public String alterarSenha(
	        @RequestParam("senha-atual") String senhaAtual,
	        @RequestParam("nova-senha") String novaSenha,
	        @RequestParam("confirma-senha") String confirmaSenha,
	        Principal principal,
	        RedirectAttributes redirectAttributes) {

	    // 1. Busca o usuário logado pelo username
	    Usuario usuario = usuarioRepository.findByUsername(principal.getName())
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

	    // 2. Verifica se a senha atual está correta
	    if (!passwordEncoder.matches(senhaAtual, usuario.getPassword())) {
	    	redirectAttributes.addFlashAttribute("erro", "Senha atual incorreta");
	    	return "redirect:/private/perfil?senhaAlterada=false";
//	        throw new RuntimeException("Senha atual incorreta");
	    }

	    // 3. Verifica se as senhas novas coincidem
	    if (!novaSenha.equals(confirmaSenha)) {
	    	redirectAttributes.addFlashAttribute("erro", "Nova senha e confirmação não coincidem");
	    	return "redirect:/private/perfil?senhaAlterada=false";
//	        throw new RuntimeException("Nova senha e confirmação não coincidem");
	    }

	    // 4. Atualiza a senha no banco (criptografada)
	    usuario.setPassword(passwordEncoder.encode(novaSenha));
	    usuarioRepository.save(usuario);

	    // 5. Redireciona com mensagem de sucesso
	    redirectAttributes.addFlashAttribute("sucesso", "Senha alterada com sucesso!");
	    //return "redirect:/private/perfil";
	    return "redirect:/private/perfil?senhaAlterada=true";
	}

}