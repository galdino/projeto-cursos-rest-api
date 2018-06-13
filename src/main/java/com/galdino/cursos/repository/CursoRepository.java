package com.galdino.cursos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.galdino.cursos.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	@Query("SELECT c FROM Curso c WHERE c.dataInicio BETWEEN :dataInicial AND :dataFinal")
	List<Curso> filtrarPorPeriodoDataInicio(@Param("dataInicial") Date dataInicial, 
											@Param("dataFinal") Date dataFinal);

}
