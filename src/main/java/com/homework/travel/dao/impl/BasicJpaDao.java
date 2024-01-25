package com.homework.travel.dao.impl;

import java.io.Serializable;

import com.homework.travel.dao.BasicDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class BasicJpaDao<T extends Serializable> implements BasicDao<T> {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void create(T t) {
		em.persist(t);
	}
	
	@Override
	public T update(T t) {
		return em.merge(t);
	}
	
	@Override
	public void delete(T t) {
		em.remove(t);
	}
}
