package br.edu.univasf.minisgep.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Patente {
	
	@Id @GeneratedValue
	private Integer id;

	private String titulo;
	private String resumo;
	private String descricao;
	private String reivindicacao;
	
	@Temporal(TemporalType.DATE)
	private Calendar data = Calendar.getInstance();

	@ManyToMany(fetch=FetchType.EAGER) /*sempre que carregar as patentes, tambem carrega os autores*/
	private List<Autor> autores = new ArrayList<Autor>();

	public Integer getId() {
		return id;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}
	
	public Calendar getData() {
		return data;
	}
	
	public void setData(Calendar parseData) {
		 this.data = parseData;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getReivindicacao() {
		return reivindicacao;
	}

	public void setReivindicacao(String reivindicacao) {
		this.reivindicacao = reivindicacao;
	}

	public void removerAutor(Autor autor) {
		this.autores.remove(autor);
		
	}
}