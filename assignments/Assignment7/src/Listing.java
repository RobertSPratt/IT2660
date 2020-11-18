/*
 * Prof. Santos
 * IT 2660 - Fall 2020
 * Robert Pratt
 * Assignment 7, Ch. 7, Prob. 30
 */
public class Listing {
    private String name;
    private String studentID;
    private String gpa;

    public Listing(String n, String i, String g) {
        this.name = n;
        this.studentID = i;
        this.gpa = g;
    }

    public Listing(String n, String i) {
        this.name = n;
        this.studentID = i;
        this.gpa = "N/A";
    }

    public String toString() {
        return("Name: " + this.name + " Student ID: " + studentID +
                " GPA: " + gpa + "\n");
    }

    public Listing deepCopy() {
        Listing clone = new Listing(name, studentID, gpa);
        return clone;
    }

    public int compareTo(String targetKey) {
        return(name.compareTo(targetKey));
    }

    public void updateGPA(String g) {
        this.gpa = g;
    }

    public void updateID(String i) {
        this.studentID = i;
    }

    public void updateName(String n) {
        this.name = n;
    }

    public String getKey() {
        return name;
    }

    public String getID() {
        return studentID;
    }

    public String getGPA() {
        return gpa;
    }
}
