package com.galvanize.autos;

public class UpdateAuto {
    String color;
    String owner;

    public UpdateAuto() {
    }

    public UpdateAuto(String color, String owner) {
        this.color = color;
        this.owner = owner;
    }

    public String getColor() {
        return color;
    }

    public String getOwner() {
        return owner;
    }
}
