package com.app.acessibilidade.api.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@AllArgsConstructor

public class ItemFoto {
    
    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="id")
    private Long id;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avaliacao", nullable = false)
    private Avaliacao avaliacao; 


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_foto", nullable = true) // Nullable = true, pois não é obrigatório ter uma foto associada
    private Foto foto; 
    
    
}
