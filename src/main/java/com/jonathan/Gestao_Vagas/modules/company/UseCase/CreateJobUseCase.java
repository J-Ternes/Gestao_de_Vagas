package com.jonathan.Gestao_Vagas.modules.company.UseCase;


import com.jonathan.Gestao_Vagas.exceptions.CompanyNotFoundException;
import com.jonathan.Gestao_Vagas.modules.company.entities.JobEntity;
import com.jonathan.Gestao_Vagas.modules.company.repositories.CompanyRepository;
import com.jonathan.Gestao_Vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;


    public JobEntity execute(JobEntity jobEntity){

        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(()->{
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(jobEntity);
    }
}
