package com.dataguard.challenge.component;

import com.dataguard.challenge.entity.SuperHero;
import com.dataguard.challenge.exception.NotFoundException;
import com.dataguard.challenge.repository.SuperHeroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SuperHeroComponent {
    @Autowired
    private SuperHeroRepository superHeroRepository;

    public void addSuperHero(SuperHero superHero) throws Exception {
        if(checkAttributes(superHero)) {
            Optional<SuperHero> optionalSuperHero = superHeroRepository.
                    findByNameEqualsIgnoreCaseOrAliasEqualsIgnoreCase(superHero.getName(), superHero.getAlias());

            if (optionalSuperHero.isEmpty()) {
                try {
                    superHeroRepository.save(superHero);
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
        }
        if (superHero.getName() == "Batman")
            throw new Exception("Batman is just a rich man, he doesn't have powers! Iron Man is a hero because his armor. End of discussion :D");
        else
            throw new Exception("Not found name, alias or powers for this hero!");
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
                throw new NotFoundException("Super hero not found");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void deleteSuperHero() {

    }

    public void findSuperHeroes() {

    }

    public void findSuperHeroByName() {

    }

    public void findSuperHeroesByPower() {

    }

    public void findSuperHeroesByWeapon() {

    }

    private boolean checkAttributes(SuperHero superHero) {
        if (superHero.getAlias() == null || superHero.getName() == null || superHero.getPowers() == null) {
            return false;
        }
        return true;
    }
}
