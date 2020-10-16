/*
 * Prof. Santos
 * IT 2660
 * Robert Pratt
 * Assignment 4, Chapter 4, Problem 27
 */

import javax.swing.JOptionPane;

public class ClassRoster {
    private Node h; //list header

    public void insert() {
        Node n = new Node();
        if(n == null) { //out of memory
            JOptionPane.showMessageDialog(null, "No space available.");
        }
        else {
            StudentListing l = new StudentListing();
            l.input();
            n.next = h.next;
            h.next = n;
            n.listing = l.deepCopy();
            JOptionPane.showMessageDialog(null, "Student added.");
        }
    }

    public void fetch(String t) {
        Node p = h.next;
        while(p != null && !(p.listing.compareTo(t) == 0)) {
            p = p.next;
        }
        if(p != null) {
            JOptionPane.showMessageDialog(null, p.listing.toString());
        }
        else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    public void delete(String t) {
        Node q = h;
        Node p = h.next;
        while(p != null && !(p.listing.compareTo(t) == 0)) {
            q = p;
            p = p.next;
        }
        if(p != null) {
            q.next = p.next;
            JOptionPane.showMessageDialog(null, "Student deleted.");
        }
        else{
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    public void update(String t, int c) {
        Node p = h.next;

        //finds target entry that matches key field
        while(p != null && !(p.listing.compareTo(t) == 0)) {
            p = p.next;
        }
        if(p == null) {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
        switch(c) {
            case 1:
                p.listing.updateName();
                break;

            case 2:
                p.listing.updateStudentNumber();
                break;

            case 3:
                p.listing.updateGPA();
                break;
        }
        JOptionPane.showMessageDialog(null, "Student updated.");
    }

    //prints out all students on the roster
    public void output() {
        String r = "Students:\n";
        Node p = h.next;

        //adds all records to single variable
        while(p != null) {
            r = r + p.listing.toString();
            p = p.next;
        }
        JOptionPane.showMessageDialog(null, r);
    }

    public ClassRoster() {
        h = new Node(); //dummy node
    }
}
