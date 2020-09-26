/*
 * Prof. Santos
 * IT 2660 Fall 2020
 * Robert Pratt
 * Chapter 1, Problem 41
 */

package listing;

import java.util.Scanner;

public class Listing {
    
    private String name;
    private int age;

    public Listing() {
        name = " ";
        age = 0;
    }
    
    public Listing(String n, int a) {
        name = n;
        age = a;
    }
    
    @Override
    public String toString() {
        return("Name: " + name + ", Age: " + age);
    }
    
    public void input() {
        Scanner data = new Scanner(System.in);
        System.out.print("Enter the name: ");
        name = data.nextLine();
        System.out.print("Enter the age: ");
        age = data.nextInt();
        String trash = data.nextLine(); //Clear's Scanner
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public void setAge(int a) {
        age = a;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public static void main(String[] args) {
        
        Listing test = new Listing();
        Listing test2 = new Listing("testName", 25);
        
        System.out.println("test " + test.toString());
        System.out.println("test2 " + test2.toString());
        test.setName("New Name");
        System.out.println(test.getName());
        test.setAge(12);
        System.out.println(test.getAge());        
    }
    
}
 
