package com.jwt.tokendemo.common.repositories;

import com.jwt.tokendemo.common.model.EOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EOUserRepository extends JpaRepository<EOUser,Long> {

//    public EOUser getUserByUsername(String name);
    public EOUser getUserByEmail(String email);
    public List<EOUser> findAll();
}
