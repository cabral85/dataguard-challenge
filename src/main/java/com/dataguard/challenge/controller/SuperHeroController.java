package com.dataguard.challenge.controller;

import com.dataguard.challenge.component.SuperHeroComponent;
import com.dataguard.challenge.entity.SuperHero;
import com.dataguard.challenge.exception.NotFoundException;
import com.dataguard.challenge.mapper.ExceptionMapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api")
public class SuperHeroController {
    private static final Logger logger = Logger.getLogger(SuperHeroController.class);

    @Autowired
    private SuperHeroComponent superHeroComponent;

    @PostMapping
    public ResponseEntity addHero(@RequestBody SuperHero superHero) throws Exception {
        superHeroComponent.addSuperHero(superHero);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{superHeroId}")
    public ResponseEntity<SuperHero> updateHero(@PathVariable("superHeroId") Long superHeroId, @RequestBody SuperHero superHero) throws Exception {
        SuperHero superHeroResponse = superHeroComponent.updateSuperHero(superHero, superHeroId);
        return ResponseEntity.status(HttpStatus.OK).body(superHeroResponse);
    }

    @DeleteMapping("{superHeroId}")
    public ResponseEntity deleteHero(@PathVariable("superHeroId") Long superHeroId) throws Exception {
        superHeroComponent.deleteSuperHero(superHeroId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity findHeroes() throws Exception {
        List<SuperHero> heroes = superHeroComponent.findSuperHeroes();
        return ResponseEntity.status(HttpStatus.OK).body(heroes);
    }

    @GetMapping("filter")
    public ResponseEntity findHero(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "alias", required = false) String alias) throws Exception {
        SuperHero superHero = superHeroComponent.findSuperHeroByNameOrAlias(name, alias);
        return ResponseEntity.status(HttpStatus.OK).body(superHero);
    }

    @GetMapping("power")
    public ResponseEntity<List<SuperHero>> findHeroByPower(@RequestParam("power") String[] power) throws Exception {
        List<SuperHero> heroes = superHeroComponent.findSuperHeroesByPower(power);
        return ResponseEntity.status(HttpStatus.OK).body(heroes);
    }

    @GetMapping("weapon")
    public ResponseEntity<List<SuperHero>> findHeroByWeapon(@RequestParam("weapon") String[] weapon) throws Exception {
        List<SuperHero> heroes = superHeroComponent.findSuperHeroesByWeapon(weapon);
        return ResponseEntity.status(HttpStatus.OK).body(heroes);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleException(NotFoundException e) {
        logger.info(String.format("## Exception: Stacktrace: %s, Cause: %s Error: %s ", e.getStackTrace(), e.getCause(), e.getMessage()));
        ExceptionMapperResponse mapper = new ExceptionMapperResponse(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(mapper);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.info(String.format("## Exception: Stacktrace: %s, Cause: %s Error: %s ", e.getStackTrace(), e.getCause(), e.getMessage()));
        ExceptionMapperResponse mapper = new ExceptionMapperResponse(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(mapper);
    }
}
