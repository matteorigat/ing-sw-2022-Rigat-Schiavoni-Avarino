package it.polimi.ingsw.model;

public class Professor {
    private Colour ProfessorColour;


    public Colour getProfessorColour() {
        return ProfessorColour;
    }

    public void setProfessorColour(Colour professorColour) {
        ProfessorColour = professorColour;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "ProfessorColour=" + ProfessorColour +
                '}';
    }
}
