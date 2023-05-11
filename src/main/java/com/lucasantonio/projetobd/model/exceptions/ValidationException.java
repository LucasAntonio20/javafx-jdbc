package com.lucasantonio.projetobd.model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{

    private Map<String, String> erros = new HashMap<>();

    public ValidationException(String msg){
        super(msg);
    }

    public Map<String, String> getErros() {
        return erros;
    }

    public void addError(String fieldName, String errorNames){
        erros.put(fieldName, errorNames);
    }
}
