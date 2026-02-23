package com.pagina.web.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recurso_adicional")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class recursoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 500)
    private String archivo;

    @Column(name = "fecha_subida")
    private LocalDateTime fechaSubida;

    @ManyToOne
    @JoinColumn(name = "rea_id", nullable = false)
    private rea rea;

    private Long usuarioId;

    @PrePersist
    protected void onCreate() {
        this.fechaSubida = LocalDateTime.now();
    }
}
