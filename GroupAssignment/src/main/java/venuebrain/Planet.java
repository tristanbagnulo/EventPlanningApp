/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venuebrain;

/**
 *
 * @author Blair
 */
public class Planet {
    private String planetName;
    private int planetRadiusInKm;

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public int getPlanetRadiusInKm() {
        return planetRadiusInKm;
    }

    public Planet(String planetName, int planetRadiusInKm) {
        this.planetName = planetName;
        this.planetRadiusInKm = planetRadiusInKm;
    }

    public void setPlanetRadiusInKm(int planetRadiusInKm) {
        this.planetRadiusInKm = planetRadiusInKm;
    }
}
