Este projeto é um sistema simples de login, cadastro de usuários e gerenciamento de filmes utilizando Spring Boot, Spring Security, Thymeleaf e JPA.
Ele implementa autenticação com criptografia de senha e proteção de rotas, além de permitir o cadastro e listagem de filmes.

📌 Funcionalidades

Cadastro de usuário com senha criptografada usando BCrypt.

Login seguro com Spring Security.

Proteção de rotas para páginas privadas.

Alteração de senha para usuários logados.

Cadastrar, editar, excluir e listagem de filmes.

Interface com Thymeleaf e Bootstrap.

Banco de dados configurado para H2 (padrão) ou PostgreSQL.

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
cd projeto-spring

# Porta padrão
server.port=8080

# Configuração H2 (banco em memória)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Console do H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuração JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

_________________________________________________________________
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

