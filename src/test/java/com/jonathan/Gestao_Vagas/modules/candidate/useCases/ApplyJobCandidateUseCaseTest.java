package com.jonathan.Gestao_Vagas.modules.candidate.useCases;

import com.jonathan.Gestao_Vagas.exceptions.JobNotFoundException;
import com.jonathan.Gestao_Vagas.exceptions.UserNotFoundException;
import com.jonathan.Gestao_Vagas.modules.candidates.controllers.CandidateRepository;
import com.jonathan.Gestao_Vagas.modules.candidates.entities.ApplyJobEntity;
import com.jonathan.Gestao_Vagas.modules.candidates.entities.CandidateEntity;
import com.jonathan.Gestao_Vagas.modules.candidates.repository.ApplyJobRepository;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.ApplyJobCandidateUseCase;
import com.jonathan.Gestao_Vagas.modules.company.entities.JobEntity;
import com.jonathan.Gestao_Vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks //O mockito realiza uma simulação
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to aply job with candidate not found")
    public void should_not_be_able_to_aply_job_with_candidate_not_found(){
        try{
            applyJobCandidateUseCase.execute(null,null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class); //Para assegurar que a instancia passada seja essa
        }
    }

    @Test
    public void should_not_be_able_to_aply_job_with_job_not_found(){

        var idCandidate = UUID.randomUUID();
        var candidade = new CandidateEntity();
        candidade.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidade));

        try{
            applyJobCandidateUseCase.execute(idCandidate,null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class); //Para assegurar que a instancia passada seja essa
        }
    }

    @Test
    public void should_be_able_to_create_a_new_apply_job(){
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate,idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }

}
