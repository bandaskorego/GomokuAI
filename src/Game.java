import java.util.Arrays;

/**
 * Created by mek on 14.05.2017.
 */
public class Game {
    final int size;
    public int[][] board;
    private int turn = 2;

    public Game(int size) {
        this.size = size;
        board = new int[size][size];
    }

    public Game() {
        this.size = 15;
        board = new int[size][size];
    }


    public String move(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size)
            return "Out of board";
        else if (board[row][col] != 0)
            return "Illegal move";
        else {
            board[row][col] = turn;
            String s = "Placed " + turn + " on [" + row + "," + col + "].\n";
            changeTurn();
            return s;
        }
    }

    public String move(int row, int col, int val) {
        if (row < 0 || col < 0 || row >= size || col >= size)
            return "Out of board";
        else if (board[row][col] != 0)
            return "Illegal move";
        else {
            board[row][col] = val;
            String s = "Placed " + turn + " on [" + row + "," + col + "].\n";
            return s;
        }
    }

    private void changeTurn() {
        if (turn == 2)
            turn = 3;
        else
            turn = 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BOARD\n");
        for (int i = 0; i < board.length; i++) {
            sb.append("\n");
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0)
                    sb.append(board[i][j] + " | ");
                else
                    sb.append("  | ");
            }
        }
        return sb.toString();
    }

    public int checkWinner() {
        int strike = 0;
        int player = 2;
        for (int row = 0; row < board.length; row++) {
            strike = 0;
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != 0) {
                    if (board[row][col] == player) {
                        strike++;
//                        System.out.printf(" Strike : " + strike);
                        if (strike == 5)
                            return player;
                    } else {
                        strike = 1;
                        player = board[row][col];
                    }
                } else
                    strike = 0;
                //board[row][col]=7;
            }
        }
        //row does not mean row in this case xD
        for (int row = 0; row < board.length; row++) {
            strike = 0;
            for (int col = 0; col < board[0].length; col++) {
                if (board[col][row] != 0) {
                    if (board[col][row] == player) {
                        strike++;
                        //System.out.printf(" Strike : "+ strike);
                        if (strike == 5)
                            return player;
                    } else {
                        strike = 1;
                        player = board[col][row];
                    }
                } else
                    strike = 0;
                //board[col][row] = 7;
            }
        }
//        //od gory do dolu od lewej do prawej (lewa dolna strona)
        for (int i = 0; i < board.length; i++) {
            strike = 0;
            int tmp1 = i;
            int tmp2 = 0;
            while (tmp1 < size && tmp2 < size) {
//                board[tmp1][tmp2]=5;
                if (board[tmp1][tmp2] != 0 ) {
                    if (board[tmp1][tmp2] == player) {
                        strike++;
                        if (strike == 5)
                            return player;
                    } else {
                        player = board[tmp1][tmp2];
                        strike = 1;
                    }
                } else {
                    strike = 0;
                }
                //board[tmp1][tmp2] = 5;
                tmp1++;
                tmp2++;
            }
//(prawa gorna strona)
            tmp1 = 0;
            tmp2 = i;
            strike = 0;
            while (tmp1 < size && tmp2 < size) {
                //               board[tmp1][tmp2]=6;
                if (board[tmp1][tmp2] != 0) {
                    if (board[tmp1][tmp2] == player) {
                        strike++;
                        if (strike == 5)
                            return player;
                    } else {
                        player = board[tmp1][tmp2];
                        strike = 1;
                    }
                } else {
                    strike = 0;
                }
                // board[tmp1][tmp2] = 5;
                tmp1++;
                tmp2++;
            }

//(lewa gorna strona)
            tmp1 = 0;
            tmp2 = i;
            strike = 0;
            while (tmp1 <= size - 1 && tmp2 >= 0) {
                if (board[tmp1][tmp2] != 0) {
                    if (board[tmp1][tmp2] == player) {
                        strike++;
                        //System.out.println("Strike : " + strike);
                        //board[tmp1][tmp2]=9;
                        if (strike == 5)
                            return player;
                    } else {
                        player = board[tmp1][tmp2];
                        strike = 1;
                    }
                } else {
                    strike = 0;
                }
                //board[tmp1][tmp2] = 7;
                tmp1++;
                tmp2--;
            }
            //(prawa dolna strona)
            tmp1 = i;
            tmp2 = size - 1;
            strike = 0;
            while (tmp1 <= size - 1 && tmp2 >= 0) {
                if (board[tmp1][tmp2] != 0) {
                    if (board[tmp1][tmp2] == player) {
                        strike++;
//                        System.out.println("Strike : " + strike);
//                        System.out.println(tmp1);
//                        System.out.println(tmp2);
                        if (strike == 5) {
                            System.out.println(tmp1);
                            System.out.println(tmp2);
                            System.out.println(tmp2);
                            return player;
                        }
                    } else {
                        player = board[tmp1][tmp2];
                        strike = 1;
                    }
                } else {
                    strike = 0;
                }
                // board[tmp1][tmp2] = 6;
                tmp1++;
                tmp2--;
            }
        }


        return 0;
    }
}
