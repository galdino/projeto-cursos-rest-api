package com.galdino.cursos.resource.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		return fields.equals("curso")
				? videoAulaService.findAllByCurso(idCurso)
				: videoAulaService.findAllByCursoSemCurso(idCurso);		

	}

}
