package com.jonathan.Gestao_Vagas.modules.company.UseCase;

import com.jonathan.Gestao_Vagas.exceptions.UserFoundException;
import com.jonathan.Gestao_Vagas.modules.company.entities.CompanyEntity;
import com.jonathan.Gestao_Vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) ->{
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(companyEntity.getPassword()); //Enviando a senha para ser criptografada
        companyEntity.setPassword(password); //Alterando a senha
        return this.companyRepository.save(companyEntity); // Salvado os dados da company
    }
}
