package com.dataguard.challenge.component;

import com.dataguard.challenge.entity.SuperHero;
import com.dataguard.challenge.repository.SuperHeroRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class SuperHeroComponentTest {

    private final String[] powers = new String[] {"Money"};
    private final String[] weapons = new String[] {"Combat Arm"};
    private final String[] association = new String[] {"Avengers"};

    @InjectMocks
    private SuperHeroComponent superHeroComponent;

    @Mock
    private SuperHeroRepository superHeroRepository;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private SuperHero mockedHero1() {
        SuperHero hero1 = new SuperHero(1L, "Iron Man", "Tony Stark", powers, weapons, "Earth", association);
        return hero1;
    }

    private SuperHero mockedHero2() {
        SuperHero hero2 = new SuperHero(2L, "Captain America", "Steve Rogers", new String[] {"Strength"}, new String[] {"Shield"}, "Earth", association);
        return hero2;
    }

    @Test
    void whenSaveNewHeroReturnHero() throws Exception {
        SuperHero superHero = mockedHero1();
        SuperHero mockedResponse = new SuperHero(1L, "Iron Man", "Tony Stark", powers, weapons, "Earth", association);

        when(superHeroRepository.findByNameEqualsIgnoreCaseOrAliasEqualsIgnoreCase("Tony Stark", "Iron Man")).thenReturn(Optional.empty());
        when(superHeroRepository.save(superHero)).thenReturn(mockedResponse);
        SuperHero response = superHeroComponent.addSuperHero(superHero);

        assertEquals(mockedResponse, response);
    }

    @Test
    void whenGetAllSuperHeroesThenReturnHeroes() throws Exception {
        SuperHero hero1 = mockedHero1();
        SuperHero hero2 = mockedHero2();

        List<SuperHero> heroes = new ArrayList<>();
        heroes.add(hero1);
        heroes.add(hero2);

        when(superHeroRepository.findAll()).thenReturn(heroes);
        List<SuperHero> response = superHeroComponent.findSuperHeroes();
        assertEquals(heroes, response);
        assertEquals(2, response.size());
    }

    @Test
    void whenGetHeroByNameThenReturnHero() throws Exception {
        SuperHero hero1 = mockedHero1();

        when(superHeroRepository.findByNameEqualsIgnoreCaseOrAliasEqualsIgnoreCase("Tony Stark", "Iron Man")).thenReturn(Optional.of(hero1));
        SuperHero response = superHeroComponent.findSuperHeroByNameOrAlias("Tony Stark", "Iron Man");

        assertEquals(hero1, response);
    }

    @Test
    void whenGetHeroByPowerThenReturnHero() throws Exception {
        List<SuperHero> heroes = new ArrayList<>();
        heroes.add(mockedHero2());
        String[] powers = new String[] {"Strength"};
        when(superHeroRepository.findByPowersIn(Collections.singleton(powers))).thenReturn(heroes);
        List<SuperHero> response = superHeroComponent.findSuperHeroesByPower(powers);
        assertEquals(heroes, response);
    }

    @Test
    void whenGetHeroByWeaponThenReturnHero() throws Exception {
        List<SuperHero> heroes = new ArrayList<>();
        heroes.add(mockedHero2());
        String[] weapon = new String[] {"Shield"};
        when(superHeroRepository.findByWeaponsIn(Collections.singleton(weapon))).thenReturn(heroes);
        List<SuperHero> response = superHeroComponent.findSuperHeroesByWeapon(weapon);
        assertEquals(heroes, response);
    }
}
