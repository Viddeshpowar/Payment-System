package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payment.entity.Account;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	@Query("SELECT MAX(a.accountId) FROM Account a")
    Long findMaxAccountId();
	
	List<Account> findByAccountName(String accountName);
}
