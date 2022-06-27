package it.polimi.ingsw.model;

/** Class parameters permits us to set numPlayers,numClouds,numCloudStudents,numTowers,gameMode,
 * depending on the number of players of the match and the game mode (expert or not). */


public class Parameters {
    public static int numPlayers;
    public static int numClouds;
    public static int numCloudStudents;
    public static int numTowers;
    public static int entranceStudents;
    public static boolean expertMode;
    public static int numIslands = 12;


    /**
     * setParameters method sets the parameters:
     * @param players of type integer
     * @param mode of type boolean
     */
    public static void setParameters(int players, boolean mode){
        numPlayers = players;
        numClouds = players;
        expertMode = mode;

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
