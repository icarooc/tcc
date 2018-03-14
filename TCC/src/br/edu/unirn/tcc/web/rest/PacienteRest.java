package br.edu.unirn.tcc.web.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.unirn.tcc.dao.PacienteDAO;
import br.edu.unirn.tcc.dominio.Paciente;

@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PacienteRest {
	
	@GET
	public List<Paciente> pacientes(){
		PacienteDAO pDao = new PacienteDAO();
		List<Paciente> pacientes = pDao.findAll();
		pacientes.forEach(p -> p.setFotos(null));
		return pacientes;
	}
	
	@GET
	@Path("{id}")
	public Paciente paciente (@PathParam("id") int id){
		PacienteDAO pDao = new PacienteDAO();
		Paciente pacientes = pDao.findByPrimaryKey(id);
		return pacientes;
	}
	
	@POST
	public Paciente criar(Paciente p){
		return p;
	}
	
	@PUT
	public Paciente alterar(Paciente p){
		return p;
	}
	
	@DELETE
	public Response remover(Paciente p){
		return Response.status(200).build();
	}

}
