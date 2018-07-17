package com.galdino.cursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.galdino.cursos.model.VideoAula;

public interface VideoAulaRepository extends JpaRepository<VideoAula, Long> {
	
	@Query("SELECT v FROM VideoAula v WHERE v.id=:idVideoAula AND v.curso.id=:idCurso")
	VideoAula findByIdVideoAulaAndIdCurso(@Param("idVideoAula") Long idVideoAula,
										  @Param("idCurso") Long idCurso);
	
	@Query("SELECT v FROM VideoAula v WHERE v.curso.id=:idCurso")
	List<VideoAula> findAllByCurso(@Param("idCurso") Long idCurso);
	
	@Query("SELECT new VideoAula(v.id, v.titulo, v.descricao, v.numero) FROM VideoAula v WHERE v.curso.id=:idCurso")
	List<VideoAula> findAllByCursoSemCurso(@Param("idCurso") Long idCurso);
 
}
