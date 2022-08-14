package over.core.gui;

import over.config.Configurator;
import over.core.controller.format.FontEditor;
import over.core.controller.table.TableController;
import over.core.controller.table.TaskTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <code>Bitacora</code> class.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class Bitacora extends JFrame {
    private JPanel inputPanel;
    private JPanel taskPanel;
    private JPanel consolePanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenu settingsMenu;
    private JMenuItem aboutOption;
    private JMenuItem configOption;
    private JMenuItem exitOption;
    private JLabel lblTask;
    private JTextField txtTask;
    private JButton btnAddTask;
    private JTable taskTable;
    private JScrollPane scrollTask;
    private JScrollPane scrollConsole;
    private TableController controller;
    private static JTextPane txtConsole;
    private static FontEditor fontEditor;

    /**
     * Class constructor.
     */
    public Bitacora() {
        initComponents();
        TaskTable.getInstance().initTable(taskTable);
        controller = new TableController();
        fontEditor = new FontEditor();
    }

    /**
     * Creates and initialize the <code>Bitacora</code> application's components.
     */
    private void initComponents() {
        inputPanel = new JPanel();
        taskPanel = new JPanel();
        consolePanel = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitOption = new JMenuItem();
        settingsMenu = new JMenu();
        configOption = new JMenuItem();
        helpMenu = new JMenu();
        aboutOption = new JMenuItem();
        lblTask = new JLabel();
        txtTask = new JTextField();
        btnAddTask = new JButton();
        scrollTask = new JScrollPane();
        taskTable = new JTable();
        scrollConsole = new JScrollPane();
        txtConsole = new JTextPane();

        setName("frmBitacora");
        setTitle(Configurator.getConfigurator().getProperty("applicationName"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(600, 600));

        createInputPanel();
        createTaskPanel();
        createConsolePanel();

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(taskPanel, BorderLayout.CENTER);
        getContentPane().add(consolePanel, BorderLayout.SOUTH);

        createMenuBar();

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Creates and initialize the <code>InputPanel</code> components.
     */
    public void createInputPanel() {
        inputPanel.setName("inputPanel");
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setMaximumSize(new Dimension(600, 100));
        inputPanel.setMinimumSize(new Dimension(600, 100));
        inputPanel.setPreferredSize(new Dimension(600, 100));

        lblTask.setName("lblTask");
        lblTask.setText(Configurator.getConfigurator().getProperty("lblTask"));

        inputPanel.add(lblTask);

        txtTask.setName("txtTask");
        txtTask.setText("");
        txtTask.setMaximumSize(new Dimension(300, 30));
        txtTask.setMinimumSize(new Dimension(300, 30));
        txtTask.setPreferredSize(new Dimension(300, 30));

        inputPanel.add(txtTask);

        btnAddTask.setName("btnAddTask");
        btnAddTask.setText(Configurator.getConfigurator().getProperty("btnAddTask"));
        btnAddTask.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnAddTaskMouseClicked(evt);
            }
        });

        inputPanel.add(btnAddTask);
    }

    /**
     * Creates and initialize the <code>TaskPanel</code> components.
     */
    public void createTaskPanel() {
        taskPanel.setName("taskPanel");
        taskPanel.setLayout(new BorderLayout());
        taskPanel.setMaximumSize(new Dimension(600, 400));
        taskPanel.setMinimumSize(new Dimension(600, 400));
        taskPanel.setPreferredSize(new Dimension(600, 400));

        taskTable.setName("taskTable");
        taskTable.setFillsViewportHeight(true);
        taskTable.setMaximumSize(new Dimension(600, 400));
        taskTable.setMinimumSize(new Dimension(600, 400));
        taskTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollTask.setName("scrollTask");
        scrollTask.setMinimumSize(new Dimension(600, 400));
        scrollTask.setMaximumSize(new Dimension(600, 400));
        scrollTask.setViewportView(taskTable);

        taskPanel.add(scrollTask, BorderLayout.CENTER);
    }

    /**
     * Creates and initialize the <code>ConsolePanel</code> components.
     */
    public void createConsolePanel() {
        consolePanel.setName("consolePanel");
        consolePanel.setMaximumSize(new Dimension(600, 200));
        consolePanel.setMinimumSize(new Dimension(600, 200));
        consolePanel.setPreferredSize(new Dimension(600, 200));
        consolePanel.setLayout(new BorderLayout());

        txtConsole.setName("txtConsole");
        txtConsole.setMaximumSize(new Dimension(500, 200));
        txtConsole.setMinimumSize(new Dimension(500, 200));
        txtConsole.setPreferredSize(new Dimension(500, 200));

        scrollConsole.setName("scrollConsole");
        scrollConsole.setPreferredSize(new Dimension(500, 200));
        scrollConsole.setViewportView(txtConsole);

        consolePanel.add(scrollConsole, BorderLayout.CENTER);
    }

    /**
     * Creates and initialize the <code>JMenuBar</code> items.
     */
    public void createMenuBar() {
        menuBar.setName("menuBar");

        fileMenu.setName("fileMenu");
        fileMenu.setText(Configurator.getConfigurator().getProperty("fileMenu"));
        fileMenu.setMnemonic('F');

        exitOption.setName("exitOption");
        exitOption.setText(Configurator.getConfigurator().getProperty("exitOption"));
        exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        exitOption.setMnemonic('E');
        exitOption.setIcon(new ImageIcon(getClass().getResource("/over/resources/img/opt_exit.png")));
        exitOption.addActionListener(e -> System.exit(0));
        fileMenu.add(exitOption);

        menuBar.add(fileMenu);

        settingsMenu.setName("settingsMenu");
        settingsMenu.setText(Configurator.getConfigurator().getProperty("settingsMenu"));
        settingsMenu.setMnemonic('S');

        configOption.setName("configOption");
        configOption.setText(Configurator.getConfigurator().getProperty("configOption"));
        configOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        configOption.setMnemonic('O');
        configOption.setIcon(new ImageIcon(getClass().getResource("/over/resources/img/opt_settings_gray.png")));
        configOption.addActionListener((ActionEvent evt) -> {});
        settingsMenu.add(configOption);

        menuBar.add(settingsMenu);

        helpMenu.setName("helpMenu");
        helpMenu.setText(Configurator.getConfigurator().getProperty("helpMenu"));
        helpMenu.setMnemonic('H');

        aboutOption.setName("aboutOption");
        aboutOption.setText(Configurator.getConfigurator().getProperty("aboutOption"));
        aboutOption.setMnemonic('A');
        aboutOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aboutOption.setIcon(new ImageIcon(getClass().getResource("/over/resources/img/opt_about.png")));
        aboutOption.addActionListener(e -> new About().setVisible(true));
        helpMenu.add(aboutOption);

        menuBar.add(helpMenu);
    }

    /**
     * Adds a new user task to the <code>JTable</code>.
     * @param evt the <code>JButton</code> click event.
     */
    private void btnAddTaskMouseClicked(MouseEvent evt) {
        controller.addTask(txtTask.getText().trim());

        txtTask.setText("");
    }

    /**
     * Adds a new message to the <code>Bitacora</code> application's messages console.
     * @param taskName the task name to display in the console.
     */
    public static void addMessage(String taskName) {
        String consoleMessage = Configurator.getConfigurator().getProperty("consoleMessage");

        fontEditor.setBold(txtConsole, consoleMessage + ": ");
        fontEditor.setSimple(txtConsole, taskName + "\n");
    }

    /**
     * The <code>Bitacora</code>'s starting point.
     * @param args the initial parameters.
     */
    public static void main(String... args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            Configurator.getConfigurator().initConfigurator();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new Bitacora().setVisible(true));
    }
}