package com.galdino.cursos.resource.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.galdino.cursos.exception.IdNaoValidoServiceException;
import com.galdino.cursos.exception.NaoExisteDaoException;
import com.galdino.cursos.model.DetalheErro;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({IdNaoValidoServiceException.class})
	public ResponseEntity<Object> idInvalido(IdNaoValidoServiceException ex, WebRequest request) {
		return handleExceptionInternal(ex, 
				DetalheErro.builder()
					.addDetalhe("A requisição possui valores inválidos, vazios ou nulos.")
					.addErro(ex.getMessage())
					.addStatus(HttpStatus.BAD_REQUEST)
					.addHttpMethod(getHttpMethod(request))
					.addPath(getPath(request))
					.build(), 
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
	public ResponseEntity<Object> constraintViolada(org.hibernate.exception.ConstraintViolationException ex, WebRequest request) {
		
		return handleExceptionInternal(ex,
				DetalheErro.builder()
					.addDetalhe("Constraint violada: " + ex.getConstraintName())
					.addErro(ex.getMessage())
					.addStatus(HttpStatus.CONFLICT)
					.addHttpMethod(getHttpMethod(request))
					.addPath(getPath(request))
					.build(), 
				new HttpHeaders(), HttpStatus.CONFLICT, request);
		
	}
	
	@ExceptionHandler({org.springframework.dao.DataIntegrityViolationException.class})
	public ResponseEntity<Object> propriedadeNula(org.springframework.dao.DataIntegrityViolationException ex, WebRequest request) {
		
		return handleExceptionInternal(ex,
				DetalheErro.builder()
					.addDetalhe("A requisição possui valores inválidos, vazios ou nulos.")
					.addErro(ex.getMessage())
					.addStatus(HttpStatus.BAD_REQUEST)
					.addHttpMethod(getHttpMethod(request))
					.addPath(getPath(request))
					.build(), 
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({NaoExisteDaoException.class})
	public ResponseEntity<Object> entidadeNaoEncontrada(NaoExisteDaoException ex, WebRequest request) {
		
		return handleExceptionInternal(ex, 
				DetalheErro.builder()
						.addDetalhe("Recurso não encontrado na base de dados.")
						.addErro(ex.getMessage())
						.addStatus(HttpStatus.NOT_FOUND)
						.addHttpMethod(getHttpMethod(request))
						.addPath(getPath(request))
						.build(),
					new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}

	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class })
	public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request) {

		return handleExceptionInternal(ex,
				DetalheErro.builder()
						.addDetalhe("Uma exceção foi lançada!")
						.addErro(ex.getMessage())
						.addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.addHttpMethod(getHttpMethod(request))
						.addPath(getPath(request))
						.build(),
				new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI();
	}

	private String getHttpMethod(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getMethod();
	}

}
