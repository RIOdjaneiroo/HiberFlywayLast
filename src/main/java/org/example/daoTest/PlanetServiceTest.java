package org.example.daoTest;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.service.PlanetCrudService;

import java.util.List;

public class PlanetServiceTest {
    public static void main(String[] args) {
        // створюємо сервіс для планет
        PlanetCrudService planetCrudService = new PlanetCrudService();
        // створюємо новоу планету
        Planet newPlanet = new Planet();
        newPlanet.setId("NEPTUNE5");
        newPlanet.setName("NEPTUNS");
        // зберігаємо new-планету в БД
        planetCrudService.createPlanet(newPlanet);
        // отримуємо планету за ідентифікатором
        String idPlanet = newPlanet.getId();
        Planet selectDbPlanet = planetCrudService.getPlanetById(idPlanet);
        System.out.println("Planet created: " + selectDbPlanet);
        // отримання списку всіх планет
        List<Planet> allPlanets = planetCrudService.getAllPlanets();
        System.out.println("Get All Planets: " + allPlanets);
        // оновлення планети
        selectDbPlanet.setName("NEPTUNE");
        planetCrudService.updatePlanet(selectDbPlanet);
        // отримання оновленої планети
        Planet updatedPlanet = planetCrudService.getPlanetById("NEPTUNE1");
        System.out.println("Updated Planet: " + updatedPlanet);
//        // видалення планети
//        planetCrudService.deletePlanet(updatedPlanet);
        // отримання списку всіх планет
        List<Planet> remainingPlanets = planetCrudService.getAllPlanets();
        System.out.println("Planets after deleted: ");
        for (Planet planet : remainingPlanets) {
            System.out.println(planet);
        }
        //System.out.println("Planets after deleted: " + remainingPlanets);
    }
}


