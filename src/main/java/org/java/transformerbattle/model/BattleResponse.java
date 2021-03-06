package org.java.transformerbattle.model;


import java.io.Serializable;

public class BattleResponse implements Serializable {

    private int noOfBattles;
    private String winningTeam;
    private String losingTeamSurvivors;

    public int getNoOfBattles() {
        return noOfBattles;
    }

    public void setNoOfBattles(int noOfBattles) {
        this.noOfBattles = noOfBattles;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    public String getLosingTeamSurvivors() {
        return losingTeamSurvivors;
    }

    public void setLosingTeamSurvivors(String losingTeamSurvivors) {
        this.losingTeamSurvivors = losingTeamSurvivors;
    }
}
