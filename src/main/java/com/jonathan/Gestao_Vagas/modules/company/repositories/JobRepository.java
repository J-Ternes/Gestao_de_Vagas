package com.jonathan.Gestao_Vagas.modules.company.repositories;

import com.jonathan.Gestao_Vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter); //Select * from job where description like %filter% (Procurar a description em qualquer lugar)

}
