package src.main.java.HW2;

public class Node implements Comparable <Node>{
    private String key;
    private String value;
    private Node leftChild;
    private Node rightChild;

    public Node(String key, String value) {
        this.key=key;
        this.value=value;
        this.leftChild=null;
        this.rightChild=null;
    }
    public Node(){
        this.key=null;
        this.value=null;
        this.leftChild=null;
        this.rightChild=null;
    }

//methods to get and set instance variables
    public String getKey(){
        try {
            return this.key;
        //if node is null
        } catch (Exception e ) {
            return null;
        }
    }
    public String getValue(){
        try {
            return this.value;
            //if node is null
        } catch (Exception e) {
            return null;
        }
    }
    public Node getLeftChild() {
        if (this.leftChild != null) {
            return this.leftChild;
        } else {
            return new Node();
        }
    }
    public void setLeftChild(Node leftChild) {this.leftChild=leftChild;}
    public Node getRightChild(){
        if (this.rightChild != null) {
            return this.rightChild;
        } else {
            return new Node();
        }
    }
    public void setRightChild(Node rightChild) {this.rightChild=rightChild;}
    public void setValue(String value) {this.value=value;}
    public String toString() {
        return "Node key: "+this.getKey()+" Node value: "+this.getValue()+" Node left child: "+
                this.getLeftChild().getKey()+" Node right child: "+this.getRightChild().getKey()+"\n";
    }

    public int compareTo(Node node) {
        return this.getKey().compareTo(node.getKey());

    }
}
