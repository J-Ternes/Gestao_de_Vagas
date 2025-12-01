package com.jonathan.Gestao_Vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Job")
@Data
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private String beneficios;

    @NotBlank(message = "Esse campo é obrigatório!")
    private String level;

    @ManyToOne() //Tipo de relacionamento: Muitos Jobs para uma empresa
    @JoinColumn(name = "company_id", insertable = false, updatable = false) //Chave estrangeira
    private CompanyEntity companyEntity;


   @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAT;

}
