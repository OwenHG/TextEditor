import java.awt.event.*;
import java.io.FileWriter;

public class Controller {
    private GUI UI;
    private FileActions fileActions;

    public Controller(GUI UI, FileActions fileActions) {
        //Gives the controller access to the UI and fileActoins
        this.UI = UI;
        this.fileActions = fileActions;

        //Creates action listeners and adds them to the UI
        this.UI.addNewListener(new NewListener());
        this.UI.addOpenListener(new OpenListener());
        this.UI.addSaveListener(new SaveListener());
        this.UI.addSaveAsListener(new SaveAsListener());
        this.UI.addCopyListener(new CopyListener());
        this.UI.addCutListener(new CutListener());
        this.UI.addPasteListener(new PasteListener());
    }


    private class NewListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fileActions.newTextEditor();
        }
    }

    private class OpenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] data = fileActions.open();

            //Checks that open method did not return empty title
            if(data != null) {
                UI.setTitle(data[0] + " - TextEditor");
                UI.setTextArea(data[1]);
                UI.displayMessage(fileActions.getOpenMessage());
            }
            else {
                //Notifies user of error
                UI.displayMessage(fileActions.getOpenErrorMessage());
            }
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent arg) {

            //FileAction.save() returns a FileWriter that was instantiated with the open file or it returns null
            FileWriter saver = fileActions.save(UI.getText());
            try {

                //Writes text from UI to file
                UI.getEditorPane().write(saver);
                saver.close();

                //Sets name and displays status
                //Setting title is only necessary if the current file was saved for the first time
                UI.setTitle(fileActions.getFileName() + " - TextEditor");
                UI.displayMessage(fileActions.getSaveMessage());
            }
            catch (Exception ex) {
                //Notifies user of error in saving
                System.out.println(ex.getMessage());
                UI.displayMessage(fileActions.getSaveErrorMessage());
            }
        }
    }

    private class SaveAsListener implements ActionListener {
        public void actionPerformed(ActionEvent arg) {

            //FileAction.saveAs() returns a FileWriter that was instantiated with the open file or it returns null
            FileWriter saver = fileActions.saveAs(UI.getText());
            try {
                //Writes text from UI to file
                UI.getEditorPane().write(saver);
                saver.close();

                //Sets title and displays status the save process
                UI.setTitle(fileActions.getFileName() + " - TextEditor");
                UI.displayMessage(fileActions.getSaveMessage());
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                UI.displayMessage(fileActions.getSaveErrorMessage());
            }
        }
    }

    //Copy, Cut and Paste are all methods built into JEditorPane
    private class CopyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            UI.getEditorPane().copy();
        }
    }

    private class CutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UI.getEditorPane().cut();
        }
    }

    private class PasteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UI.getEditorPane().paste();
        }
    }

}