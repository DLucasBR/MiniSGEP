package br.edu.univasf.minisgep.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.edu.univasf.minisgep.modelo.Autor;
import br.edu.univasf.minisgep.modelo.Patente;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Autor danielLucas = geraAutor("Daniel Lucas Nunes De Alencar Alves","dalucasdna@hotmail.com", "Avenida Antonio Carlos Magalhes", "87991279126", "Discente de Engenharia da Computacao","05875813440");
		em.persist(danielLucas);

		Autor anaEmilia = geraAutor("Ana Emilia Queiroz","ana.queiroz@univasf.edu.br", "Avenida Antonio Carlos Magalhes", "123456789", "Professora de Engenharia da Computacao","987654321");
		em.persist(anaEmilia);

		Autor ricardoRamos = geraAutor("Ricardo Ramos","mario.godoy@univasf.edu.br", "Avenida Antonio Carlos Magalhes", "987654321", "Professor de Engenharia da Computacao","123456789");
		em.persist(ricardoRamos);

		Patente sgep = geraPatente("SGEP", "Software de cadastro de patentes", "Software online que recebe pedidos de patentes e gera pdf formatado", "propriedade intelectual e so minha!!!","01/01/2018", danielLucas);
		Patente tcc = geraPatente("TCC", "TCC de daniel lucas", "sistema de controle de gasto energetico de edificios comerciais por redes de sensores sem fio", "Patente de produto","18/06/2018", ricardoRamos);

		em.persist(sgep);
		em.persist(tcc);


		em.getTransaction().commit();
		em.close();

	}

	private static Autor geraAutor(String nome,String email, String endereco, String telefone, String qualificacao, String CPF) {
		Autor autor = new Autor();
		autor.setNome(nome);
		autor.setEmail(email);
		autor.setEndereco(endereco);
		autor.setTelefone(telefone);
		autor.setQualificacao(qualificacao);
		autor.setCPF(CPF);
		return autor;
	}

	private static Patente geraPatente(String titulo, String resumo, String descricao,
			String reivindicacao,String data, Autor autor) {
		Patente patente = new Patente();
		patente.setTitulo(titulo);;
		patente.setResumo(resumo);
		patente.setDescricao(descricao);
		patente.setReivindicacao(reivindicacao);
		patente.setData(parseData(data));
		patente.adicionaAutor(autor);
		return patente;
	}

	@SuppressWarnings("unused")
	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
