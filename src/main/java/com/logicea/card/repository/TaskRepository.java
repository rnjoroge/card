package com.logicea.card.repository;

import com.logicea.card.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    List<Task> findByuserId(int userid);  
    Task findBytaskId(int taskId);  

}

//    @Query("SELECT t FROM task t WHERE CONCAT(t.task_name, t.task_status, t.task_color) LIKE %?1%")
