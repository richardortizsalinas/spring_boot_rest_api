package com.bancolombia.creditcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bancolombia.creditcard.domain.Creditcard;

@Repository
public interface CreditcardRepository extends JpaRepository<Creditcard, Long> {
	// List<Creditcard> findByLastName(String lastName);
	Creditcard findByNumber(String number);

	@Query("select p from Creditcard p where upper(p.ownerIdType) = upper(?1) and upper(p.ownerId) = upper(?2)")
	List<Creditcard> getByOwner(String ownerIdType, String ownerId);
}
