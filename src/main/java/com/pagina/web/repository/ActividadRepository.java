package com.pagina.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.web.model.actividad;
import com.pagina.web.model.rea;

@Repository
public interface ActividadRepository extends JpaRepository<actividad, Long> {
    List<actividad> findByRea(rea rea);
}
