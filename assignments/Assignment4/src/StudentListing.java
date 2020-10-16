/*
 * Prof. Santos
 * IT 2660 Fall 2020
 * Robert Pratt
 * Assignment 4, Chapter 4, Problem 27
 */

import javax.swing.JOptionPane;

public class StudentListing {

    private String name; //key field
    private String studentNumber;
    private String gradePointAvg;

    @Override
    public String toString() {
        return("Name: " + name + " Student Number: " + studentNumber + " GPA: " + gradePointAvg + "\n");
    }

    public StudentListing deepCopy() {
        StudentListing clone = new StudentListing(name, studentNumber, gradePointAvg);
        return clone;
    }

    public int compareTo(String targetKey) {
        return(name.compareTo(targetKey));
    }

    public void input() {
        name = JOptionPane.showInputDialog("Enter a name: ");
        studentNumber = JOptionPane.showInputDialog("Enter a student number: ");
        gradePointAvg = JOptionPane.showInputDialog("Enter GPA:");
    }

    public void updateName() {
        this.name = JOptionPane.showInputDialog("Enter new name: ");
    }

    public void updateStudentNumber() {
        this.studentNumber = JOptionPane.showInputDialog("Enter new student number: ");
    }

    public void updateGPA() {
        this.gradePointAvg = JOptionPane.showInputDialog("Enter new GPA: ");
    }

    public StudentListing() {
        this.name = "";
        this.studentNumber = "0";
        this.gradePointAvg = "0";
    }

    public StudentListing(String n) {
        this.name = n;
        this.studentNumber = "0";
        this.gradePointAvg = "0";
    }

    public StudentListing(String n, String i) {
        this.name = n;
        this.studentNumber = i;
        this.gradePointAvg = "0";
    }

    public StudentListing(String n, String i, String j) {
        this.name = n;
        this.studentNumber = i;
        this.gradePointAvg = j;
    }
}