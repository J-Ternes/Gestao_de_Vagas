package com.jonathan.Gestao_Vagas.modules.candidates.controllers;


import com.jonathan.Gestao_Vagas.modules.candidates.CandidateEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Classes controladoras e todas as respostas sejam em JSON
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public void create(@RequestBody CandidateEntity candidateEntity){
        System.out.println("Candidato: " + candidateEntity.getEmail());
    }

}
