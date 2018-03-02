package br.edu.unirn.tcc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

	private static Database singleton = new Database();
	private static EntityManager em;

	private Database() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OdontoPU");
		em = emf.createEntityManager();
	}

	public static Database getInstance() {
		return singleton;
	}

	public EntityManager getEntityManager() {
		return em;
	}

}

