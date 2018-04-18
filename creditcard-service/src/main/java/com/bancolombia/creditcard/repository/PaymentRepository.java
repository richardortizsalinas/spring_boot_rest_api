package com.bancolombia.creditcard.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.creditcard.domain.Creditcard;
import com.bancolombia.creditcard.domain.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{
	List<Payment> findByCreditcard(Creditcard number);
}
