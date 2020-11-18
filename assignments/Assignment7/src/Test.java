/*
 * This is a test program that runs through the BinaryTree to ensure functionality.
 */
public class Test {
    public static void main(String[] args) {
        //test program (program with required choices in the commented-out section below
        Main.BinaryTree b = new Main.BinaryTree();
        String[][] entries = {
                {"Robert", "1", "4"},
                {"Emmy", "2", "4"},
                {"Matt", "3", "3"},
                {"Viktor", "4", "4"},
                {"Robin", "5", "3.75"},
                {"Shawn", "6", "3.2"},
                {"Clara", "7", "3"},
                {"Frici", "8", "3"},
                {"Dorka", "9", "4"},
                {"Ricsi", "10", "3.5"},
                {"Balazs", "11", "4"},
                {"Villo", "12", "3"}
        };
        for(int i = 0; i < entries.length; i++) {
            b.insert(new Listing(entries[i][0], entries[i][1], entries[i][2]));
        }
        b.output();
        b.delete("Matt");
        b.output();
        b.update("Frici", new Listing("Frigyes", "8", "3"));
        //BTNode has private values, so this program can only print memory location
        System.out.print(b.fetch("Frigyes").toString());
    }
}
