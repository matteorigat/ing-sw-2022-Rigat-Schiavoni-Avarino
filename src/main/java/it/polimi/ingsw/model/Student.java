package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Colour;

public class Student {
    private Colour colour;


    public Student(Colour colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return colour.toString();

    }
}
