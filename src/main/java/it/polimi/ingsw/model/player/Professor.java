package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.Colour;

import java.io.Serializable;

/** the Professor class has only the attribute which defines the professor's colour  */
public class Professor implements Serializable {
    private Colour professorColour;

    public Professor(Colour professorColour) {
        this.professorColour = professorColour;
    }

    /**
     * Method getProfessourColour returns the professor's colour.
     * @return professorColour
     */
    public Colour getProfessorColour() {
        return professorColour;
    }

}
