package com.galdino.cursos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "t_videoaulas")
public class VideoAula implements Serializable {

	private static final long serialVersionUID = 1813676955917876549L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60)
	private String titulo;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Integer numero;

	@JsonInclude(value = Include.NON_NULL)
	@JsonIgnoreProperties({"videoAulas", "titulo", "cargaHoraria", "dataInicio"})
	@ManyToOne
	@JoinColumn(name = "id_curso_fk")
	private Curso curso;
	
	public VideoAula() {
		super();
	}
	
	public VideoAula(Long id, String titulo, String descricao, Integer numero) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.numero = numero;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoAula other = (VideoAula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VideoAula [id=" + id + ", titulo=" + titulo + "]";
	}
}
