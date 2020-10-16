/*
 * Prof. Santos
 * IT 2660 Fall 2020
 * Robert Pratt
 * Assignment 4, Chapter 4, Problem 27
 */

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        ClassRoster c;
        int s;
        int choice;

        s = Integer.parseInt(JOptionPane.showInputDialog("How many students to start with?: "));
        c = new ClassRoster();

        for(int i = 0; i < s; i++) {
            c.insert();
        }

        do {
            choice = Integer.parseInt(JOptionPane.showInputDialog(
                    "Enter: 1 to insert a new student's information\n" +
                            "       2 to fetch and output a student's information\n" +
                            "       3 to delete a student's information\n" +
                            "       4 to update a student's information\n" +
                            "       5 to output all the student information\n" +
                            "       6 to exit the program "));

            switch(choice) {
                case 1:
                    c.insert();
                    break;

                case 2:
                    c.fetch(JOptionPane.showInputDialog("Enter student's name: "));
                    break;

                case 3:
                    c.delete(JOptionPane.showInputDialog("Enter student name to delete record: "));
                    break;

                case 4:
                    String n = JOptionPane.showInputDialog("Enter student's name");
                    int o = Integer.parseInt(JOptionPane.showInputDialog(
                            "Enter: 1 to update student's name\n" +
                                    "       2 to update student's student number\n" +
                                    "       3 to update student's GPA "));
                    c.update(n, o);
                    break;

                case 5:
                    c.output();
                    break;
            }
        } while(choice != 6);

        JOptionPane.showMessageDialog(null, "Logged off.");
    }
}