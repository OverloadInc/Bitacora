package over.core.model;

import java.time.LocalTime;

/**
 * <code>Task</code> class.
 * @author Overload Inc.
 * @version 1.0, 01 Jul 2022
 */
public class Task {
    private int id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime effectiveTime;
    private boolean firstTime;
    private Status status;

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @param endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @param effectiveTime
     */
    public void setEffectiveTime(LocalTime effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     *
     * @param firstTime
     */
    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     *
     * @return
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     *
     * @return
     */
    public LocalTime getEffectiveTime() {
        return effectiveTime;
    }

    /**
     *
     * @return
     */
    public boolean isFirstTime() {
        return firstTime;
    }

    /**
     *
     * @return
     */
    public Status getStatus() {
        return status;
    }

    public void addTask() {

    }
}