package com.jonathan.Gestao_Vagas.modules.candidates;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;


@Data //Adiciona todos os getters e setters dos atributos
public class CandidateEntity {
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

}
