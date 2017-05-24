import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by mek on 17.05.2017.
 */
public class GameTree {
    int[][] state;

    ArrayList<Move> possibleMoves = new ArrayList<Move>();
    ArrayList<State> leafs = new ArrayList<State>();

    public GameTree(int[][] state) {
        this.state = state;
        findPossibleMoves();
    }

    private void findPossibleMoves(){
        for (int row = 0; row < state.length; row++)
            for (int col = 0; col < state[0].length; col++)
                if(state[row][col]==0)
                    possibleMoves.add(new Move(row,col,2));
    }

//    public void createLeafs(){
//        for (Move m : possibleMoves) {
//            ArrayList<Move> list = new ArrayList<Move>();
//            list.add(m);
//            leafs.add(new State(state, list));
//        }
//    }
}
