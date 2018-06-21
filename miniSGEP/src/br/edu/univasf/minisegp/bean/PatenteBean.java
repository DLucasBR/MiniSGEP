package br.edu.univasf.minisegp.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.util.List;
import br.edu.univasf.minisgep.dao.DAO;
import br.edu.univasf.minisgep.modelo.Autor;
import br.edu.univasf.minisgep.modelo.Patente;

@ManagedBean
@ViewScoped
public class PatenteBean {

	private Integer autorId;
	private Patente patente = new Patente();

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void gravarAutor() {
		System.out.println("Autor adicionado");
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.patente.adicionaAutor(autor);
	}

	public Patente getPatente() {
		return patente;
	}

	public void gravar() {
		System.out.println("Gravar foi clicado!" + this.patente.getTitulo());

		if (patente.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("A patente deve ter pelo menos um Autor"));
			return;
		}

		if (this.patente.getId() == null)
			new DAO<Patente>(Patente.class).adiciona(this.patente);
		else
			new DAO<Patente>(Patente.class).atualiza(this.patente);

		this.patente = new Patente();
	}

	public List<Autor> getAutoresDaPatente(){
		return this.patente.getAutores();
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Patente> getPatentes() {
		return new DAO<Patente>(Patente.class).listaTodos();
	}

	public String formAutor() {
		return "autor?faces-redirect=true";
	}

	public void remover(Patente patente) {
		System.out.println("Removendo patente: " + patente.getTitulo());
		new DAO<Patente>(Patente.class).remove(patente);
	}

	public void alterar(Patente patente) {
		System.out.println("Carregando patente: " + patente.getTitulo());
		this.patente = patente;
	}

	public void removerAutorDaPatente(Autor autor) {
		this.patente.removerAutor(autor);
	}
}
