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


public class ItemAcessibilidade {
    @Id
    // indetity -> forma nativa do sgbd(no caso auto increment.Para casos em que o banco é criado manualmente. AUTO é para quando for criando junto com a API)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="id")
    private Long id;
   

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local", nullable = false )
    private Local local; // Cada item de acessibilidade está associado a um único local

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_acessibilidade", nullable = true) // Nullable = true, pois nem todo item de acessibilidade precisa ter um tipo
    private TipoAcessibilidade tipoAcessibilidade; // Cada ItemAcessibilidade pode ter no máximo um TipoDeAcessibilidade





}
