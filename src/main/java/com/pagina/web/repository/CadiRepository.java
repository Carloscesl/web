package com.pagina.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.web.model.cadi;

@Repository
public interface CadiRepository extends JpaRepository<cadi, Long> {

}
