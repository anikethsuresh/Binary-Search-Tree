
package src.main.java.HW2;

import src.main.java.HW2.Node;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Table{
    private Node head;

    public Table(){
        this.head= new Node();
    }

    //using in order traversal to search
    public Node searchNodes(Node parent, Node searchNode) {
            //look at parent
            if (parent.getKey().equals(searchNode.getKey())) {
                return parent;
                // if value is not found
            } else if ((parent.getRightChild().getKey()==null && parent.getLeftChild().getKey()==null)){
                parent = new Node ();
                return parent;
            } else {
                //loop through all nodes and check keys
                if (parent.compareTo(searchNode) > 0) {
                    if (parent.getLeftChild().getKey() != null) {
                        //recursion to the left
                        parent = searchNodes(parent.getLeftChild(), searchNode);
                    } else {
                        //if parent is more than search key but left child is null, key does not exist so return null
                        parent = new Node ();
                        return parent;                    }
                } else if (parent.compareTo(searchNode) < 0) {
                    if (parent.getRightChild().getKey()!= null) {
                        //recursion to the right
                        parent = searchNodes(parent.getRightChild(), searchNode);
                    } else {
                        //if parent is less than search key but right child is null, key does not exist so return null
                        parent = new Node ();
                        return parent;                    }
                }
            }
        //    }
            return parent;
        }

    public Node insertNodes(Node parent, Node insertNode) {
        //look at parent
        if (parent.getKey()==null) {
            parent = insertNode;
            return parent;
        }
        //loop through all nodes and check keys
        //in order traversal
         if (parent.compareTo(insertNode) > 0) {
                parent.setLeftChild(insertNodes(parent.getLeftChild(),insertNode));
         } else  if (parent.compareTo(insertNode) < 0) {
             parent.setRightChild(insertNodes(parent.getRightChild(),insertNode));
            }
        return parent;
    }

    public int returnNodes(Node parent, int count, String traversalType) {
        count++;
        if (parent.getKey() == null) {
            return 0;
        }
        //print nodes, order varies based on function input
        if (traversalType.equals("inorder")) {
            returnNodes(parent.getLeftChild(), count, traversalType);
            System.out.println("Name: " + parent.getKey());
            System.out.println("Address: " + parent.getValue());
            returnNodes(parent.getRightChild(), count, traversalType);
        } else         if (traversalType.equals("preorder")) {
            System.out.println("Name: " + parent.getKey());
            System.out.println("Address: " + parent.getValue());
            returnNodes(parent.getLeftChild(), count, traversalType);
            returnNodes(parent.getRightChild(), count, traversalType);
        } else         if (traversalType.equals("postorder")) {
            returnNodes(parent.getLeftChild(), count, traversalType);
            returnNodes(parent.getRightChild(), count, traversalType);
            System.out.println("Name: " + parent.getKey());
            System.out.println("Address: " + parent.getValue());
        }
        return count;
    }

    //if both children are null, parent is min val
    //else look at left (since it is always smaller than right
    //if left is null look at right
    public Node minValue(Node parent) {
            if (parent.getLeftChild().getKey()==null && parent.getRightChild().getKey()==null) {
                return parent;
            } else {
                if (parent.getLeftChild().getKey()!=null) {
                    parent = minValue(parent.getLeftChild());
                } else {
                    parent=minValue(parent.getRightChild());
                }
            }

            return parent;

        }

    public Node deleteNode(Node parent, Node deleteNode, boolean delete) {
        //if parent is blank, at end of tree
         if (parent.getKey() == null) {
            return parent;
        }
        //look in left child if key to remove is less than parent key
        // in right child if key to remove is more than parent key
        if (parent.compareTo(deleteNode) > 0) {
            parent.setLeftChild(deleteNode(parent.getLeftChild(), deleteNode, delete));
        } else if (parent.compareTo(deleteNode) < 0) {
            parent.setRightChild(deleteNode(parent.getRightChild(), deleteNode,delete));
        } else {
            //if parent key is key to delete
            delete=true;
            //if no right child, must make new parent left child and vice versa
            if (parent.getRightChild().getKey() == null) {
                return parent.getLeftChild();
            } else if (parent.getLeftChild().getKey() == null) {
                return parent.getRightChild();
            }
            //otherwise look in right child, since right > parent > left and get min val
            parent = minValue(parent.getRightChild());
            parent.setRightChild(deleteNode(parent.getRightChild(), parent,delete));

        }
        return parent;

    }






    public String lookUp(String key){
        // check if null
        if (this.head.getKey()==null) {
            return null;
        } else {
            Node searchNode = new Node(key, "NA");
            //if search key found, return true else false
            Node result=searchNodes(this.head, searchNode);
            if (result.getKey()!=null) {
                return result.getValue();
            } else {
                return null;
            }
        }


    }
    public boolean insert(String key, String value) {
        //if list is empty
        if (this.head.getKey()==null) {
            this.head = new Node(key,value);
            return true;
        }  else {
            Node insertNode = new Node(key, value);
            Node result = insertNodes(this.head, insertNode);
            if (result.getKey() != null) {
                return true;
            }
        }
        return false;

    }


    public boolean delete(String key){
        if (this.head.getKey()==null) {
            return false;
        }
        Node deleteNode = new Node(key,"NA");
        //if node is not found, return false
        if (lookUp(key)==null) {
            return false;
        }
        this.head=deleteNode(this.head,deleteNode,false);

        return true;

    }

    public boolean update(String key, String newValue) {
        //look through nodes to find search key & update value
        Node value = searchNodes(this.head, new Node(key,newValue));
        //if search key is found
        if (value.getKey()!=null) {
            value.setValue(newValue);
            return true;
        }
      //if key does not exist
        return false;
    }

    public int displayAll(){
        //if head is blank ie there are no nodes
        if (this.head.getKey()==null) {
            return 0;
        }
        int count = returnNodes(this.head,0, "inorder");
        return count;

    }

    public void save() {
        System.out.println("Enter file name: ");
        Scanner scanner = new Scanner(System.in);
        String fileName= scanner.nextLine();
        try {
            PrintStream stdout=System.out;
            PrintStream out = new PrintStream(new FileOutputStream(fileName));
            //sets System.out to print console output to file
            System.setOut(out);
            //prints out nodes in preorder traversal
            returnNodes(this.head,0,"preorder");
            //sets system.out to console again
            System.setOut(stdout);
            //closes file
            out.close();
            System.out.println("File created successfully.");
        } catch (IOException e ) {
            System.out.println("Unable to create file: "+fileName);
        }
    }


}
