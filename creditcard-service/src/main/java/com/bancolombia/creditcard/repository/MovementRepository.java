package com.bancolombia.creditcard.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.creditcard.domain.Creditcard;
import com.bancolombia.creditcard.domain.Movement;

@Repository
public interface MovementRepository extends CrudRepository<Movement, Long>{
	List<Movement> findByCreditcard(Creditcard number);
//	List<WashComment> findByWash_CarWash_Id($Parameter(name="id") int id) 
}
