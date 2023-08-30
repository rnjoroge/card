package com.logicea.card.service;

import com.logicea.card.entity.Task;
import com.logicea.card.repository.TaskRepository;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task saveTask(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public List<Task> fetchAllTasks() {
        return (List<Task>)
        taskRepository.findAll();
    }

    @Override
    public List<Task> fetchUserTasks(int userid) {
       return taskRepository.findByuserId(userid);
    }

    @Override
    public Task updateTask(Task task, long taskId) {
        Task taskDb
        = taskRepository.findById(taskId)
              .get();

        if (Objects.nonNull(task.getTaskName())
            && !"".equalsIgnoreCase(
                task.getTaskName())) {
            taskDb.setTaskName(
                task.getTaskName());
        }  
        
        if (Objects.nonNull(task.getColor())
            && !"".equalsIgnoreCase(
                task.getColor())) {
            taskDb.setColor(
                task.getColor());
        }   
        
        if (Objects.nonNull(task.getTaskDescription())
            && !"".equalsIgnoreCase(
                task.getTaskDescription())) {
            taskDb.setTaskDescription(
                task.getTaskDescription());
        } 
        
        if (Objects.nonNull(task.getTaskStatus())
            && !"".equalsIgnoreCase(
                task.getTaskStatus())) {
            taskDb.setTaskStatus(
                task.getTaskStatus());
        }   

      return taskRepository.save(taskDb);       
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task getTask(int taskId) {
        return taskRepository.findBytaskId(taskId);
      
    }

    @Override
    public List<Task> search(int userId,String keyword) {
           return taskRepository.findByuserId(userId);
    }
    
}
