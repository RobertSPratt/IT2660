/* Prof. Santos
 * IT 2660
 * Robert Pratt
 * Assignment 3, Chapter 3, Problems 19 & 20
 */

public class Listing {
    private String name;
    private String address;
    private String number;

    public String toString() {
        return ("Name is " + name + "\n" +
                "Address is " + address + "\n" +
                "Number is " + number + "\n");
    }

    public Listing deepCopy() {
        Listing clone = new Listing(name, address, number);
        return clone;
    }

    public Listing() {
        name = "";
        address = "";
        number = "";
    }

    public Listing(String n, String a, String num) {
        name = n;
        address = a;
        number = num;
    }
}
