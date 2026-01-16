package com.jonathan.Gestao_Vagas.modules.candidates.controllers;


import com.jonathan.Gestao_Vagas.modules.candidates.dto.ProfileCandidateResponseDTO;
import com.jonathan.Gestao_Vagas.modules.candidates.entities.CandidateEntity;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.CreateCandidateUseCase;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.ListAllJobsByFilterUseCase;
import com.jonathan.Gestao_Vagas.modules.candidates.useCases.ProfileCandidateUseCase;
import com.jonathan.Gestao_Vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController //Classes controladoras e todas as respostas sejam em JSON
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações sobre o candidato")
public class CandidateController {
//O @Valid faz com que as informações do candidato sejam validadas.
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

        @PostMapping("/")
        @Operation(summary = "Cadastro de candidato",
                description = "Essa função é responsável por cadastrar um candidato")
        @ApiResponses({
                @ApiResponse(responseCode = "200", content = {
                        @Content(schema = @Schema(implementation = CandidateEntity.class))
                }),
                @ApiResponse(responseCode = "400", description = "Usuário já existe")
        })
        public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
            try {
               var result = this.createCandidateUseCase.execute(candidateEntity);
               return ResponseEntity.ok().body(result);

            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());

            }
        }
        @GetMapping("/")
        @PreAuthorize("hasRole('CANDIDATE')")
        @Operation(summary = "Perfil do candidato",
                description = "Essa função é responsável por buscar as informações do candidato")
        @SecurityRequirement(name = "jwt_auth")
        @ApiResponses({
                @ApiResponse(responseCode = "200", content = {
                        @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
                }),
                @ApiResponse(responseCode = "400", description = "User not found")
        })
        public ResponseEntity<Object> get(HttpServletRequest request){
            var idCandidate = request.getAttribute("candidate_id");
           try{
               var profile = profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));

               return ResponseEntity.ok().body(profile);

           }catch (Exception e){
               return ResponseEntity.badRequest().body(e.getMessage());
           }

        }

        @GetMapping("/job")
        @PreAuthorize("hasRole('CANDIDATE')")
        @Operation(summary = "Listagem de vagas disponíveis para o candicato", description = "Essa função é responsável por listas todas as vagas disponíveis atraves de um filtro")
        @ApiResponses({
                @ApiResponse(responseCode = "200", content = {
                        @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
                })
        })
        @SecurityRequirement(name = "jwt_auth") //Adicionando a camada de segurança construída no Aplication.java
        public List<JobEntity> findJobNyFilter(@RequestParam String filter){
            return this.listAllJobsByFilterUseCase.execute(filter); //Realiza uma busca utilizando um filtro
        }


    }


