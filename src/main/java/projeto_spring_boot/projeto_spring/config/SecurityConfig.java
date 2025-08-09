package projeto_spring_boot.projeto_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import projeto_spring_boot.projeto_spring.repository.UsuarioRepository;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/login", "/cadastro", "/css/**", "/h2-console/**").permitAll()
	            .requestMatchers("/private/**").authenticated()
	            .anyRequest().authenticated()
	        )
	        .formLogin(login -> login
	            .loginPage("/login")
	            .defaultSuccessUrl("/private/home", true)
	            .permitAll()
	        )
		    //  .logout(logout -> logout.permitAll())
		    //  .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout")
	            .invalidateHttpSession(true) // invalida a sessão
	            .clearAuthentication(true)   // limpa autenticação
	            .permitAll()
	        )
	        .headers(headers -> headers
	            .frameOptions(frame -> frame.sameOrigin())
	            .cacheControl(cache -> cache.disable()) // desabilita cache do navegador
	        )
	        .csrf(csrf -> csrf
	            .ignoringRequestMatchers("/h2-console/**")
	        );

	    return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> usuarioRepository.findByUsername(username)
            .<org.springframework.security.core.userdetails.UserDetails>map(user -> 
                org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build()
            )
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}