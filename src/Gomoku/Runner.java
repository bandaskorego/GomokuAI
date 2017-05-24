package Gomoku;

import java.util.ArrayList;

import static java.lang.System.nanoTime;

/**
 * Created by mek on 18.05.2017.
 */
public class Runner {
    public static void main(String[] args) throws Exception {
        Game game = new Game(9);
        System.out.println("test");

//        long c = System.nanoTime();
//        c=c-System.nanoTime();
//        System.out.println(c);
//        long d = System.currentTimeMillis();
//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.nanoTime());
//        System.out.println(game.checkWinner(0,0, Color.BLACK));
//        System.out.println(game.checkWinner(0,1, Color.BLACK));
//        System.out.println(game.checkWinner(0,2, Color.BLACK));
//        System.out.println(game.checkWinner(1,3, Color.BLACK));
//        System.out.println(game.checkWinner(1,1, Color.BLACK));
//        System.out.println(game.checkWinner(1,2, Color.BLACK));
//        System.out.println(game.checkWinner(2,3, Color.BLACK));
//        System.out.println(game.checkWinner(2,1, Color.BLACK));
//        System.out.println(game.checkWinner(2,2, Color.BLACK));


        long c = System.nanoTime();
        ArrayList<Node> nodes = new ArrayList<>();
        int j=0;
        for( int i =0 ; i<1000000; i++){
            for(Node n : game.getNodeList()) {
                j++;
                nodes.add(n);
            }
        }
        c=System.nanoTime()-c;
        System.out.println(c);

       // MinMax minmax = new MinMax(game,Color.BLACK,3);

        game.toString();



    }
}
