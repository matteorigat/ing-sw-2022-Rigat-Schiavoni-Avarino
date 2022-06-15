package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.Colour;

import java.io.Serializable;

/** the professor class has only onw attribute which defined the professor colour  */
public class Professor implements Serializable {
    private Colour professorColour;

    public Professor(Colour professorColour) {
        this.professorColour = professorColour;
    }

    /**
     * Method getProfessourColour returns a professour by his colour.
     * @return professorColour
     */
    public Colour getProfessorColour() {
        return professorColour;
    }

}
