package com.galdino.cursos.service;

import java.util.Date;
import java.util.List;

import com.galdino.cursos.model.Curso;

public interface CursoService {
	
	void save(Curso curso);
	
	void update(Long id, Curso curso);
	
	void delete(Long id);
	
	Curso findById(Long id);
	
	List<Curso> findAll();
	
	Curso updateDataInicio(Long id, Date dataInicio);

	List<Curso> filtrarPorPeriodoDataInicio(Date dataInicial, Date dataFinal);
	
	List<Curso> findAllSemVideoAulas();

}
