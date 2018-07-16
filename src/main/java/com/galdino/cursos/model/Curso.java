package com.galdino.cursos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "cursos", indexes = {
		@Index(columnList = "titulo, data_inicio", unique = true, name = "unique_titulo_dataInicio")

})
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(name = "carga_horaria", nullable = false)
	private CargaHoraria cargaHoraria;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonIgnoreProperties("curso")
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	private List<VideoAula> videoAulas;
	
	public Curso() {
		super();
	}
	
	public Curso(Long id, String titulo, CargaHoraria cargaHoraria, Date dataInicio) {
		this.id = id;
		this.titulo = titulo;
		this.cargaHoraria = cargaHoraria;
		this.dataInicio = dataInicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public CargaHoraria getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(CargaHoraria cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public List<VideoAula> getVideoAulas() {
		return videoAulas;
	}

	public void setVideoAulas(List<VideoAula> videoAulas) {
		this.videoAulas = videoAulas;
	}

}
