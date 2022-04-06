package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;

public class Professor {
    private Colour professorColour;

    public Professor(Colour professorColour) {
        this.professorColour = professorColour;
    }

    //Gets the professor color
    public Colour getProfessorColour() {
        return professorColour;
    }

}
