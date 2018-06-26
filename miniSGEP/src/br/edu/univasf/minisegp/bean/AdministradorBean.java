package br.edu.univasf.minisegp.bean;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.univasf.minisgep.dao.DAO;
import br.edu.univasf.minisgep.modelo.Patente;

@ManagedBean
@SessionScoped
public class AdministradorBean {

	
	public void patantesRegistradasPorMes() {
		
		List<Patente> patentes = new DAO<Patente>(Patente.class).listaTodos();
		int [] meses =  new int[12] ;
		
		for(Patente patente:patentes) {
			System.out.println("Mes da patente " + patente.getTitulo() + " = " + ((patente.getData().get(Calendar.MONTH)) + 1));
			meses[(patente.getData().get(Calendar.MONTH)) + 1 ]++;
			System.out.println("Meses na posica 6 = " + meses[6]);
		}
		
		for(int i = 0; i < 12; i++)
			System.out.println("O numero de patentes em " + i + 1 + " eh = " +  meses[i]);	 
	}
}
