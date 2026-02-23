package com.pagina.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.web.model.rea;

@Repository
public interface ReaRepository extends JpaRepository<rea, Long> {

}
