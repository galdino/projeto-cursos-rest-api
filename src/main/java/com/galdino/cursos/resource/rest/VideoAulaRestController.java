package com.galdino.cursos.resource.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.galdino.cursos.model.VideoAula;
import com.galdino.cursos.service.VideoAulaService;

@RestController
@RequestMapping("/cursos/{idCurso}/videoaulas")
public class VideoAulaRestController {

	@Autowired
	private VideoAulaService videoAulaService;

	@GetMapping("/{idVideoAula}")
	@ResponseStatus(value = HttpStatus.OK)
	public VideoAula getVideoAula(@PathVariable("idVideoAula") Long idVideoAula,
			                      @PathVariable("idCurso") Long idCurso) {

		return videoAulaService.findByIdVideoAulaAndIdCurso(idVideoAula, idCurso);

	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<VideoAula> getAllVideoAulas(@PathVariable("idCurso") Long idCurso,
			                                @RequestParam(name = "fields", defaultValue = "", required = false) String fields) {
		return fields.equals("curso") ? videoAulaService.findAllByCurso(idCurso)
				: videoAulaService.findAllByCursoSemCurso(idCurso);

	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Void> salvar(@RequestBody VideoAula videoAula, @PathVariable("idCurso") Long idCurso) {
		
		videoAulaService.save(idCurso, videoAula);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(videoAula.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{idVideoAula}")
	@ResponseStatus(value = HttpStatus.OK)
	public VideoAula update(@RequestBody VideoAula videoAula, 
			                @PathVariable("idCurso") Long idCurso,
			                @PathVariable("idVideoAula") Long idVideoAula) {
		
		videoAulaService.update(idCurso, idVideoAula, videoAula);
		
		return videoAula;
		
	}
	
	@DeleteMapping("/{idVideoAula}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("idCurso") Long idCurso, 
					   @PathVariable("idVideoAula") Long idVideoAula) {
		
		videoAulaService.delete(idCurso, idVideoAula);
		
	}

}
