package com.example.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.domain.*;

public interface HumanRepository extends JpaRepository<Human, Integer> {

}
