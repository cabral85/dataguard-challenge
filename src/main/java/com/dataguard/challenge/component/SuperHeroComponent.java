package com.dataguard.challenge.component;

import com.dataguard.challenge.entity.SuperHero;
import com.dataguard.challenge.exception.NotFoundException;
import com.dataguard.challenge.repository.SuperHeroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class SuperHeroComponent {
    @Autowired
    private SuperHeroRepository superHeroRepository;

    public void addSuperHero(SuperHero superHero) throws Exception {
        if(checkAttributes(superHero)) {
            Optional<SuperHero> optionalSuperHero = superHeroRepository.
                    findByNameEqualsIgnoreCaseOrAliasEqualsIgnoreCase(superHero.getName(), superHero.getAlias());

            if (!optionalSuperHero.isPresent()) {
                try {
                    superHeroRepository.save(superHero);
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
        } else {
            throw new Exception("Not found name, alias or powers for this hero!");
        }
    }

    public SuperHero updateSuperHero(SuperHero superHero, Long superHeroId) throws Exception {
        try {
            Optional<SuperHero> optionalSuperHero = superHeroRepository.findById(superHeroId);
            if (optionalSuperHero.isPresent() && checkAttributes(superHero)) {
                SuperHero currentData = optionalSuperHero.get();
                BeanUtils.copyProperties(superHero, currentData, "superHeroId");
                superHeroRepository.save(currentData);
                return currentData;
            } else {
                throw new NotFoundException("Superhero not found");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    // Please use this method only if you added Batman by mistake changing his name... Heroes are heroes even after die
    public void deleteSuperHero(Long superHeroId) throws Exception {
        try {
            Optional<SuperHero> optionalSuperHero = superHeroRepository.findById(superHeroId);
            if (optionalSuperHero.isPresent()) {
                superHeroRepository.deleteById(superHeroId);
            } else {
                throw new NotFoundException("Was not possible to find a superhero with this ID");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<SuperHero> findSuperHeroes() throws Exception {
        try {
            return superHeroRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public SuperHero findSuperHeroByNameOrAlias(String name, String alias) throws Exception {
        try {
            Optional<SuperHero> optionalSuperHero = superHeroRepository.findByNameEqualsIgnoreCaseOrAliasEqualsIgnoreCase(name, alias);
            if (optionalSuperHero.isPresent()) {
               return optionalSuperHero.get();
            } else {
                throw new NotFoundException("Was not possible to find a superhero with this name or alias");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<SuperHero> findSuperHeroesByPower(String[] powers) throws Exception {
        try {
            List<SuperHero> optionalSuperHero = superHeroRepository.findByPowersIn(Collections.singleton(powers));
            if (!optionalSuperHero.isEmpty()) {
                return optionalSuperHero;
            } else {
                throw new NotFoundException("Was not possible to find a superhero with this power");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<SuperHero> findSuperHeroesByWeapon(String[] weapon) throws Exception {
        try {
            List<SuperHero> optionalSuperHero = superHeroRepository.findByWeaponsIn(Collections.singleton(weapon));
            if (!optionalSuperHero.isEmpty()) {
                return optionalSuperHero;
            } else {
                throw new NotFoundException("Was not possible to find a superhero with this weapon");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private boolean checkAttributes(SuperHero superHero) {
        if (superHero.getAlias() == null || superHero.getName() == null || superHero.getPowers() == null) {
            return false;
        }
        return true;
    }
}
