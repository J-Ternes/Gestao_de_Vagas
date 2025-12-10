package com.jonathan.Gestao_Vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseCompanyDTO {
    private String access_token;
    private Long expires_in;
}
