package Gomoku;

/**
 * Created by mek on 24.05.2017.
 */
public class Statistic {


    private long creatingTables=0;
    private long assingUnassing=0;
    private long checkingWinner=0;
    private long calculatingPoints=0;

    @Override
    public String toString() {
        return "Statistic{" +
                "creatingTables=" + creatingTables/1000000 +
                ", assingUnassing=" + assingUnassing/1000000 +
                ", checkingWinner=" + checkingWinner/1000000 +
                ", calculatingPoints=" + calculatingPoints/1000000 +
                '}';
    }

    public long getCreatingTables() {
        return creatingTables;
    }

    public void setCreatingTables(long creatingTables) {
        this.creatingTables = creatingTables;
    }

    public long getAssingUnassing() {
        return assingUnassing;
    }

    public void setAssingUnassing(long assingUnassing) {
        this.assingUnassing = assingUnassing;
    }

    public long getCheckingWinner() {
        return checkingWinner;
    }

    public void setCheckingWinner(long checkingWinner) {
        this.checkingWinner = checkingWinner;
    }

    public long getCalculatingPoints() {
        return calculatingPoints;
    }

    public void setCalculatingPoints(long calculatingPoints) {
        this.calculatingPoints = calculatingPoints;
    }
}
