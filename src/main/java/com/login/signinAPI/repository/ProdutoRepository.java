package com.login.signinAPI.repository;

import com.login.signinAPI.entity.Produto;
import com.login.signinAPI.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Produto findByEmail(String email);
}

