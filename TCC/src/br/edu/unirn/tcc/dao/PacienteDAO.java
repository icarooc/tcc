package br.edu.unirn.tcc.dao;

import br.edu.unirn.tcc.dominio.Paciente;

public class PacienteDAO extends GenericDAO<Paciente> {

	@Override
	public Class<Paciente> getClassType() {
		// TODO Auto-generated method stub
		return Paciente.class;
	}

}
