package org.example.service;

import org.example.dao.PlanetDao;
import org.example.entity.Planet;

import java.util.List;

public class PlanetCrudService {
    private PlanetDao planetDao = new PlanetDao();

    public void createPlanet(Planet planet) {
        planetDao.createPlanet(planet);
    }

    public Planet getPlanetById(String id) {
        return planetDao.findById(id);
    }

    public List<Planet> getAllPlanets() {
        return planetDao.getAllPlanets();
    }

    public void updatePlanet(Planet planet) {
        planetDao.update(planet);
    }

    public void deletePlanet(Planet planet) {
        planetDao.delete(planet);
    }
}

