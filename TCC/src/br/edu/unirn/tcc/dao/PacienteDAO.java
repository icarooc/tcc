package br.edu.unirn.tcc.dao;

import javax.persistence.EntityManager;

import br.edu.unirn.tcc.dominio.Paciente;

public class PacienteDAO extends GenericDAO<Paciente> {

	@Override
	public Class<Paciente> getClassType() {
		// TODO Auto-generated method stub
		return Paciente.class;
	}
	
	public Paciente findByPrimaryKey(Long id){
		EntityManager em = getEm();
		Paciente c = em.find(getClassType(), id);
		return c;
	}

}
