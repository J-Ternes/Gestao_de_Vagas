package com.jonathan.Gestao_Vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration //Spring irá gerenciar essa classe de configuração

public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;
    @Bean//Sobrescreve o metodo que ja existe na camada original por esse
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/candidate/").permitAll() //Permite autorização total nessa rota
                            .requestMatchers("/company/").permitAll()
                            .requestMatchers("/company/auth").permitAll() //Permite autorização total nessa rota
                            .requestMatchers("/candidate/auth").permitAll();
                    auth.anyRequest().authenticated(); //Qualquer outra rota, exceto as de cima, precisa de autenticação
                }).addFilterBefore(securityCandidateFilter,BasicAuthenticationFilter.class)
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //Criptografando a senha
    }
}
