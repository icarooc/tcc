package br.edu.unirn.tcc.beans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSessionContext;
import javax.xml.ws.spi.http.HttpContext;

import org.primefaces.component.tabview.TabView;

import br.edu.unirn.tcc.dao.PacienteDAO;
import br.edu.unirn.tcc.dominio.Paciente;

@ManagedBean
@SessionScoped
public class PacienteMBean {
	
	TabView tabView;
	
	private Paciente paciente = new Paciente();

	public String salvar() throws IOException{
		PacienteDAO pDao = new PacienteDAO();
		pDao.create(paciente);
		System.out.println(paciente.getId());
		//FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
		return "index.xhtml?faces-redirect=true";
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}
	
}
