package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;


public class Student {
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
        switch(colorInt) {
            case (0) : return "🟢"; //""\033[38;2;31;224;44mGreen\033[0m";
            case (1) : return "🔴"; //"\033[31mRed\033[0m";
            case (2) : return "🟡"; //"\033[93mYellow\033[0m";
            case (3) : return "🟣"; //"\033[38;2;249;177;250mPink\033[0m";
            case (4) : return "🔵"; //"\033[38;2;85;99;250mBlue\033[0m";
        }
        return "";
    }
}

