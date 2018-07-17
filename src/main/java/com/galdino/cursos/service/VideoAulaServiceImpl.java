package com.galdino.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.cursos.model.VideoAula;
import com.galdino.cursos.repository.VideoAulaRepository;

@Service
@Transactional
public class VideoAulaServiceImpl implements VideoAulaService {

	@Autowired
	private VideoAulaRepository videoAulaRepository;

	@Override
	public void save(Long idCurso, VideoAula videoAula) {
		videoAulaRepository.save(videoAula);
	}

	@Override
	public void update(Long idCurso, Long idVideoAula, VideoAula videoAula) {
		videoAula.setId(idVideoAula);

		VideoAula videoAulaAux = this.findByIdVideoAulaAndIdCurso(videoAula.getId(), idCurso);
		videoAulaAux.setTitulo(videoAula.getTitulo());
		videoAulaAux.setDescricao(videoAula.getDescricao());
		videoAulaAux.setNumero(videoAula.getNumero());

		this.save(videoAulaAux.getCurso().getId(), videoAulaAux);
	}

	@Override
	public void delete(Long idCurso, Long idVideoAula) {

		videoAulaRepository.delete(this.findByIdVideoAulaAndIdCurso(idVideoAula, idCurso));

	}

	@Override
	@Transactional(readOnly = true)
	public VideoAula findByIdVideoAulaAndIdCurso(Long idVideoAula, Long idCurso) {
		return videoAulaRepository.findByIdVideoAulaAndIdCurso(idVideoAula, idCurso);
	}

	@Override
	@Transactional(readOnly = true)
	public List<VideoAula> findAllByCurso(Long idCurso) {
		return videoAulaRepository.findAllByCurso(idCurso);
	}

	@Override
	@Transactional(readOnly = true)
	public List<VideoAula> findAllByCursoSemCurso(Long idCurso) {
		return videoAulaRepository.findAllByCursoSemCurso(idCurso);
	}

}
