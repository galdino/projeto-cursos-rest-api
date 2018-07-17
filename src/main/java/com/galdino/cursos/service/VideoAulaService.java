package com.galdino.cursos.service;

import java.util.List;

import com.galdino.cursos.model.VideoAula;

public interface VideoAulaService {

	void save(Long idCurso, VideoAula videoAula);

	void update(Long idCurso, Long idVideoAula, VideoAula videoAula);

	void delete(Long idCurso, Long idVideoAula);

	VideoAula findByIdVideoAulaAndIdCurso(Long idVideoAula, Long idCurso);

	List<VideoAula> findAllByCurso(Long idCurso);

	List<VideoAula> findAllByCursoSemCurso(Long idCurso);

}
