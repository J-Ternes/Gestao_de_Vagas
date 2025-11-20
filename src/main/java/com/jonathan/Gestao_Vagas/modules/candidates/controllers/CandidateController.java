package com.jonathan.Gestao_Vagas.modules.candidates.controllers;


import com.jonathan.Gestao_Vagas.exceptions.UserFoundException;
import com.jonathan.Gestao_Vagas.modules.candidates.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Classes controladoras e todas as respostas sejam em JSON
@RequestMapping("/candidate")
public class CandidateController {
//O @Valid faz com que as informações do candidato sejam validadas.

    @Autowired //Essa anotation faz com que o Spring gerencie
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) ->{
                    throw new UserFoundException();

                } );
        return this.candidateRepository.save(candidateEntity);
    }

}
