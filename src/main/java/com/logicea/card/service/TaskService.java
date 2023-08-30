package com.logicea.card.service;

import com.logicea.card.entity.Task;
import java.util.List;

public interface TaskService {
    Task saveTask (Task task);
    Task getTask (int taskId);
    List<Task> fetchAllTasks();
    List<Task> fetchUserTasks(int userid);
    Task updateTask(Task task,long taskId);
    void deleteTaskById(Long taskId);
    List<Task> search(int userId,String keyword);
}
