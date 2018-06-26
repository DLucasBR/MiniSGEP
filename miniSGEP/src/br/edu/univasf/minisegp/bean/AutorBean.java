package br.edu.univasf.minisegp.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.edu.univasf.minisgep.dao.DAO;
import br.edu.univasf.minisgep.modelo.Autor;
import br.edu.univasf.minisgep.util.ValidadorCPF;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void alterar(Autor autor) {
		this.autor = autor;
	}
	
	public void remover(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
	}

	public void validaCPF(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String CPF = value.toString();
		if (!ValidadorCPF.isCPF(CPF))
			throw new ValidatorException(new FacesMessage("CPF invalido!"));
		else
			autor.setCPF(ValidadorCPF.imprimeCPF(CPF));
	}

	public String gravar() {

		if(this.autor.getId() == null)
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		else
		new DAO<Autor>(Autor.class).atualiza(this.autor);	
		System.out.println("Gravando autor " + this.autor.getNome() + "ID do autor adicionado = " + this.autor.getId());

		autor = new Autor();
		
		return "patentes?faces-redirect=true";
	}
	
	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
}
