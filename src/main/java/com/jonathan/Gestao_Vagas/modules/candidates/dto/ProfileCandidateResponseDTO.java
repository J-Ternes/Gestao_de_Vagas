package com.jonathan.Gestao_Vagas.modules.candidates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    private  String description;
    private String username;
    private String email;
    private String id;
    private String name;
}
