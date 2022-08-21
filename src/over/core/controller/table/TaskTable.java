package over.core.controller.table;

import over.config.Configurator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * <code>TableController</code> class allows the user to create a <code>JTable</code>
 * in order to display a set of tasks. In addition, this class is able to render the <code>JTable</code>
 * cells to include <code>JButton</code> components with its listeners associated.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public final class TaskTable {
    private static TaskTable instance;
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private Object colums[];

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

        this.taskTable.setModel(tableModel);

        this.taskTable.getColumn(Configurator.getConfigurator().getProperty("playColumn")).setCellRenderer(new ButtonRenderer());
        this.taskTable.getColumn(Configurator.getConfigurator().getProperty("playColumn")).setCellEditor(new ButtonEditor(new JCheckBox()));
        this.taskTable.getColumn(Configurator.getConfigurator().getProperty("stopColumn")).setCellRenderer(new ButtonRenderer());
        this.taskTable.getColumn(Configurator.getConfigurator().getProperty("stopColumn")).setCellEditor(new ButtonEditor(new JCheckBox()));
        this.taskTable.getColumn(Configurator.getConfigurator().getProperty("deleteColumn")).setCellRenderer(new ButtonRenderer());
        this.taskTable.getColumn(Configurator.getConfigurator().getProperty("deleteColumn")).setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    /**
     * Gets a <code>TaskTable</code> instance.
     * @return the <code>TaskTable</code> instance.
     */
    public static TaskTable getInstance() {
        if (instance == null)
            instance = new TaskTable();

        return instance;
    }

    /**
     * Gets an instance of the <code>JTable</code> user tasks.
     * @return the <code>JTable</code> instance.
     */
    public JTable getTaskTable() {
        return taskTable;
    }

    /**
     * Gets the user task table's <code>DefaultTableModel</code> instance.
     * @return the current <code>DefaultTableModel</code> instance.
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}