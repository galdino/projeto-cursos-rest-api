package com.galdino.cursos.exception;

public class IdNaoValidoServiceException extends RuntimeException {
	private static final long serialVersionUID = -5972250608515834746L;

	public IdNaoValidoServiceException(String message) {
		super(message);
	}
}
