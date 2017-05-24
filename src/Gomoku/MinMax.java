package Gomoku;

import java.util.ArrayList;

/**
 * Created by mek on 23.05.2017.
 */
public class MinMax {
    Game game;
    ArrayList<Node> myRocks = new ArrayList<>();
    ArrayList<Node> opponentRocks = new ArrayList<>();
    ArrayList<Node> availableMoves = new ArrayList<>();
    ArrayList<Node> moveList = new ArrayList<>();
    Statistic stats;
    long time;
    Color myColor;
    int depth;
    boolean maxTurn;
    int points;

    ArrayList<MinMax> childs = new ArrayList<>();

    public MinMax(Game game, Color myColor, int depth) throws Exception {
        this.game = game;
        this.myColor = myColor;
        this.depth = depth;
        maxTurn = true;
        points = 0;
        stats = new Statistic();


        //Inicjalizacja pierwszego wywolania minmax
        for (Node n : game.getNodeList()) {
            if (n.getColor() == myColor) {
                myRocks.add(new Node(n.getPosX(), n.getPosY(), n.getColor()));
            } else if (n.getColor() != Color.EMPTY)
                opponentRocks.add(new Node(n.getPosX(), n.getPosY(), n.getColor()));
            else
                availableMoves.add(new Node(n.getPosX(), n.getPosY(), n.getColor()));
        }


        // Zaczynamy budować drzewo
        for (int i = 0; i < availableMoves.size(); i++) {


            ArrayList<Node> myRocks1 = nowaLista(myRocks);
            ArrayList<Node> opponentRocks1 = nowaLista(opponentRocks);
            ArrayList<Node> availableMoves1 = nowaLista(availableMoves);
            ArrayList<Node> moveList1 = nowaLista(moveList);
            availableMoves1.remove(i);


            moveList1.add(new Node(availableMoves.get(i).getPosX(), availableMoves.get(i).getPosY(), myColor));
            if (maxTurn)
                myRocks1.add(new Node(availableMoves.get(i).getPosX(), availableMoves.get(i).getPosY(), myColor));
            else {
                opponentRocks.add(availableMoves.get(i));
                throw new Exception("Tutaj powinie zawsze dodawac do listy moich kamieni");
            }


            //System.out.println("Kopiowanie kolejek : " + c);


            childs.add(new MinMax(game, myRocks1, opponentRocks1, availableMoves1, moveList1, myColor, depth - 1, !maxTurn,stats));

        }

        // Ustawianie max
        for (MinMax m : childs) {
            if (m.points > points)
                points = m.points;
        }

        System.out.println(stats.toString());
    }


    public MinMax(Game game, ArrayList<Node> myRocks1, ArrayList<Node> opponentRocks1, ArrayList<Node> availableMoves1, ArrayList<Node> moveList1,
                  Color myColor, int depth, boolean maxTurn, Statistic stats) throws Exception {
        this.stats = stats;
        this.game = game;
        this.myRocks = myRocks1;
        this.opponentRocks = opponentRocks1;
        this.availableMoves = availableMoves1;
        this.moveList = moveList1;
        this.myColor = myColor;
        this.depth = depth;
        this.maxTurn = maxTurn;
        points = 0;

        if (depth == 0) {
            points = countPoints();
        } else {
            //Dodaj kamienie do planszy

            assignMovePath();

            time = System.nanoTime();
            // Wez ostatni z listy ruchow dodaj do planszy i sprawdz czy nie ma wygranej/przegranej
            if (game.checkWinner(moveList.get(moveList.size() - 1).getPosX(), moveList.get(moveList.size() - 1).getPosY(), moveList.get(moveList.size() - 1).getColor())) {
                if (maxTurn)
                    points = 1000000000;
                else
                    points = -1000000000;

            }
            stats.setCheckingWinner(stats.getCheckingWinner()+(System.nanoTime()-time));

            //Usuwanie kamieni

            unAssignMovePath();


            if (points != 1000000000 || points != -1000000000) {
                // Zaczynamy budować drzewo
                for (int i = 0; i < availableMoves.size(); i++) {

                    myRocks1 = nowaLista(myRocks);
                    opponentRocks1 = nowaLista(opponentRocks);
                    availableMoves1 = nowaLista(availableMoves);
                    moveList1 = nowaLista(moveList);
                    availableMoves1.remove(i);

                    Color c = myColor;
                    if (!maxTurn)
                        if (c == Color.BLACK)
                            c = Color.WHITE;
                        else
                            c = Color.BLACK;
                    moveList1.add(new Node(availableMoves.get(i).getPosX(), availableMoves.get(i).getPosY(), c));
                    if (maxTurn)
                        myRocks1.add(new Node(availableMoves.get(i).getPosX(), availableMoves.get(i).getPosY(), c));
                    else
                        opponentRocks.add(new Node(availableMoves.get(i).getPosX(), availableMoves.get(i).getPosY(), c));

                    childs.add(new MinMax(game, myRocks1, opponentRocks1, availableMoves1, moveList1, myColor, depth - 1, !maxTurn,stats));
                }

                // Ustawianie max
                if (maxTurn) {
                    points = 0;
                    for (MinMax m : childs) {
                        if (m.points > points)
                            points = m.points;
                    }
                } else {
                    points = 100000;
                    for (MinMax m : childs) {
                        if (m.points < points)
                            points = m.points;
                    }
                }
            }
        }

    }

    private ArrayList<Node> nowaLista(ArrayList<Node> myRocks) {
        time = System.nanoTime();
        ArrayList<Node> nowa = new ArrayList<>();
        for (Node n : myRocks) {
            nowa.add(new Node(n.getPosX(), n.getPosY(), n.getColor()));
        }
        stats.setCreatingTables(stats.getCreatingTables()+(System.nanoTime()-time));
        return nowa;
    }

    private int minmax(Game game, ArrayList<Node> myRocks, ArrayList<Node> opponentRocks, ArrayList<Node> availableMoves, ArrayList<Node> moveList, Color myColor, int depth, boolean maxturn) throws Exception {
        if (depth == 0) {
            return countPoints();
        }
        return 0;
    }

    private int countPoints() throws Exception {
        //Dodaj kamienie do planszy
        assignMovePath();

        time = System.nanoTime();
        // Wez ostatni z listy ruchow dodaj do planszy i sprawdz czy nie ma wygranej/przegranej
        if (game.checkWinner(moveList.get(moveList.size() - 1).getPosX(), moveList.get(moveList.size() - 1).getPosY(), moveList.get(moveList.size() - 1).getColor())) {
            stats.setCheckingWinner(stats.getCheckingWinner()+(System.nanoTime()-time));
            if (!maxTurn) {
                unAssignMovePath();
                return 1000000000;
            } else {
                unAssignMovePath();
                return -1000000000;
            }
        }
        //Policz punkty
        time = System.nanoTime();
        int count = 0;
        for (Node n : myRocks) {
            for (Node neighbour : game.getNode(n.getPosX(), n.getPosY()).neighbours) {
                if (neighbour.getColor() == Color.EMPTY)
                    count++;
            }
        }
        stats.setCalculatingPoints(stats.getCalculatingPoints()+(System.nanoTime()-time));

        //Usun kamienie z planszy
        unAssignMovePath();
        return count;
    }

    private void unAssignMovePath() {
        time = System.nanoTime();
        for (int i = 0; i < moveList.size(); i++) {
            game.getNode(moveList.get(i).getPosX(), moveList.get(i).getPosY()).setColor(Color.EMPTY);
        }
        stats.setAssingUnassing(stats.getAssingUnassing()+(System.nanoTime()-time));
    }

    private void assignMovePath() {
        time = System.nanoTime();
        for (int i = 0; i < moveList.size() - 1; i++) {
            game.makeMove(moveList.get(i).getPosX(), moveList.get(i).getPosY(), moveList.get(i).getColor());
        }
        stats.setAssingUnassing(stats.getAssingUnassing()+(System.nanoTime()-time));
    }


}
