import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class FileActions {

    private JFileChooser fileChooser;

    private String fileName;
    private String filePath;
    private Date date;
    private DateFormat dateFormat;

    public FileActions() {
        fileChooser = new JFileChooser();

        //Date and date format are used for status messages telling user if the operation worked
        date = new Date();
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    //Creates a new text editor
    //Gives new controller a new UI and a new fileActions class
    public void newTextEditor() {
        GUI UI = new GUI();
        FileActions fileActions = new FileActions();
        new Controller(UI, fileActions);
    }


    public String[] open() {
        //fileData will be returned with title at index 0 and text at index 2
        String fileData[] = new String[2];

        //showOpenDialog returns an int corresponding to whether user clicked save
        //showSaveDialog creates file explorer for user to choose which file to open
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {

            try {
                File file = fileChooser.getSelectedFile();
                filePath = file.getAbsolutePath();

                //Scanner instantiated with chosen file
                Scanner scan = new Scanner(file);

                //Adds each line from file to arrayList
                ArrayList<String> fileTextList = new ArrayList<>();
                while (scan.hasNextLine()) {
                    fileTextList.add(scan.nextLine() + "\n");
                }

                fileData[0] = file.getName();
                fileData[1] = String.join("", fileTextList); //Combines all of fileTextList into array

                fileName = fileData[0];
            }

            //Catch statement mainly exists for fileNotFoundExceptions
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
        return fileData;
    }

    public FileWriter save(String text) {

        //Checks if file has been saved before
        if (fileName != null) {
            try {

                //Instantiates and returns FileWriter with current file
                FileWriter saver = new FileWriter(filePath);
                return saver;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        //If fileName equals null file must be saveAs'd
        return saveAs(text);
    }


    public FileWriter saveAs(String text) { //Parameter is the text being saved

        //showSaveDialog tells us if the file chooser is still open
        //showSaveDialog creates file explorer for user to choose, name and save file
        int option = fileChooser.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                
                this.filePath = fileChooser.getSelectedFile().getAbsolutePath();
                this.fileName = fileChooser.getSelectedFile().getName();

                return save(text);
            }
            catch (Exception ex) {
                System.out.println("1");
            }
        }
        return null;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSaveMessage() {
        return this.getFileName() + " was saved at " + dateFormat.format(date) + ".";
    }

    public String getSaveErrorMessage() {
        return this.getFileName() + " was unable to be saved at " + dateFormat.format(date) + ".";
    }

    public String getOpenMessage() {
        return this.getFileName() + " was opened at " + dateFormat.format(date) + ".";
    }

    public String getOpenErrorMessage() {
        return this.getFileName() + " was unable to be opened at " + dateFormat.format(date) + ".";
    }

}


