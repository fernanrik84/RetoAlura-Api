package com.alura.Exception;

public class RegistroDuplicadoException extends RuntimeException{

	private static final long serialVersionUID = 5035030137612307535L;
	
	public RegistroDuplicadoException(String message) {
        super(message);
    }

}
