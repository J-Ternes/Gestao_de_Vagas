package com.jonathan.Gestao_Vagas.modules.candidates.controllers;


import com.jonathan.Gestao_Vagas.exceptions.UserFoundException;
import com.jonathan.Gestao_Vagas.modules.candidates.CandidateEntity;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Classes controladoras e todas as respostas sejam em JSON
@RequestMapping("/candidate")
public class CandidateController {
//O @Valid faz com que as informações do candidato sejam validadas.
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
        @PostMapping("/")
        public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
            try {
               var result = this.createCandidateUseCase.execute(candidateEntity);
               return ResponseEntity.ok().body(result);

            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());

            }
        }

    }


