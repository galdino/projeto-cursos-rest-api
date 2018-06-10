package com.galdino.cursos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galdino.cursos.model.Curso;
import com.galdino.cursos.repository.CursoRepository;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public void save(Curso curso) {
		cursoRepository.save(curso);
	}

	@Override
	public void update(Long id, Curso curso) {
		Curso cursoAux = this.findById(id);
		cursoAux.setTitulo(curso.getTitulo());
		cursoAux.setCargaHoraria(curso.getCargaHoraria());
		cursoAux.setDataInicio(curso.getDataInicio());

		this.save(cursoAux);
	}

	@Override
	public void delete(Long id) {
		cursoRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Curso findById(Long id) {
		return cursoRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return cursoRepository.findAll();
	}

	@Override
	public Curso updateDataInicio(Long id, Date dataInicio) {
		Curso curso = this.findById(id);
		curso.setDataInicio(dataInicio);
		return curso;
	}

}
