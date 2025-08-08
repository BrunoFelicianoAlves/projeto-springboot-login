package projeto_spring_boot.projeto_spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import projeto_spring_boot.projeto_spring.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
