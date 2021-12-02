package com.dataguard.challenge.repository;

import com.dataguard.challenge.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
}
