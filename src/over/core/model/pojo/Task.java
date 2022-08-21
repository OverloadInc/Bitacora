package over.core.model.pojo;

import over.core.model.db.DBConnection;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * <code>Task</code> class encapsulates the functionalities to store and control the user tasks information.
 * @author Overload Inc.
 * @version 1.0, 01 Jul 2022
 */
public class Task {
    private int id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime effectiveTime;
    private Date date;
    private boolean firstTime = true;
    private Status status = Status.PLAY;

    /**
     * Sets the id user task.
     * @param id the id user task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the user task name.
     * @param name the user task name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the user task start time.
     * @param startTime the user task start time.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the user task end time.
     * @param endTime the user task end time.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets the user task effective time.
     * @param effectiveTime the user task effective time.
     */
    public void setEffectiveTime(LocalTime effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Sets the user task register date.
     * @param date the user task register date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets the flag to indicate a user task first usage.
     * @param firstTime the usage flag. <code>true</code> if the task is used for first time; <code>false</code> otherwise.
     */
    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    /**
     * Sets the user task status.
     * @param status the user task status; <code>PLAY</code> or <code>PAUSE</code>.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the user task id.
     * @return the user task id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the user task name.
     * @return the user task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user task start time.
     * @return the user task start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the user task end time.
     * @return the user task end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the user task effective time.
     * @return the user task effective time.
     */
    public LocalTime getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Gets the user task register date.
     * @return the user task register date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Gets the flag to indicate the user task first usage.
     * @return <code>true</code> if the task is used for first time; <code>false</code> otherwise.
     */
    public boolean isFirstTime() {
        return firstTime;
    }

    /**
     * Gets the user task status.
     * @return <code>PLAY</code> or <code>PAUSE</code>.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets all the tasks stored in the Database.
     * @return the <code>ArrayList</code> of user tasks.
     */
    public ArrayList<Task> get() {
        ArrayList<Task> taskList = new ArrayList<>();

        String query = "SELECT * FROM tasks ORDER BY id_task ASC;";

        try {
            DBConnection dbConnection = DBConnection.getInstance();
            dbConnection.connect();

            ResultSet resultSet = dbConnection.executeQuery(query);

            while(resultSet.next()) {
                Task task = new Task();

                task.setId(resultSet.getInt("id_task"));
                task.setName(resultSet.getString("name"));
                task.setStartTime(resultSet.getTime("start_time").toLocalTime());
                task.setEndTime(resultSet.getTime("end_time").toLocalTime());
                task.setEffectiveTime(resultSet.getTime("effective_time").toLocalTime());
                task.setDate(resultSet.getDate("register_date"));

                taskList.add(task);
            }

            dbConnection.disconnect();
        }
        catch (Exception e) {
        }

        return taskList;
    }

    /**
     * Gets a specific task stored in the Database.
     * @param id the user task id.
     * @return the <code>Task</code> object with the information requested.
     */
    public Task get(int id) {
        Task task = new Task();

        String query = "SELECT * FROM tasks WHERE id_task = " + id + ";";

        try {
            DBConnection dbConnection = DBConnection.getInstance();
            dbConnection.connect();

            ResultSet resultSet = dbConnection.executeQuery(query);

            while(resultSet.next()) {
                task.setId(resultSet.getInt("id_task"));
                task.setName(resultSet.getString("name"));
                task.setStartTime(resultSet.getTime("start_time").toLocalTime());
                task.setEndTime(resultSet.getTime("end_time").toLocalTime());
                task.setEffectiveTime(resultSet.getTime("effective_time").toLocalTime());
                task.setDate(resultSet.getDate("register_date"));
            }

            dbConnection.disconnect();
        }
        catch (Exception e) {
        }

        return task;
    }

    /**
     * Gets the last user task stored in the Database.
     * @return a <code>Task</code> object with the user task information.
     */
    public Task getLast() {
        Task task = new Task();
        String query = "SELECT * FROM tasks WHERE id_task = (SELECT max(id_task) FROM tasks);";

        try {
            DBConnection dbConnection = DBConnection.getInstance();
            dbConnection.connect();

            ResultSet resultSet = dbConnection.executeQuery(query);

            while(resultSet.next()) {
                task.setId(resultSet.getInt("id_task"));
                task.setName(resultSet.getString("name"));
                task.setStartTime(resultSet.getTime("start_time").toLocalTime());
                task.setEndTime(resultSet.getTime("end_time").toLocalTime());
                task.setEffectiveTime(resultSet.getTime("effective_time").toLocalTime());
                task.setDate(resultSet.getDate("register_date"));
            }

            dbConnection.disconnect();
        }
        catch (Exception e) {
        }

        return task;
    }

    /**
     * Adds a new user task in the Database.
     * @return <code>true</code> if the user task is stored correctly; <code>false</code> otherwise.
     */
    public boolean add() {
        boolean result = false;
        String command = "INSERT INTO tasks (name, start_time, end_time, effective_time, register_date) VALUES ('" + this.name + "', '" + this.startTime + "', '" + this.endTime + "', '" + this.effectiveTime + "', '" + this.date + "');";

        try {
            DBConnection dbConnection = DBConnection.getInstance();
            dbConnection.connect();

            result = dbConnection.executeCommand(command);

            dbConnection.disconnect();
        }
        catch(Exception e) {
        }

        return result;
    }

    /**
     * Deletes a specific user task stored in the Database.
     * @return <code>true</code> if the user task is removed; <code>false</code> otherwise.
     */
    public boolean delete() {
        boolean result = false;
        String command = "DELETE FROM tasks WHERE id_task = " + this.id + ";";

        try {
            DBConnection dbConnection = DBConnection.getInstance();
            dbConnection.connect();

            result = dbConnection.executeCommand(command);

            dbConnection.disconnect();
        }
        catch (Exception e) {
        }

        return result;
    }

    /**
     * Updates the information of a specific user task.
     * @return <code>true</code> if the user task information is updated; <code>false</code> otherwise.
     */
    public boolean update() {
        boolean result = false;
        String command = "UPDATE tasks SET name = '" + this.name + "', start_time = '" + this.startTime + "', end_time = '" + this.endTime + "', effective_time = '" + this.effectiveTime + "', register_date = '" + this.date + "' WHERE id_task = " + this.id + ";";

        try {
            DBConnection dbConnection = DBConnection.getInstance();
            dbConnection.connect();

            result = dbConnection.executeCommand(command);

            dbConnection.disconnect();
        }
        catch (Exception e) {
        }

        return result;
    }
}