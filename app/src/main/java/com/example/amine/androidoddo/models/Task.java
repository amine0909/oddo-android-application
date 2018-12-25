package com.example.amine.androidoddo.models;


import java.util.Date;

public class Task {

    private Long id;
    private String name;
    private String description;
    private String stage_name;
    private String start_date;
    private String deadLine;
    private String employee_name;
    private Long project_id;



    public Task(Long id, String name, String stage_name, String start_date, String employee_name, String description, String deadline, Long project_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stage_name = stage_name;
        this.start_date = start_date;
        this.deadLine = deadline;
        this.employee_name = employee_name;
        this.project_id = project_id;
    }

    public Task(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_id) {
        this.stage_name = stage_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stage_id=" + stage_name +
                ", start_date=" + start_date +
                ", deadLine=" + deadLine +
                ", employee_name='" + employee_name + '\'' +
                '}';
    }
}
