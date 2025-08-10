package projeto_spring_boot.projeto_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto_spring_boot.projeto_spring.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
