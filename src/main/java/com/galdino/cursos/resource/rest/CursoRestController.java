package com.galdino.cursos.resource.rest;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.galdino.cursos.model.Curso;
import com.galdino.cursos.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoRestController {

	@Autowired
	private CursoService cursoService;

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Curso curso) {

		cursoService.save(curso);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(curso.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public Curso getCurso(@PathVariable Long id) {
		return cursoService.findById(id);
	}
	
	@GetMapping("/dataInicial/{dataInicial}/dataFinal/{dataFinal}")
	public List<Curso> filtrarPorPeriodoDataInicio(@PathVariable String dataInicial, @PathVariable String dataFinal) throws ParseException{
		String dataInicialAux = dataInicial.replace("-", "/");
		String dataFinalAux = dataFinal.replace("-", "/");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicialDate = simpleDateFormat.parse(dataInicialAux);
		Date dataFinalDate = simpleDateFormat.parse(dataFinalAux);
		
		return cursoService.filtrarPorPeriodoDataInicio(dataInicialDate, dataFinalDate);
	}

	@GetMapping
	public List<Curso> listar(@RequestParam(name = "fields", required = false, defaultValue = "") String fields) {
		return fields.equals("videoAulas")
				? cursoService.findAll()
				: cursoService.findAllSemVideoAulas();		
	}
	
	@PutMapping("/{id}")
	public Curso editar(@PathVariable Long id, @RequestBody Curso curso) {
		cursoService.update(id, curso);
		
		return curso;
	}
	
	@PatchMapping("/{id}")
	public Curso editarDataInicio(@PathVariable Long id, @RequestBody Curso curso) {
		return cursoService.updateDataInicio(id, curso.getDataInicio());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		cursoService.delete(id);		
	}

}
