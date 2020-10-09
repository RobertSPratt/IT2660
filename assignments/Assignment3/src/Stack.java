/* Prof. Santos
 * IT 2660
 * Robert Pratt
 * Assignment 3, Chapter 3, Problems 19 & 20
 */

public class Stack {
    private Listing[] data;
    private int top;
    private int size;

    public boolean push(Listing newNode) {
        if(top == size - 1) {
            return false;
        }
        else {
            top++;
            data[top] = newNode.deepCopy();
            return true;
        }
    }

    public Listing pop() {
        int topLocation;
        if(top == -1) {
            return null;
        }
        else {
            topLocation = top;
            top--;
            return data[topLocation];
        }
    }

    public void showAll() {
        for(int i = top; i >= 0; i--) {
            System.out.print(data[i].toString());
        }
    }

    public Stack() {
        top = -1;
        size = 100;
        data = new Listing[100];
    }

    public Stack(int n) {
        top = -1;
        size = n;
        data = new Listing[n];
    }
}
