package com.tiagogguedes.oficina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagogguedes.oficina.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
