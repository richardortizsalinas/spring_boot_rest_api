package com.bancolombia.creditcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bancolombia.creditcard.domain.Creditcard;

@Repository
public interface CreditcardRepository extends JpaRepository<Creditcard, Long>{
//	List<Creditcard> findByLastName(String lastName);
	Creditcard findByNumber(String number);
}
