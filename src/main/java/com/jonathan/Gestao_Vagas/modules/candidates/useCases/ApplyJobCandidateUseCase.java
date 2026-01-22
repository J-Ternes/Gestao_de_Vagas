package com.jonathan.Gestao_Vagas.modules.candidates.useCases;

import com.jonathan.Gestao_Vagas.exceptions.JobNotFoundException;
import com.jonathan.Gestao_Vagas.exceptions.UserNotFoundException;
import com.jonathan.Gestao_Vagas.modules.candidates.controllers.CandidateRepository;
import com.jonathan.Gestao_Vagas.modules.candidates.entities.ApplyJobEntity;
import com.jonathan.Gestao_Vagas.modules.candidates.repository.ApplyJobRepository;
import com.jonathan.Gestao_Vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){
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
        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();
        var applyJobSave = applyJobRepository.save(applyJob);
        return applyJobSave;

    }
}
