package com.jonathan.Gestao_Vagas.modules.candidates;

import lombok.Data;

import java.util.UUID;


@Data //Adiciona todos os getters e setters dos atributos
public class CandidateEntity {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String curriculum;
    private String description;

}
