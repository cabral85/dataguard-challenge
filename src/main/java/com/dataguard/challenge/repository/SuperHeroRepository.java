package com.dataguard.challenge.repository;

import com.dataguard.challenge.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    Optional<SuperHero> findByNameEqualsIgnoreCaseOrAliasEqualsIgnoreCase(String name, String alias);

    List<SuperHero> findByPowersContainsIgnoreCase(String powers);

    List<SuperHero> findByWeaponsContainsIgnoreCase(String weapons);
}
