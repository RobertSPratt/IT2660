/*
 * Prof. Santos
 * IT 2660 Fall 2020
 * Robert Pratt
 * Chapter 2, Problem 20
 */

import javax.swing.JOptionPane;

public class ClassRoster {

    private StudentListings[] roster;
    private int max;
    private int next = 0;

    public void insert() {

        //checks if array is at max size
        if(next == max) {
            JOptionPane.showMessageDialog(null, "No space available for more students");
            return;
        }

        //recreates array with another space if full
        if(next >= roster.length) {
            StudentListings[] newRoster = new StudentListings[roster.length + 1];
            for(int i = 0; i < roster.length; i++) {
                newRoster[i] = roster[i];
            }
            roster = newRoster;
        }

        StudentListings l = new StudentListings();
        l.input();
        roster[next] = l;
        next++;
        JOptionPane.showMessageDialog(null, "Student added");
    }

    public void fetch(String k) {
        int t = 0;

        //finds target entry that matches key field
        while(t < roster.length && roster[t].compareTo(k) != 0) {
            t++;
        }

        if(t == roster.length) {
            JOptionPane.showMessageDialog(null, "Student not found");
            return;
        }

        JOptionPane.showMessageDialog(null, roster[t].toString());
    }

    public void delete(String k) {
        StudentListings[] newRoster = new StudentListings[roster.length - 1];
        int t = 0;

        //finds target entry that matches key field
        while(t < roster.length && roster[t].compareTo(k) != 0) {
            t++;
        }

        if (t == roster.length) {
            JOptionPane.showMessageDialog(null, "Student not found");
            return;
        }

        //recreates array without entry
        int j = 0;
        for(int i = 0; i < newRoster.length; i++) {

            if(t != i) {
                newRoster[i] = roster[j];
                j++;

            }

            else {
                newRoster[j] = roster[i + 1];
                j++;
                i++;
            }
        }

        roster = newRoster;
        next = roster.length;
        JOptionPane.showMessageDialog(null, "Student deleted");
    }

    public void update(String k, int f) {
        int t = 0;

        //finds target entry that matches key field
        do {
            t++;
        } while(roster[t].compareTo(k) != 0);

        //updates the specific field
        switch(f) {
            case 1:
                roster[t].updateName();
                break;

            case 2:
                roster[t].updateStudentNumber();
                break;

            case 3:
                roster[t].updateGPA();
                break;
        }

        JOptionPane.showMessageDialog(null, "Student updated");

    }

    //prints out all students on the roster
    public void output() {
        String r = "Students:\n";

        //adds all records to single variable
        for(int i = 0; i < roster.length; i++) {
            r = r + roster[i].toString();
        }

        JOptionPane.showMessageDialog(null, r);
    }

    public ClassRoster(int i, int m) {
        roster = new StudentListings[i];
        max = m;
    }
}
