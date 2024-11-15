package com.app.acessibilidade.api.domain.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@AllArgsConstructor
@NoArgsConstructor

public class Foto {

    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="id")
    private Long id;
    @Column(name = "imagem", columnDefinition = "TEXT")
    private  String imagem;

     @OneToMany(mappedBy = "foto")
    private List<ItemFoto> itemFotos;
    
    
}
