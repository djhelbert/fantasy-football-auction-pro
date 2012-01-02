package com.fantasy.football.auctionpro.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Base JPA DAO
 * 
 * @author dhelbert
 *
 */
public abstract class BaseJpaDao<T, PK extends Serializable> {

	/** Entity Manager Factory */
	protected static EntityManagerFactory entityManagerFactory;
	
	/** Entity Manager */
	protected static EntityManager entityManager;
	
	/** Type */
	private Class<T> classType;
	
	/**
	 * Constructor
	 * 
	 * @param pType
	 */
	public BaseJpaDao(Class<T> classType) {
		this.classType = classType;

		if( entityManagerFactory == null ) {
			entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
		}
		
		if( entityManager == null ) {
			entityManager = entityManagerFactory.createEntityManager();
		}
	}
	
	/**
	 * Close
	 */
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	/**
	 * Get EntityManager
	 * 
	 * @return EntityManager
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Begin Transaction
	 */
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}
	
	/**
	 * Commit Transaction
	 */
	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	/**
	 * Roll Back Transaction
	 * 
	 */
	public void rollbackTransaction() {
		entityManager.getTransaction().rollback();
	}
	
	/**
	 * Create
	 * 
	 * @param entity
	 */
	public void create(T entity) {
		try {
			beginTransaction();
			getEntityManager().persist(entity);
			commitTransaction();
		}
		catch(Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Delete
	 * 
	 * @param id
	 */
	public void delete(PK id) {
		if (id != null) {
			try {
				beginTransaction();
				
				T entity = read(id);
				getEntityManager().remove(entity);
				
				commitTransaction();
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Cannot delete an entity with a null primary key.");
		}
	}
	
	/**
	 * Delete
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		if (entity != null) {
			try {
				beginTransaction();
				
				T merged = getEntityManager().merge(entity);
				getEntityManager().remove(merged);
				
				commitTransaction();
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Cannot delete a null entity.");
		}
	}

	/**
	 * Read
	 * 
	 * @param pId
	 * @return
	 */
	public T read(PK id) {
		T results = null;
		
		if (id != null) {
			results = getEntityManager().find(classType, id);
		}
		
		return results;
	}

	/**
	 * Update 
	 * 
	 * @param pObject
	 * @return
	 */
	public T update(T entity) {
		T merged = null;
		
		if (entity != null) {
			try {
				beginTransaction();
				
				merged = getEntityManager().merge(entity);
				
				commitTransaction();
			}
			catch(Exception err) {
				err.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException("Cannot attempt to update a entity.");
		}
		
		return merged;
	}

}

