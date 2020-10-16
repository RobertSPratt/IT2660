/*
 * Prof. Santos
 * IT 2660
 * Robert Pratt
 * Assignment 4, Chapter 4, Problem 27
 */

public class Node {
    StudentListing listing;
    Node next;
    
    public String toString() {
        return this.listing.toString();
    }
    
    public Node(StudentListing l, Node n) {
        this.listing = l;
        this.next = n;
    }

    public Node() {
        this.listing = null;
        this.next = null;
    }
}
