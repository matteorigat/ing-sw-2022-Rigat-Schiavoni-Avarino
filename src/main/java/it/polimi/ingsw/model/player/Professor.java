package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.Colour;

import java.io.Serializable;
import java.util.ArrayList;

/** the professor class has only onw attribute which defined the professor colour */
public class Professor implements Serializable {
    private Colour professorColour;

    public Professor(Colour professorColour) {
        this.professorColour = professorColour;
    }

    //Gets the professor color
    public Colour getProfessorColour() {
        return professorColour;
    }

}
