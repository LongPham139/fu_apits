package com.apits.apitssystembackend.repository;

import com.apits.apitssystembackend.entity.Account;
import com.apits.apitssystembackend.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
    Account findAccountByEmail(String email);
    Page<Account> findByRole(Role role, Pageable pageable);
    Account findAccountById(int id);
    @Query(nativeQuery = true
    , value = "SELECT * " +
            "FROM aptis_db.account " +
            "where email LIKE :name% \n" +
            "order by id asc\n" +
            ";")
    List<Account> findLastAccountLikeName(@Param("name") String name);
}
