package com.jonathan.Gestao_Vagas.modules.company.controllers;


import com.jonathan.Gestao_Vagas.modules.company.UseCase.CreateJobUseCase;
import com.jonathan.Gestao_Vagas.modules.company.dto.CreateJobDTO;
import com.jonathan.Gestao_Vagas.modules.company.entities.JobEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')") //apenas quem tem a role company poderá acessar (Usa-se uppercase por padrao)
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        var idCompany = request.getAttribute("companyId");
        //jobEntity.setCompanyId(UUID.fromString(idCompany.toString()));

        var jobEntity = JobEntity.builder().beneficios(createJobDTO.getBenefits())
                .companyId(UUID.fromString(idCompany.toString()))
                .description(createJobDTO.getDescriptions())
                .level(createJobDTO.getLevel())
                .build();

        return this.createJobUseCase.execute(jobEntity);

    }

}
