package com.galvanize.autos;

public class Automobiles {
    int year;
    String make;
    String model;
    String color;
    String owner;
    String vin;

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getOwner() {
        return owner;
    }

    public String getVin() {
        return vin;
    }

    public Automobiles() {
    }

    public Automobiles(int year, String make, String model, String color, String owner, String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.owner = owner;
        this.vin = vin;
    }
}
