package com.jonathan.Gestao_Vagas.modules.candidates.useCases;

import com.jonathan.Gestao_Vagas.exceptions.JobNotFoundException;
import com.jonathan.Gestao_Vagas.exceptions.UserNotFoundException;
import com.jonathan.Gestao_Vagas.modules.candidates.controllers.CandidateRepository;
import com.jonathan.Gestao_Vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute(UUID idCandidate, UUID idJob){
        //Validar a existência do candidato
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(()->{
                   throw new UserNotFoundException();
                });
        //Validar a existência da vaga
        this.jobRepository.findById(idJob)
                .orElseThrow(()->{
                   throw new JobNotFoundException();
                });

        //Candidato se inscreve na vaga

    }
}
