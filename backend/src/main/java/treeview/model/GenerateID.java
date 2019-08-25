package treeview.model;

public class GenerateID {
    private static int COUNT = 1;
    public static int getID() {
        return COUNT++;
    }
}
