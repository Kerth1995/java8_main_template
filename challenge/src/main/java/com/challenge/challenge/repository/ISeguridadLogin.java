package com.challenge.challenge.repository;

import com.challenge.challenge.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeguridadLogin extends JpaRepository<Login, Integer> {

}
