package com.jonathan.Gestao_Vagas.modules.company.UseCase;

import com.jonathan.Gestao_Vagas.exceptions.UserFoundException;
import com.jonathan.Gestao_Vagas.modules.company.entities.CompanyEntity;
import com.jonathan.Gestao_Vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) ->{
                    throw new UserFoundException();
                });
        return this.companyRepository.save(companyEntity);
    }
}
