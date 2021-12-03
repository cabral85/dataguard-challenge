package com.dataguard.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class SuperHero implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long superHeroId;

    @Column(nullable = false)
    private String alias;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String[] powers;

    @Column
    private String[] weapons;

    private String origin;

    private String[] associations;

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
