package com.jonathan.Gestao_Vagas.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //Essa anotation cria um construtor com argumento
public class ErrorMessageDTO {

    private String message;
    private String field;
}
