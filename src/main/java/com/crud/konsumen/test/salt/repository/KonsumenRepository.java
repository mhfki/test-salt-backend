package com.crud.konsumen.test.salt.repository;

import java.util.List;

import com.crud.konsumen.test.salt.model.Konsumen;

public interface KonsumenRepository {
	List<Konsumen> findAll(String keyword);
	
	Konsumen findById(int id);
	
	int save(Konsumen konsumen);
	
	int update(Konsumen konsumen);
	
	int deleteById(int id);
}
