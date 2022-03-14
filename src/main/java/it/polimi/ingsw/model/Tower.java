package it.polimi.ingsw.model;

public class Tower {
    private TowerColour TColour;


    public TowerColour getTColour() {
        return TColour;
    }

    public void setTColour(TowerColour TColour) {
        this.TColour = TColour;
    }

    @Override
    public String toString() {
        return "Tower{" +
                "TColour=" + TColour +
                '}';
    }
}
