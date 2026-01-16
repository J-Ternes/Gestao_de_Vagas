package com.jonathan.Gestao_Vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "Pessoa desenvolvedora Java", requiredMode = Schema.RequiredMode.REQUIRED) // requiredeMode significa que é obrigatório o preenchimento
    private String descriptions;

    @Schema(example = "GymPass, VR, VA, Dayoff, Plano de Saúde apartamento", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "Estagiária", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;

}
