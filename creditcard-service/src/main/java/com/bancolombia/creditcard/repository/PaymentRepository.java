package com.bancolombia.creditcard.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.creditcard.domain.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{
//	List<Movement> findByCreditcard(Creditcard number);
//	List<WashComment> findByWash_CarWash_Id($Parameter(name="id") int id) 
}
