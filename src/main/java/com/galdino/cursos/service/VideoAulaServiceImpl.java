package com.galdino.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.cursos.exception.NaoExisteDaoException;
import com.galdino.cursos.model.VideoAula;
import com.galdino.cursos.repository.VideoAulaRepository;

@Service
@Transactional
public class VideoAulaServiceImpl implements VideoAulaService {

	@Autowired
	private VideoAulaRepository videoAulaRepository;
	
	@Autowired
	private CursoService cursoService;

	@Override
	public void save(Long idCurso, VideoAula videoAula) {
		if(videoAula.getCurso() == null) {
			videoAula.setCurso(cursoService.findById(cursoService.idValido(idCurso)));
		}
		
		try {
			videoAulaRepository.save(videoAula);
		} catch (Exception e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}

	@Override
	public void update(Long idCurso, Long idVideoAula, VideoAula videoAula) {
		videoAula.setId(cursoService.idValido(idVideoAula));

		VideoAula videoAulaAux = this.findByIdVideoAulaAndIdCurso(videoAula.getId(), cursoService.idValido(idCurso));
		
		if(videoAulaAux != null) {
			videoAulaAux.setTitulo(videoAula.getTitulo());
			videoAulaAux.setDescricao(videoAula.getDescricao());
			videoAulaAux.setNumero(videoAula.getNumero());
			
			this.save(videoAulaAux.getCurso().getId(), videoAulaAux);
		}
	}

	@Override
	public void delete(Long idCurso, Long idVideoAula) {
		videoAulaRepository.delete(this.findByIdVideoAulaAndIdCurso(idVideoAula, idCurso));
	}

	@Override
	@Transactional(readOnly = true)
	public VideoAula findByIdVideoAulaAndIdCurso(Long idVideoAula, Long idCurso) {
		VideoAula videoAula = videoAulaRepository.findByIdVideoAulaAndIdCurso(idVideoAula, idCurso);
		if (videoAula == null) {
			throw new NaoExisteDaoException("Video Aula não encontrada para o id: " + idVideoAula);
		}
		return videoAula;
	}

	@Override
	@Transactional(readOnly = true)
	public List<VideoAula> findAllByCurso(Long idCurso) {
		List<VideoAula> list = videoAulaRepository.findAllByCurso(cursoService.idValido(idCurso));
		if(list == null || list.size() == 0) {
			throw new NaoExisteDaoException("Video Aula não encontrada para o Curso de id: " + idCurso);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<VideoAula> findAllByCursoSemCurso(Long idCurso) {
		List<VideoAula> list = videoAulaRepository.findAllByCursoSemCurso(cursoService.idValido(idCurso));
		if(list == null || list.size() == 0) {
			throw new NaoExisteDaoException("Video Aula não encontrada para o Curso de id: " + idCurso);
		}
		return list;
	}

}
