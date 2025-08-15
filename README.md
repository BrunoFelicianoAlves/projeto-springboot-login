Este projeto é um sistema simples de login, cadastro de usuários e gerenciamento de filmes
Utiliza Spring Boot, Spring Security, Thymeleaf e JPA.
Implementa autenticação com criptografia de senha e proteção de rotas.

📌 Funcionalidades

Login seguro com Spring Security.
Interface com Thymeleaf e Bootstrap.
Proteção de rotas para páginas privadas.
Alteração de senha para usuários logados.
Cadastrar, editar, excluir e listagem de filmes.

Banco de dados configurado para H2 (padrão) ou PostgreSQL.
Cadastro de usuário com senha criptografada usando BCrypt.

🛠 Tecnologias Utilizadas

Java 17
Spring Boot 3
Spring Security
Spring Data JPA
Thymeleaf
Bootstrap 5
H2 Database (banco em memória para testes)
PostgreSQL (opcional)

📂 Estrutura Básica

SecurityConfig → Configuração de segurança e autenticação.

AuthController → Controla login, cadastro e páginas privadas.

UsuarioAlteraSenhaController → Função para alterar senha do usuário.

FilmeController → Cadastro e listagem de filmes.

UsuarioRepository / FilmeRepository → Interfaces de acesso ao banco.

Filme / Usuario → Entidades do sistema.

🚀 Como Rodar o Projeto
1️⃣ Pré-requisitos

Java 17 instalado
Maven instalado
(Opcional) PostgreSQL rodando se quiser usar banco real

Clonar o repositório

git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git

# Porta padrão
server.port=8080

Ex: http://localhost:8080/login
