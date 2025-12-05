package com.jonathan.Gestao_Vagas.modules.candidates.useCases;

import com.jonathan.Gestao_Vagas.modules.candidates.controllers.CandidateRepository;
import com.jonathan.Gestao_Vagas.modules.candidates.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;


    public ProfileCandidateResponseDTO execute(UUID idCandidate){
       var candidate =  this.candidateRepository.findById(idCandidate)
               .orElseThrow(()->{
                   throw new UsernameNotFoundException("User not found");
               });
       var candidateDTO = ProfileCandidateResponseDTO.builder().description(candidate.getDescription())
               .username(candidate.getUsername()).email(candidate.getEmail()).name(candidate.getName())
               .id(String.valueOf(candidate.getId())).build();

       return candidateDTO;

    }
}
