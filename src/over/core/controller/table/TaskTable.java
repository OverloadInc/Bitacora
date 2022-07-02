package over.core.controller.table;

import over.config.Configurator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * <code>TaskTable</code> class.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class TaskTable {
    private DefaultTableModel tableModel;
    private JTable taskTable;
    private Object colums[];
    private String rows[][];

    /**
     *
     * @param taskTable
     */
    public TaskTable(JTable taskTable) {
        this.taskTable = taskTable;
        this.colums = new String[]{
                Configurator.getConfigurator().getProperty("idColumn"),
                Configurator.getConfigurator().getProperty("taskColumn"),
                Configurator.getConfigurator().getProperty("startColumn"),
                Configurator.getConfigurator().getProperty("endColumn"),
                Configurator.getConfigurator().getProperty("totalColumn"),
                Configurator.getConfigurator().getProperty("playColumn"),
                Configurator.getConfigurator().getProperty("stopColumn"),
                Configurator.getConfigurator().getProperty("deleteColumn")
        };
        initTable();
    }

    /**
     *
     */
    public void initTable() {
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
     *
     */
    public void refreshTable() {
        rows = new String[][]{{"1","","",""},{"","","",""}};
        tableModel.addRow(rows);
    }

    /**
     *
     * @return
     */
    public JTable getTaskTable() {
        return this.taskTable;
    }
}