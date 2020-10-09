/* Prof. Santos
 * IT 2660
 * Robert Pratt
 * Assignment 3, Chapter 3, Problems 19 & 20
 */

public class Stack {
    private Listing[] data;
    private int top;
    private int size;

    //Beginning of methods for Problem 19

    //reinitiate the Stack to empty
    public void reinit() { top = -1; }

    //test for underflow condition
    public boolean isEmpty() {
        if(top == -1) {
            return true;
        }
        else {
            return false;
        }
    }

    //test for overflow condition
    public boolean isFull() {
        if(top == size - 1) {
            return true;
        }
        else {
            return false;
        }
    }

    //perform a Peek operation
    public Listing peek() {
        if(top == -1) {
            return null;
        }
        else {
            return data[top];
        }
    }

    //End of methods for Problem 19

    //Beginning of method for Problem 20

    //rewrite the Push operation so that it expands
    //when it is performed and the Stack is full
    public void push(Listing newNode) {
        if(top == size - 1) {
            Listing[] newData = new Listing[top + 1];
            for(int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
            size++;
        }
        top++;
        data[top] = newNode.deepCopy();
    }

    //End of the method for Problem 20

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
