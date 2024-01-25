package com.homework.travel.dao;

import java.io.Serializable;

public interface BasicDao<T extends Serializable> {
	
	void create(T t);
	T update(T t);
	void delete(T t);
}
