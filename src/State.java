import java.util.ArrayList;

/**
 * Created by mek on 16.05.2017.
 */

public class State {


    int color;
    int points;
    int depth;
    int[][] state;

    boolean ifMax;
    boolean isFinished;

    State branch;

    ArrayList<Move> myRocks = new ArrayList<Move>();
    ArrayList<Move> moveList = new ArrayList<Move>();
    ArrayList<Move> possibleMoves = new ArrayList<Move>();
    ArrayList<State> childs = new ArrayList<State>();



    public State(int[][] state, ArrayList<Move> moveList, boolean findMax) {
        this.state = state;
        this.ifMax=findMax;
        this.moveList = moveList;
        for (Move m : moveList) {
            state[m.row][m.col] = m.val;
            myRocks.add(m);
        }
        calculatePoints();
        for (Move m : moveList) {
            state[m.row][m.col] = 0;
        }
    }

    public void buildTree(){
        for(Move m : possibleMoves){
            ArrayList<Move> movelist = new ArrayList<Move>(moveList);
            movelist.add(m);

            ArrayList<Move> possiblemoves = new ArrayList<Move>(possibleMoves);
            possiblemoves.remove(m);

          //  childs.add(new State(state, movelist, ));
        }
    }

    public State(int[][] state, int color, int depth) {
        this.state = state;
        this.color = color;
        this.depth = depth;

        findAllPossibleMovesAndRocks();
    }

    private void findAllPossibleMovesAndRocks(){
        for (int row = 0; row < state.length; row++) {
            for (int col = 0; col < state[0].length; col++) {
                if (state[row][col] == 0)
                    possibleMoves.add(new Move(row, col, color));
                else if (state[row][col] == color) {
                    myRocks.add(new Move(row, col, color));
                }
            }
        }
    }

    public void findValue() {
        if (ifMax) {
            points = -100000;
            for (State s : childs) {
                if (points < s.points) {
                    branch = s;
                    points = s.points;
                }
            }
        } else {
            points = 1000000;
            for (State s : childs) {
                if (points > s.points) {
                    branch = s;
                    points = s.points;
                }
            }
        }
    }

    public void calculatePoints() {
        for (Move rock : myRocks)
            points += checkSurrounding(rock.row, rock.col);
    }

    private int checkSurrounding(int row, int col) {
        int score = 0;
        if (row != 0) {
            if (state[row - 1][col] == 0)
                score++;
            if (col != 0)
                if (state[row - 1][col - 1] == 0)
                    score++;
            if (col != state.length - 1)
                if (state[row - 1][col + 1] == 0)
                    score++;
        }
        if (row != state.length - 1) {
            if (state[row + 1][col] == 0)
                score++;
            if (col != 0)
                if (state[row + 1][col - 1] == 0)
                    score++;
            if (col != state.length - 1)
                if (state[row + 1][col + 1] == 0)
                    score++;
        }
        if (col != 0)
            if (state[row][col - 1] == 0)
                score++;
        if (col != state.length - 1)
            if (state[row][col + 1] == 0)
                score++;
        return score;
    }
}
