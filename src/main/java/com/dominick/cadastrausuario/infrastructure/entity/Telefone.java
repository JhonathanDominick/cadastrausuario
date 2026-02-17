package com.dominick.cadastrausuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "telefone", length = 10)
    private Long telefone;
    @Column(name = "ddd", length = 3)
    private String ddd;


}
