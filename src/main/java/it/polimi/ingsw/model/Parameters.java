package it.polimi.ingsw.model;

public class Parameters {
    public static int numPlayers;
    public static int numClouds;
    public static int numCloudStudents;
    public static int numTowers;
    public static int entranceStudents;

    public static int numIsland = 12;
    public static int numStudents = 130;


    public static void setParameters(int players){
        numPlayers = players;
        numClouds = players; //numero nuvole uguale a numero giocatori

        if (players == 2){
            numCloudStudents = 3;
            numTowers = 8;
            entranceStudents = 7;
        } else{
            numCloudStudents = 4;
            numTowers = 6;
            entranceStudents = 9;
        }
    }

}
