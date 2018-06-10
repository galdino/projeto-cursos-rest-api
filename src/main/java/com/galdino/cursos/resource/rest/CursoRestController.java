package com.galdino.cursos.resource.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galdino.cursos.model.Curso;
import com.galdino.cursos.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoRestController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping
	public List<Curso> listar(){
		return cursoService.findAll();
	}

}
