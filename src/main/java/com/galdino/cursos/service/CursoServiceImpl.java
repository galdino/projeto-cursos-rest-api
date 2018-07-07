package com.galdino.cursos.service;

import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.cursos.exception.IdNaoValidoServiceException;
import com.galdino.cursos.exception.NaoExisteDaoException;
import com.galdino.cursos.model.Curso;
import com.galdino.cursos.repository.CursoRepository;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public void save(Curso curso) {
		try {
			cursoRepository.save(curso);
		} catch (DataIntegrityViolationException e) {
			if(e.getMostSpecificCause().getMessage().toUpperCase().contains("UNIQUE_TITULO_DATAINICIO")) {
				throw new ConstraintViolationException(e.getMessage(), null, "unique_titulo_dataInicio");
			} else {
				throw new DataIntegrityViolationException(e.getMessage());
			}
		}
	}

	@Override
	public void update(Long id, Curso curso) {
		curso.setId(this.idValido(id));
		
		Curso cursoAux = this.findById(curso.getId());
		cursoAux.setTitulo(curso.getTitulo());
		cursoAux.setCargaHoraria(curso.getCargaHoraria());
		cursoAux.setDataInicio(curso.getDataInicio());

		this.save(cursoAux);
	}

	@Override
	public void delete(Long id) {
		try {
			cursoRepository.delete(idValido(id));
		} catch (EmptyResultDataAccessException e) {
			throw new NaoExisteDaoException("Curso não encontrado para o id = "+ id + ".");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findById(Long id) {
		Curso curso = cursoRepository.findOne(this.idValido(id));
		if(curso == null) {
			throw new NaoExisteDaoException("Curso não encontrado para o id = "+ id + ".");
		}
		return curso;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return cursoRepository.findAll();
	}
	
	@Override
	public Curso updateDataInicio(Long id, Date dataInicio) {
		Curso curso = this.findById(this.idValido(id));
		curso.setDataInicio(dataInicio);
		return curso;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Curso> filtrarPorPeriodoDataInicio(Date dataInicial, Date dataFinal) {
		return cursoRepository.filtrarPorPeriodoDataInicio(dataInicial, dataFinal);
	}
	
	private Long idValido(Long id) {
		if(id <= 0) {
			throw new IdNaoValidoServiceException("Valor do campo id está inválido. Deve ser um valor inteiro maior que zero.");
		}
		
		return id;
		
	}

}
