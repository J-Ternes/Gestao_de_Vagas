package com.jonathan.Gestao_Vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(){
        super("Job not found!");
    }
}
