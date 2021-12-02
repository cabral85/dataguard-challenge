package com.dataguard.challenge.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table
public class SuperHero implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long superHeroId;

    @Column
    private String alias;

    @Column
    private String name;

    @Column
    private Set powers;

    @Column
    private Set weapons;

    @Column
    private String origin;

    @Column
    private Set associations;

    @Override
    public String toString() {
        return "SuperHero{" +
                "superHeroId=" + superHeroId +
                ", alias='" + alias + '\'' +
                ", name='" + name + '\'' +
                ", powers=" + powers +
                ", weapons=" + weapons +
                ", origin='" + origin + '\'' +
                ", associations=" + associations +
                '}';
    }
}
