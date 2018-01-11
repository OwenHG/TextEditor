import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private Font defaultFont;
    private JFrame frame;
    private JEditorPane textArea;
    private JTextField status;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem newFile, openFile, saveFile, saveAsFile, copy, cut, paste;
    private JScrollPane scrollPane;


    public GUI() {
        //Initializes frame and default font
        createFrame();

        //Creates menu bar, menu bar items and sets the menu bar to the frame.
        createMenuBar();

        //Initializes text area, status bar, scroll bar and sets frame visible
        createMisc();

    }

    public void createFrame() {
        frame = new JFrame();
        frame.setSize(1400, 1000);
        frame.setTitle("Untitled - TextEditor");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        defaultFont = new Font("Dialog", Font.PLAIN, 22);
        frame.setFont(defaultFont);
        frame.setLayout(new BorderLayout());

    }

    public void createMenuBar() {
        //Create menu bar and set it to the window
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(300, 35));
        frame.setJMenuBar(menuBar);

        //Create JMenu File, Edit and Help
        fileMenu = new JMenu("File ");
        fileMenu.setFont(defaultFont);
        editMenu = new JMenu("Edit ");
        editMenu.setFont(defaultFont);
        helpMenu = new JMenu("Help ");
        helpMenu.setFont(defaultFont);

        //Adding JMenus to MenuBar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        //Create JMenuItems and sets font
        newFile = new JMenuItem("New");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newFile.setFont(defaultFont);

        openFile = new JMenuItem("Open");
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openFile.setFont(defaultFont);

        saveFile = new JMenuItem("Save");
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveFile.setFont(defaultFont);

        saveAsFile = new JMenuItem("Save As");
        saveAsFile.setFont(defaultFont);

        copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        copy.setFont(defaultFont);

        cut = new JMenuItem("Cut");
        cut.setFont(defaultFont);

        paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        paste.setFont(defaultFont);

        //Set JMenuItems to respective JMenus
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        editMenu.add(copy);
        editMenu.add(cut);
        editMenu.add(paste);
    }

    public void createMisc() {

        //Status bar updates user if the file was saved
        status = new JTextField();
        status.setFont(new Font("Dialog", Font.PLAIN, 20));
        frame.add(status, BorderLayout.SOUTH);

        //User writes in the text area
        textArea = new JEditorPane();
        textArea.setFont(new Font("Dialog", Font.PLAIN, 26));

        //ScrollPane gives more vertical area to type
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
    }


    //Getter and Setter methods are used by the controller
    public JEditorPane getEditorPane() {
        return this.textArea;
    }

    public String getText() {
        return textArea.getText();
    }

    public void setTextArea(String text) {
        textArea.setText(text);
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }


    //Updates when the user attempts to save file
    public void displayMessage(String text) {
        status.setText(text);
    }


    //Action Listeners are set by the controller in it's constructor
    public void addNewListener(ActionListener newListener) {
        newFile.addActionListener(newListener);
    }

    public void addOpenListener(ActionListener openListener) {
        openFile.addActionListener(openListener);
    }

    public void addSaveListener(ActionListener saveListener) {
        saveFile.addActionListener(saveListener);
    }

    public void addSaveAsListener(ActionListener saveAsListener) {
        saveAsFile.addActionListener(saveAsListener);
    }

    public void addCopyListener( ActionListener copyListener) {
        copy.addActionListener(copyListener);
    }

    public void addPasteListener(ActionListener pasteListener) {
        paste.addActionListener(pasteListener);
    }

    public void addCutListener(ActionListener cutListener) {
        cut.addActionListener(cutListener);
    }
}