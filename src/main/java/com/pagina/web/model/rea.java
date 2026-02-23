package com.pagina.web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rea")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class rea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cadi_id", nullable = false)
    @JsonIgnoreProperties("reas") // Evita la serialización de la lista de reas en cadi
    private cadi cadi;

    @OneToMany(mappedBy = "rea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<actividad> actividades;

    @OneToMany(mappedBy = "rea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<recursoAdicional> recursos;
}
