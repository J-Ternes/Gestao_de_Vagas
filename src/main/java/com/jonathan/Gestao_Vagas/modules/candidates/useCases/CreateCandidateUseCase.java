package com.jonathan.Gestao_Vagas.modules.candidates.useCases;

import com.jonathan.Gestao_Vagas.exceptions.UserFoundException;
import com.jonathan.Gestao_Vagas.modules.candidates.CandidateEntity;
import com.jonathan.Gestao_Vagas.modules.candidates.controllers.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Significa que é a camada de serviço, regra de negocio
public class CreateCandidateUseCase {
    @Autowired //Essa anotation faz com que o Spring gerencie
    private CandidateRepository candidateRepository;

    public CandidateEntity execute (CandidateEntity candidateEntity){
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();

                });
        return this.candidateRepository.save(candidateEntity);
    }


    }

