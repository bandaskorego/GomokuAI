package Gomoku;

import java.util.ArrayList;

/**
 * Created by mek on 18.05.2017.
 */
public class Node {

    private int posX;
    private int posY;
    Color color;
    int id;

    ArrayList<Node> neighbours;
    Node node1, node2, node3, node4, node5, node6, node7, node8;

    public Node(int posX, int posY, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public Node(int i, int j, int id) {
        this.posX=i;
        this.posY=j;
        this.id=id;
        this.color= Color.EMPTY;
        neighbours = new ArrayList<>();
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = neighbours;
    }
}
