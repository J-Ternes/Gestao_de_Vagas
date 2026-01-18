package com.jonathan.Gestao_Vagas.modules.candidate.useCases;

import com.jonathan.Gestao_Vagas.exceptions.UserNotFoundException;
import com.jonathan.Gestao_Vagas.modules.candidates.controllers.CandidateRepository;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.AplyJobCandidateUseCase;
import com.jonathan.Gestao_Vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class AplyJobCandidateUseCaseTest {

    @InjectMocks //O mockito realiza uma simulação
    private AplyJobCandidateUseCase aplyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to aply job with candidate not found")
    public void should_not_be_able_to_aply_job_with_candidate_not_found(){
        try{
            aplyJobCandidateUseCase.execute(null,null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class); //Para assegurar que a instancia passada seja essa
        }
    }

}
