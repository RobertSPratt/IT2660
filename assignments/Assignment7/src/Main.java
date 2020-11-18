/*
 * Prof. Santos
 * IT 2660 - Fall 2020
 * Robert Pratt
 * Assignment 7, Ch. 7, Prob. 31
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        Scanner keyboard = new Scanner(System.in);
        Listing result;
        String key;
        boolean wasSuccessful;
        int choice;

        do {
            System.out.println("Enter: 1 to insert a new student's information\n" +
                    "        2 to fetch and output a student's information\n" +
                    "        3 to delete a student's information\n" +
                    "        4 to update a student's information\n" +
                    "        5 to output all the student information\n" +
                    "        6 to exit the program "
            );
            choice = keyboard.nextInt();
            String trash = keyboard.nextLine();

            switch(choice) {
                case 1: //insert a new listing
                    System.out.print("Student's name: ");
                    String name = keyboard.nextLine();
                    System.out.print("Student's ID: ");
                    String sID = keyboard.nextLine();
                    System.out.print("Student's GPA: ");
                    String gpa = keyboard.nextLine();
                    Listing n = new Listing(name, sID, gpa);
                    if(!b.insert(n))
                        System.out.println("Record could not be added.");
                    else
                        System.out.println("Record added.");

                case 2: //fetch a listing
                    System.out.print("Enter the student's name to view record: ");
                    key = keyboard.nextLine();
                    result = b.fetch(key).listing.deepCopy();
                    if(result == null)
                        System.out.println("Record not found.");
                    else
                        System.out.println(result.toString());

                case 3: //delete a listing
                    System.out.print("Enter the student's name to delete the record: ");
                    key = keyboard.nextLine();
                    wasSuccessful = b.delete(key);
                    if(!wasSuccessful)
                        System.out.println("Record not found.");
                    else
                        System.out.print("Record deleted.");

                case 4: //update a listing
                    System.out.print("Enter the student's name to update the record: ");
                    key = keyboard.nextLine();
                    int option;
                    System.out.print("Enter: 1 to update a student's name\n" +
                            "       2 to update a student's ID number\n" +
                            "       3 to update a student's GPA "
                    );
                    option = keyboard.nextInt();
                    switch(option) {
                        case 1:
                            System.out.print("Enter the new name: ");
                            name = keyboard.nextLine();
                            if(b.update(key, new Listing(name, b.fetch(key).listing.getID(), b.fetch(key).listing.getGPA())))
                                System.out.println("Record could not be updated.");
                            else
                                System.out.print("Record successfully updated.");

                        case 2:
                            System.out.print("Enter the new student ID number: ");
                            sID = keyboard.nextLine();
                            if(b.update(key, new Listing(b.fetch(key).listing.getKey(), sID, b.fetch(key).listing.getGPA())))
                                System.out.print("Record could not be updated.");
                            else
                                System.out.print("Record successfully updated.");

                        case 3:
                            System.out.print("Enter the new student GPA: ");
                            gpa = keyboard.nextLine();
                            if(b.update(key, new Listing(b.fetch(key).listing.getKey(), b.fetch(key).listing.getID(), gpa)))
                                System.out.println("Record could not be updated.");
                            else
                                System.out.print("Record successfully updated.");
                    }

                case 5: //print all records in reverse alphabetical order
                    b.output();
            }
        } while(choice != 6);
    }
    public static class BinaryTree {
        BTNode rootNode;

        public BinaryTree() {
            rootNode = new BTNode();
        }

        //allows recursive method to be called from main without
        //requiring access to the tree's nodes
        public boolean insert(Listing l) {
            BTNode parent = new BTNode();
            BTNode child = rootNode;
            return insertBNTNode(parent, child, l);
        }

        public boolean insertBNTNode(BTNode parent, BTNode child, Listing l) {
            if(child.listing == null) {
                child.listing = l.deepCopy();
                if(child == rootNode)
                    child.parent = null;
                else
                    child.parent = parent;
                return true;
            }
            else if(child.listing.getKey().equals(l.getKey()))
                System.out.println("This listing already exists.");
            else {
                parent = child;
                if(l.getKey().compareTo(child.listing.getKey()) < 0) {
                    if (parent.leftChild == null) {
                        parent.leftChild = new BTNode();
                    }
                    insertBNTNode(parent, parent.leftChild, l);
                }
                else {
                    if (parent.rightChild == null) {
                        parent.rightChild = new BTNode();
                    }
                    insertBNTNode(parent, parent.rightChild, l);
                }
            }
            return false; //default to a failed entry
        }

        public BTNode fetch(String key) {
            BTNode child = rootNode;
            child = findBTNode(child, key);
            return child;
        }

        public boolean delete(String key) {
            BTNode parent;
            BTNode child = rootNode;
            BTNode leftChild, rightChild;
            child = findBTNode(child, key);
            parent = child.parent;
            if(child.listing == null)
                return false;
            else {
                //listing has no children
                if(child.leftChild == null && child.rightChild == null) {
                    if (parent.leftChild == child)
                        parent.leftChild = null;
                    else
                        parent.rightChild = null;
                }
                //listing has only one child
                else if(child.leftChild == null || child.rightChild == null) {
                    if(parent.leftChild == child) {
                        if(child.leftChild != null)
                            parent.leftChild = child.leftChild;
                        else
                            parent.leftChild = child.rightChild;
                    }
                    else {
                        if(child.leftChild != null)
                            parent.rightChild = child.leftChild;
                        else
                            parent.rightChild = child.rightChild;
                    }
                }
                //listing has two children
                else {
                    leftChild = child.leftChild;
                    rightChild = leftChild.rightChild;
                    if(rightChild != null) { //left child has a right subtree
                        while(rightChild != null) {
                            leftChild = rightChild;
                            rightChild = rightChild.rightChild;
                        }
                        child.listing = leftChild.listing;
                        leftChild.listing = null;
                    }
                    else { //left child does not have a right subtree
                        leftChild.rightChild = child.rightChild;
                        if(parent.leftChild == child)
                            parent.leftChild = leftChild;
                        else
                            parent.rightChild = leftChild;
                    }
                }
                return true;
            }
        }

        public boolean update(String key, Listing newListing) {
            if(!delete(key))
                return true;
            else
                return insert(newListing);
        }

        //allows for calling from main method
        public void output() {
            RNLOutput(rootNode);
        }

        public void RNLOutput(BTNode n) {
            if(n.rightChild != null)
                RNLOutput(n.rightChild);
            System.out.print(n.listing.toString());
            if(n.leftChild != null)
                RNLOutput(n.leftChild);
        }

        public BTNode findBTNode(BTNode child, String key) {
            if(child == null) {
                return null;
            }
            else {
                if(key.compareTo(child.listing.getKey()) < 0)
                    return(findBTNode(child.leftChild, key));
                else if(key.compareTo(child.listing.getKey()) > 0)
                    return(findBTNode(child.rightChild, key));
                return child;
            }
        }

        public class BTNode {
            private BTNode parent, leftChild, rightChild;
            private Listing listing;

            public BTNode(Listing l) {
                this.parent = null;
                this.leftChild = null;
                this.rightChild = null;
                this.listing = l;
            }

            public BTNode() {
                }
            }
    }
}
