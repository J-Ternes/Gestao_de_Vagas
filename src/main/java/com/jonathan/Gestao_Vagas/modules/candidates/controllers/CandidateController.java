package com.jonathan.Gestao_Vagas.modules.candidates.controllers;


import com.jonathan.Gestao_Vagas.modules.candidates.entities.CandidateEntity;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.CreateCandidateUseCase;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController //Classes controladoras e todas as respostas sejam em JSON
@RequestMapping("/candidate")
public class CandidateController {
//O @Valid faz com que as informações do candidato sejam validadas.
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

        @PostMapping("/")
        public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
            try {
               var result = this.createCandidateUseCase.execute(candidateEntity);
               return ResponseEntity.ok().body(result);

            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());

            }
        }
        @GetMapping("/")
        public ResponseEntity<Object> get(HttpServletRequest request){
            var idCandidate = request.getAttribute("candidate_id");
           try{
               var profile = profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));

               return ResponseEntity.ok().body(profile);

           }catch (Exception e){
               return ResponseEntity.badRequest().body(e.getMessage());
           }

        }

    }


