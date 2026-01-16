package com.jonathan.Gestao_Vagas.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Vaga para NodeJs")
    private String description;
    @Schema(example = "GymPass, VR, VA, Dayoff, Plano de Saúde apartamento")
    private String beneficios;

    @NotBlank(message = "Esse campo é obrigatório!")
    @Schema(example = "Senior")
    private String level;

    @ManyToOne() //Tipo de relacionamento: Muitos Jobs para uma empresa
    @JoinColumn(name = "company_id", insertable = false, updatable = false) //Chave estrangeira
    private CompanyEntity companyEntity;


   @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAT;

}
