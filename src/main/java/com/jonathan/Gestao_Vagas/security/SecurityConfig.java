package com.jonathan.Gestao_Vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Spring irá gerenciar essa classe de configuração
public class SecurityConfig {

    @Bean
        //Sobrescreve o metodo que ja existe na camada original por esse
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/candidate/").permitAll() //Permite autorização total nessa rota
                            .requestMatchers("/company/").permitAll(); //Permite autorização total nessa rota
                    auth.anyRequest().authenticated(); //Qualquer outra rota, exceto a de cima, precisa de autenticação
                });
        return http.build();
    }
}
