/*
 * Prof. Santos
 * IT 2660 Fall 2020
 * Robert Pratt
 * Chapter 1, Problem 42
 */

package declare;

public class Declare {

    public static void main(String[] args) {
        Listing[] listings = {
            new Listing(), new Listing(), new Listing() 
        };
        
        for(int i = 0; i < listings.length; i++) {
            System.out.println("Listing " + (i + 1) + "...");
            listings[i].input();
        }
        
        for(int i = listings.length - 1; i >= 0; i--) {
            System.out.println("Listing " + (i + 1) + ": " + listings[i].toString());
        }
    }
    
}
