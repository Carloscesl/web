package com.pagina.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.web.model.recursoAdicional;

@Repository
public interface RecursoAdicionalRepository extends JpaRepository<recursoAdicional, Long> {

}
