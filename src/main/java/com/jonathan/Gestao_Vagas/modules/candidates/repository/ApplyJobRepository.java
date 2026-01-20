package com.jonathan.Gestao_Vagas.modules.candidates.repository;

import com.jonathan.Gestao_Vagas.modules.candidates.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
