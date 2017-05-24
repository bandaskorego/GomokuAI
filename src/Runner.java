import java.util.Arrays;

/**
 * Created by mek on 14.05.2017.
 */
public class Runner {
    public static void main(String[] args) {
        Game game = new Game();

        State s = new State(game.board, 2, 2);
        System.out.println("Size : " + s.myRocks.size());
        System.out.println("Possible moves : " + s.possibleMoves.size());

        // System.out.println(game.move(14, 14,2));
        // State s = new State();
        // s.state = game.board;
        //s.myRocks.add(new Move(14,14,2));


        // s.calculatePoints();
       //   System.out.println(s.points);
          int[][] a = cloneArray(game.board);


        System.out.println(game.move(11, 14));
        System.out.println(game.move(12, 4, 3));
        System.out.println(game.move(13, 2, 3));
        System.out.println(game.move(12, 3, 3));
        System.out.println(game.move(11, 4, 3));
        System.out.println(game.move(10, 5, 3));
        System.out.println(game.move(18, 6, 3));
        System.out.println(game.move(3, 3));
        System.out.println(game.move(2, 13));
        System.out.println(game.move(2, 4));
        System.out.println(game.move(3, 15, 2));
        System.out.println(game.move(4, 7, 2));
        System.out.println(game.move(5, 8, 2));
        System.out.println(game.move(6, 9, 2));
        System.out.println(game.move(8, 6, 2));
        System.out.println(game.move(7, 5, 2));
        System.out.println(game.move(6, 4, 2));
        System.out.println(game.move(5, 3, 2));
        System.out.println(game.move(4, 2, 2));
        System.out.println(game.move(3, 1, 2));
//        System.out.println(game.move(5, 13));

//        GameTree gametree = new GameTree(game.board);
//        gametree.createLeafs();
//        int d = 0;
//        for (State s : gametree.leafs) {
//            System.out.print(s.points);
//            d++;
//            if (d % game.board.length == 0)
//                System.out.println();
//        }

        //  System.out.println("Winner : " + game.checkWinner());
        //   System.out.println(game.toString());

        int c = 0;
        long startTime = System.currentTimeMillis();


        for (int k = 0; k < 10000000; k++) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if (a[i][j] == 0)
                        c++;
                }
            }
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println(estimatedTime + ", c = " + c);


    }


    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
}
