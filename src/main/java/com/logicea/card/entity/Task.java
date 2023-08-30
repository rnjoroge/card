package com.logicea.card.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


// Annotations
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", length = 50)
    private int taskId;

    @Column(name = "user_id")
    @NotNull(message = "userid is required")
    //@ManyToOne
    //@JoinColumn(name="user_id")
    private int userId;
    
    @Column(name = "task_name", length = 50)
    @Size(max = 20, min = 3, message = "{task.name.invalid}")
    @NotEmpty(message = "Name is required")
    private String taskName;

    @Column(name = "task_description", length = 50)
    private String taskDescription;
    
    // should be enum
    @Column(name = "task_status", length = 50)
    private String taskStatus;

    @Column(name = "task_color", length = 50)
    private String taskColor;

    @Column(name = "created_date", length = 50)
    private Date taskCreatedDate;

    public Task() {}

    public Task(int taskId,int userId ,String taskName,String taskDescription,String taskStatus,Date taskCreatedDate,String taskColor) {
      this.taskId=taskId;
      this.userId=userId;
      this.taskName=taskName;
      this.taskStatus=taskStatus;
      this.taskDescription=taskDescription;
      this.taskCreatedDate=taskCreatedDate;
      this.taskColor=taskColor;
    }

    public int getTaskId () {
        return this.taskId;
    }
    public void setTaskId(int taskid) {
        this.taskId = taskid;
    }

    public int getUserId () {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTaskName () {
        return this.taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription () {
        return this.taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    } 
    
    public String getTaskStatus () {
        return this.taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    } 
    
    public String getColor () {
        return this.taskColor;
    }
    public void setColor(String taskColor) {
        this.taskColor = taskColor;
    } 

    public Date getTaskCreatedDate () {
        return this.taskCreatedDate;
    }
    public void setTaskCreatedDate(Date taskCreatedDate) {
        this.taskCreatedDate = taskCreatedDate;
    } 
    
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", userId='" + userId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskStatus=" + taskStatus +               
                ", taskDescription=" + taskDescription +
                ", taskCreatedDate=" + taskCreatedDate.toString() +
                '}';
    }

}  
