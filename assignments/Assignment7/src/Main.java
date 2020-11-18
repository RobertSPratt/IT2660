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
                    result = b.fetch(key).l.deepCopy();
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
                            if(b.update(key, new Listing(name, b.fetch(key).l.getID(), b.fetch(key).l.getGPA())))
                                System.out.println("Record could not be updated.");
                            else
                                System.out.print("Record successfully updated.");

                        case 2:
                            System.out.print("Enter the new student ID number: ");
                            sID = keyboard.nextLine();
                            if(b.update(key, new Listing(b.fetch(key).l.getKey(), sID, b.fetch(key).l.getGPA())))
                                System.out.print("Record could not be updated.");
                            else
                                System.out.print("Record successfully updated.");

                        case 3:
                            System.out.print("Enter the new student GPA: ");
                            gpa = keyboard.nextLine();
                            if(b.update(key, new Listing(b.fetch(key).l.getKey(), b.fetch(key).l.getID(), gpa)))
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
        BTNode r;

        public BinaryTree() {
            r = new BTNode();
        }

        //allows recursive method to be called from main without
        //requiring access to the tree's nodes
        public boolean insert(Listing l) {
            BTNode p = new BTNode();
            BTNode c = r;
            return insertBNTNode(p, c, l);
        }

        public boolean insertBNTNode(BTNode p, BTNode c, Listing listing) {
            if(c.l == null) {
                c.l = listing.deepCopy();
                if(c == r)
                    c.p = null;
                else
                    c.p = p;
                return true;
            }
            else if(c.l.getKey().equals(listing.getKey()))
                System.out.println("This listing already exists.");
            else {
                p = c;
                if(listing.getKey().compareTo(c.l.getKey()) < 0) {
                    if (p.lc == null) {
                        p.lc = new BTNode();
                    }
                    insertBNTNode(p, p.lc, listing);
                }
                else {
                    if (p.rc == null) {
                        p.rc = new BTNode();
                    }
                    insertBNTNode(p, p.rc, listing);
                }
            }
            return false; //default to a failed entry
        }

        public BTNode fetch(String key) {
            BTNode p = new BTNode();
            BTNode c = r;
            c = findBTNode(p, c, key);
            return c;
        }

        public boolean delete(String key) {
            BTNode p = new BTNode();
            BTNode c = r;
            BTNode leftChild, rightChild;
            c = findBTNode(p, c, key);
            p = c.p;
            if(c.l == null)
                return false;
            else {
                //listing has no children
                if(c.lc == null && c.rc == null) {
                    if (p.lc == c)
                        p.lc = null;
                    else
                        p.rc = null;
                }
                //listing has only one child
                else if(c.lc == null || c.rc == null) {
                    if(p.lc == c) {
                        if(c.lc != null)
                            p.lc = c.lc;
                        else
                            p.lc = c.rc;
                    }
                    else {
                        if(c.lc != null)
                            p.rc = c.lc;
                        else
                            p.rc = c.rc;
                    }
                }
                //listing has two children
                else {
                    leftChild = c.lc;
                    rightChild = leftChild.rc;
                    if(rightChild != null) { //left child has a right subtree
                        while(rightChild != null) {
                            leftChild = rightChild;
                            rightChild = rightChild.rc;
                        }
                        c.l = leftChild.l;
                        leftChild.l = null;
                    }
                    else { //left child does not have a right subtree
                        leftChild.rc = c.rc;
                        if(p.lc == c)
                            p.lc = leftChild;
                        else
                            p.rc = leftChild;
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
            RNLOutput(r);
        }

        public void RNLOutput(BTNode n) {
            if(n.rc != null)
                RNLOutput(n.rc);
            System.out.print(n.l.toString());
            if(n.lc != null)
                RNLOutput(n.lc);
        }

        public BTNode findBTNode(BTNode p, BTNode c, String key) {
            if(c == null) {
                return null;
            }
            else {
                p = c;
                if(key.compareTo(c.l.getKey()) < 0)
                    return(findBTNode(p, p.lc, key));
                else if(key.compareTo(c.l.getKey()) > 0)
                    return(findBTNode(p, p.rc, key));
                return c;
            }
        }

        public class BTNode {
            private BTNode p, lc, rc; //parent, left child, right child
            private Listing l;

            public BTNode(Listing l) {
                this.p = null;
                this.lc = null;
                this.rc = null;
                this.l = l;
            }

            public BTNode() {
                }
            }
    }
}
