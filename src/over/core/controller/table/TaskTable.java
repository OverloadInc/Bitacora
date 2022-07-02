package over.core.controller.table;

import over.config.Configurator;
import over.core.model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>TaskTable</code> class.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public final class TaskTable {
    private static TaskTable instance;
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private Object colums[];
    private int index;

    /**
     * Class constructor.
     */
    private TaskTable() {
    }

    /**
     * Creates and initialize the <code>JTable</code> of user's tasks.
     */
    public void initTable(JTable taskTable) {
        this.taskTable = taskTable;
        this.colums = new Object[]{
                Configurator.getConfigurator().getProperty("idColumn"),
                Configurator.getConfigurator().getProperty("taskColumn"),
                Configurator.getConfigurator().getProperty("startColumn"),
                Configurator.getConfigurator().getProperty("endColumn"),
                Configurator.getConfigurator().getProperty("totalColumn"),
                Configurator.getConfigurator().getProperty("playColumn"),
                Configurator.getConfigurator().getProperty("stopColumn"),
                Configurator.getConfigurator().getProperty("deleteColumn")
        };

        tableModel = new DefaultTableModel(0, 0);
        tableModel.setColumnIdentifiers(colums);

        taskTable.setModel(tableModel);

        taskTable.getColumn(Configurator.getConfigurator().getProperty("playColumn")).setCellRenderer(new ButtonRenderer());
        taskTable.getColumn(Configurator.getConfigurator().getProperty("playColumn")).setCellEditor(new ButtonEditor(new JCheckBox()));
        taskTable.getColumn(Configurator.getConfigurator().getProperty("stopColumn")).setCellRenderer(new ButtonRenderer());
        taskTable.getColumn(Configurator.getConfigurator().getProperty("stopColumn")).setCellEditor(new ButtonEditor(new JCheckBox()));
        taskTable.getColumn(Configurator.getConfigurator().getProperty("deleteColumn")).setCellRenderer(new ButtonRenderer());
        taskTable.getColumn(Configurator.getConfigurator().getProperty("deleteColumn")).setCellEditor(new ButtonEditor(new JCheckBox()));

        refreshTable();
    }

    /**
     * Adds a new user task to the <code>JTable</code>.
     * @param taskName the user task name.
     */
    public void addTask(String taskName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();

        Task task = new Task();
        task.setId(index++);
        task.setName(taskName);
        task.setStartTime(localTime);

        tableModel.addRow(new Object[]{task.getId(), task.getName(), dtf.format(task.getStartTime()), "", ""});
    }

    /**
     * Updates the <code>JTable</code>'s information with current user tasks.
     */
    public void refreshTable() {
    }

    /**
     * Gets a <code>TaskTable</code> instance.
     * @return the <code>TaskTable</code> instance.
     */
    public static TaskTable getInstance() {
        if(instance == null)
            instance = new TaskTable();

        return instance;
    }

    /**
     * Gets an instance of the <code>JTable</code> user tasks.
     * @return the <code>JTable</code> instance.
     */
    public JTable getTaskTable() {
        return this.taskTable;
    }
}