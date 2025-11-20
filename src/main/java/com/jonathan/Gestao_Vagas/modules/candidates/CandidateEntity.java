package com.jonathan.Gestao_Vagas.modules.candidates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;


@Data //Adiciona todos os getters e setters dos atributos
@Entity(name = "candidate") //criação da tabela
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //Cria o id automaticamente quando inserido um dado
    private UUID id;

    private String name;

    @Pattern(regexp = "\\S+", message = "O campo do usarname não pode conter espaços.")
    private String username;

    @Email(message = "Favor, preencher o campo com um email válido.") //Essa anotation garante que o email seja no formato certo!
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres") //Restringe o tamanho do email
    private String password;

    private String curriculum;
    private String description;

    @CreationTimestamp //pega a data e hora exata da criação do dado no banco de dados
    private LocalDateTime creatdAt;

}
