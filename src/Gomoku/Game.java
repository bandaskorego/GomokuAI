package Gomoku;

import java.util.ArrayList;

/**
 * Created by mek on 18.05.2017.
 */
public class Game {

    StringBuilder sb = new StringBuilder();

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    ArrayList<Node> nodeList = new ArrayList<Node>();
    int size;

    public Game(int size) {
        this.size = size;
        nodeList = new ArrayList<Node>();
        prepareGame();
    }
    public Game() {
        this.size = 4;
        prepareGame();
    }

    private void prepareGame() {
        createNodes();
        createConnections();
        addNeighboursToList();
    }

    private void addNeighboursToList() {
        for(Node n : nodeList){
            if(n.node1!=null)
                n.neighbours.add(n.node1);
            if(n.node2!=null)
                n.neighbours.add(n.node2);
            if(n.node3!=null)
                n.neighbours.add(n.node3);
            if(n.node4!=null)
                n.neighbours.add(n.node4);
            if(n.node5!=null)
                n.neighbours.add(n.node5);
            if(n.node6!=null)
                n.neighbours.add(n.node6);
            if(n.node7!=null)
                n.neighbours.add(n.node7);
            if(n.node8!=null)
                n.neighbours.add(n.node8);
        }
    }

    private void createConnections() {
        for(Node n : nodeList){
            if(n.getPosX()!=0) {
                //n.getNeighbours().add(getNode(n.getId() - 1));
                n.node4=getNode(n.getId() - 1);
                if(n.getPosY()!=0){
                    //n.getNeighbours().add(nodeList.get(n.getId()-size-1));
                    n.node1=nodeList.get(n.getId()-size-1);
                    //n.getNeighbours().add(nodeList.get(n.getId()-size));
                    n.node2=nodeList.get(n.getId()-size);
                }
                if(n.getPosY()!=size-1){
                    //n.getNeighbours().add(nodeList.get(n.getId()+size-1));
                    n.node6=nodeList.get(n.getId()+size-1);
                    //n.getNeighbours().add(nodeList.get(n.getId()+size));
                    n.node7=nodeList.get(n.getId()+size);
                }
            }
            if(n.getPosX()!=size-1) {
                //n.getNeighbours().add(getNode(n.getId() + 1));
                n.node5=getNode(n.getId() + 1);
                if(n.getPosY()!=0){
                    //n.getNeighbours().add(nodeList.get(n.getId()-size));
                    n.node2=nodeList.get(n.getId()-size);
                    //n.getNeighbours().add(nodeList.get(n.getId()-size+1));
                    n.node3=nodeList.get(n.getId()+-size+1);
                }
                if(n.getPosY()!=size-1){
                    //n.getNeighbours().add(nodeList.get(n.getId()+size));
                    n.node7=nodeList.get(n.getId()+size);
                    //n.getNeighbours().add(nodeList.get(n.getId()+size+1));
                    n.node8=nodeList.get(n.getId()+size+1);

                }
            }
        }
    }
    private void createNodes() {
        for(int i =0;i<size;i++){
            for(int j=0; j<size;j++){
                nodeList.add(new Node(j,i,i*size+j));
            }
        }
    }

    public boolean makeMove(int x, int y, Color color){
        if(x<0 || x>=size || y<0 || y>=size){
            return false;
        } else if(getNode(x,y).getColor()!= Color.EMPTY)
            return false;
        else {
            getNode(x, y).setColor(color);
            return true;
        }
    }
    public boolean checkWinner(int x, int y, Color color) throws Exception {
        if(x<0 || x>=size || y<0 || y>=size ){
            throw new Exception("X lub Y nieprawidlowe");
        }
        else if(getNode(x,y).getColor()!= Color.EMPTY) {
            throw new Exception("Pole jest juz zajęte");
        }
        else if(color == Color.EMPTY) {
            throw new Exception("Ten kolor nie moze byc empty");
        }
        else
            getNode(x,y).setColor(color);

        int counter = 1;

        Node node = getNode(x,y);
        Node actual = getNode(x,y);

        //Poziomo
        while(actual.node4!=null && actual.node4.getColor()==node.getColor()){
            counter++;
            actual = actual.node4;
        }
        actual = getNode(x,y);
        while(actual.node5!=null && actual.node5.getColor()==node.getColor()){
            counter++;
            actual = actual.node5;
        }
        if(counter>=5)
            return true;
        else {
            counter = 1;
            actual = getNode(x,y);
        }

        // Pionowo
        while(actual.node2!=null && actual.node2.getColor()==node.getColor()){
            counter++;
            actual = actual.node2;
        }
        actual = getNode(x,y);
        while(actual.node7!=null && actual.node7.getColor()==node.getColor()){
            counter++;
            actual = actual.node7;
        }
        if(counter>=5)
            return true;
        else {
            counter = 1;
            actual = getNode(x,y);
        }

        // Skos 1
        while(actual.node1!=null && actual.node1.getColor()==node.getColor()){
            counter++;
            actual = actual.node1;
        }
        actual = getNode(x,y);
        while(actual.node8!=null && actual.node8.getColor()==node.getColor()){
            counter++;
            actual = actual.node8;
        }
        if(counter>=5)
            return true;
        else {
            counter = 1;
            actual = getNode(x,y);
        }

        // Skos 2
        while(actual.node3!=null && actual.node3.getColor()==node.getColor()){
            counter++;
            actual = actual.node3;
        }
        actual = getNode(x,y);
        while(actual.node6!=null && actual.node6.getColor()==node.getColor()){
            counter++;
            actual = actual.node6;
        }
        if(counter>=5)
            return true;
        else {
            counter = 1;
            actual = getNode(x,y);
        }

        if(counter>=5)
            return true;
        else
            return false;

    }

    Node getNode(int x, int y){
        return nodeList.get((size*y)+x);
    }
    Node getNode (int id){
        return nodeList.get(id);
    }

    @Override
    public String toString() {
        sb.append("Aktualna rozgrywka\n\n");
        sb.append("    0   1   2   3   4   5   6   7   8   9  10  11  12  13  14");
        for(Node n : nodeList){
            if(n.getPosX()==0) {
                if(n.getPosY()<10){
                    sb.append("\n " + n.getPosY() + "| ");
                }
                else
                    sb.append("\n" + n.getPosY() + "| ");
            }
            if(n.getColor()==Color.BLACK)
                sb.append("B | ");
            else if(n.getColor()==Color.WHITE)
                sb.append("W | ");
            else
                sb.append("  | ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
        return sb.toString();
    }
}
