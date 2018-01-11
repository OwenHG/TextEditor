

public class TextEditor {

    public static void main(String[] args ) {
        GUI UI = new GUI();
        FileActions fileActions = new FileActions();
        new Controller(UI, fileActions);
    }
}
