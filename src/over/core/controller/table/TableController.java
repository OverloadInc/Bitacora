package over.core.controller.table;

import over.core.gui.Bitacora;
import over.core.model.pojo.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <code>TableController</code> class.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class TableController {
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Class constructor.
     */
    public TableController() {
        taskTable = TaskTable.getInstance().getTaskTable();
        tableModel = TaskTable.getInstance().getTableModel();
    }

    /**
     * Adds a new user task to the <code>JTable</code>.
     * @param name the user task name.
     */
    public void addTask(String name) {
        if (!name.isEmpty() && name != null) {
            Task task = new Task();
            task.setId(1);
            task.setName(name);
            task.setStartTime(LocalTime.now());
            task.setEndTime(LocalTime.of(00, 00, 00));
            task.setFirstTime(true);
            task.setEffectiveTime(LocalTime.of(00, 00, 00));
            task.setDate(new Date(System.currentTimeMillis()));

            task.add();

            refreshTable(1);
            Bitacora.addMessage(name);
        }
        else
            JOptionPane.showMessageDialog(null, "User task not valid!");
    }

    /**
     * Deletes a selected user task from the <code>JTable</code>
     */
    public void deleteTask() {
        int selectedRow = taskTable.getSelectedRow();
        int rowCount = taskTable.getRowCount();

        if(selectedRow >= 0 && rowCount >= 1) {
            Task task = new Task();
            task.setId((int)tableModel.getValueAt(selectedRow, 0));

            task.delete();

            refreshTable(2);
        }
        else
            JOptionPane.showMessageDialog(null, "Select a valid task");
    }

    public void updateTask() {
        System.out.println("Update task");
    }

    public void play() {
        System.out.println("Play task");
    }

    public void stop() {
        System.out.println("Stop task");
    }

    /**
     * Updates the <code>JTable</code>'s information with current user tasks.
     */
    public void refreshTable(int option) {
        if (option == 1) {
            Task task = new Task().getLast();
            tableModel.addRow(new Object[]{task.getId(), task.getName(), dtf.format(task.getStartTime()), dtf.format(task.getEndTime()), dtf.format(task.getEffectiveTime())});
        }
        else if (option == 2) {
            taskTable.getCellEditor().stopCellEditing();
            tableModel.removeRow(taskTable.getSelectedRow());
            taskTable.repaint();
        }
    }
}