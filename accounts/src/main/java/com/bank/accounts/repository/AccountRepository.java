package com.bank.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.accounts.entity.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long>{

}
