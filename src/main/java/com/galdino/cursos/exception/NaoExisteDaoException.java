package com.galdino.cursos.exception;

public class NaoExisteDaoException extends RuntimeException {
	
	private static final long serialVersionUID = 6018530798519549377L;

	public NaoExisteDaoException(String message) {
		super(message);
	}

}
