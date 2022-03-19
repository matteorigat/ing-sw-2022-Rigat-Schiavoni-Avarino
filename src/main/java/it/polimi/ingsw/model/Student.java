package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;


public class Student {
    private Colour colour;



    public Student(Colour colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return colour.toString();

    }

    public Colour getColour(){
        return this.colour;
    }





}

