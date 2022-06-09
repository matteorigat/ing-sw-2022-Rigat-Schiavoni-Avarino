package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.Colour;

import java.io.Serializable;

/** Class Student has a Constructor, a getter and an override tostring method which returns a coloured circle of the corresponding color */
public class Student implements Serializable {
    private Colour colour;

    public Student(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour(){
        return this.colour;
    }

    @Override
    public String toString() {
        int colorInt = colour.ordinal();
        return switch (colorInt) {
            case (0) -> "🟢"; //""\033[38;2;31;224;44mGreen\033[0m";
            case (1) -> "🔴"; //"\033[31mRed\033[0m";
            case (2) -> "🟡"; //"\033[93mYellow\033[0m";
            case (3) -> "🟣"; //"\033[38;2;249;177;250mPink\033[0m";
            case (4) -> "🔵";
            default -> //"\033[38;2;85;99;250mBlue\033[0m";
                    "";
        };
    }
}

