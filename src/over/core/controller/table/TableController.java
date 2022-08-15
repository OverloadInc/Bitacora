package over.core.controller.table;

import over.core.gui.Bitacora;
import over.core.model.pojo.Status;
import over.core.model.pojo.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * <code>TableController</code> class.
 * @author Overload Inc.
 * @version 1.0, 19 Jun 2022
 */
public class TableController {
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Class constructor.
     */
    public TableController() {
        taskTable = TaskTable.getInstance().getTaskTable();
        tableModel = TaskTable.getInstance().getTableModel();
    }

    /**
     * Searches for a specific task inside a list of <code>Task</code> objects.
     * @param id the <code>id</code> of the task required.
     * @return the <code>Task</code> object; <code>null</code> otherwise.
     */
    public Task find(int id) {
        for (Task task : taskList)
            if (task.getId() == id)
                return task;

        return null;
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

    public void updateTask(Task task) {
        if (task != null)
            task.update();
    }

    //TODO Implementation of effective time mechanism
    public void play() {
        int selectedRow = taskTable.getSelectedRow();
        int rowCount = taskTable.getRowCount();

        if(selectedRow >= 0 && rowCount >= 1) {
            Task task = find((int)taskTable.getValueAt(selectedRow, 0));

            if (task.isFirstTime() && task.getStatus() == Status.PLAY) {
                task.setStatus(Status.PAUSE);
                task.setStartTime(LocalTime.now());
                tableModel.setValueAt(task.getStartTime(), selectedRow, 2);
                tableModel.setValueAt(task.getEffectiveTime(), selectedRow, 4);
                task.setFirstTime(false);

            }
            else if (!task.isFirstTime() && task.getStatus() == Status.PAUSE) {
                task.setStatus(Status.PLAY);
                task.setFirstTime(false);
                //System.out.println("Duration: " + Duration.between(start, end));
                //System.out.println("Effective: " + task.getEffectiveTime());
                task.setEffectiveTime(task.getEffectiveTime().plusMinutes(Duration.between(LocalTime.now(), task.getStartTime()).toMinutes()));
                tableModel.setValueAt(task.getEffectiveTime(), selectedRow, 4);
            }
            else if (!task.isFirstTime() && task.getStatus() == Status.PLAY) {
                task.setStatus(Status.PAUSE);
                task.setFirstTime(false);
                task.setStartTime(LocalTime.now());
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Select a valid task");
    }

    public void stop() {
        int selectedRow = taskTable.getSelectedRow();
        int rowCount = taskTable.getRowCount();

        if(selectedRow >= 0 && rowCount >= 1) {
            Task task = find((int)taskTable.getValueAt(selectedRow, 0));

            if (!task.isFirstTime() && task.getStatus() == Status.PAUSE) {
                task.setStatus(Status.PLAY);
                task.setFirstTime(true);
                task.setEndTime(LocalTime.now());
                tableModel.setValueAt(task.getEndTime(), selectedRow, 3);
            }
            else if (!task.isFirstTime() && task.getStatus() == Status.PLAY) {
                task.setStatus(Status.PLAY);
                task.setFirstTime(true);
                task.setEndTime(LocalTime.now());
                tableModel.setValueAt(task.getEndTime(), selectedRow, 3);
                task.setEffectiveTime(task.getEffectiveTime().plusMinutes(Duration.between(LocalTime.now(), task.getStartTime()).toMinutes()));
                tableModel.setValueAt(task.getEffectiveTime(), selectedRow, 4);
            }

            updateTask(task);
        }
        else
            JOptionPane.showMessageDialog(null, "Select a valid task");
    }

    /**
     * Updates the <code>JTable</code>'s information with current user tasks.
     */
    public void refreshTable(int option) {
        if (option == 1) {
            Task task = new Task().getLast();
            taskList.add(task);
            tableModel.addRow(new Object[]{task.getId(), task.getName(), dtf.format(task.getStartTime()), dtf.format(task.getEndTime()), dtf.format(task.getEffectiveTime())});
            Bitacora.addMessage(task.getName());
        }
        else if (option == 2) {
            taskList.removeIf(obj -> obj.getId() == (int)tableModel.getValueAt(taskTable.getSelectedRow(), 0));
            taskTable.getCellEditor().stopCellEditing();
            tableModel.removeRow(taskTable.getSelectedRow());
            taskTable.repaint();
        }
    }
}