package com.logicea.card.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.logicea.card.entity.Task;
import com.logicea.card.payload.response.MessageResponse;
import com.logicea.card.security.authentication.IAuthenticationFacade;
import com.logicea.card.security.services.UserDetailsImpl;
import com.logicea.card.service.TaskService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/card")
public class CardController {

    // Annotation
    @Autowired private TaskService taskService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @PostMapping("/create")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveTask(
        @Valid @RequestBody Task task)
    {
        Authentication authentication = authenticationFacade.getAuthentication();
        UserDetailsImpl userdet = (UserDetailsImpl)authentication.getPrincipal();

        if(task.getColor()!=null) {
            if(!this.ValidateColor(task.getColor())) {
             return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: invalid color should conform to a “6 alphanumeric characters prefixed with a #“ format"));
            }
        }

        task.setUserId(userdet.getId());
        task.setTaskCreatedDate(new Date());
        task.setTaskStatus("To Do");
        taskService.saveTask(task);
        
         return new ResponseEntity<Task>(task,  HttpStatus.CREATED);
        // return ResponseEntity.ok(new MessageResponse("task created  successfully!"));
    }

    @GetMapping("/list")
    public List<Task> fetchUserTasks()
    {
        Authentication authentication = authenticationFacade.getAuthentication();
        UserDetailsImpl userdet = (UserDetailsImpl)authentication.getPrincipal();
        if(userdet.getAuthorities().contains("ADMIN")) {
            return taskService.fetchAllTasks();
        }
        return taskService.fetchUserTasks(userdet.getId());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> fetchUserTask(@PathVariable("taskId") int id)
    {
        Authentication authentication = authenticationFacade.getAuthentication();
        UserDetailsImpl userdet = (UserDetailsImpl)authentication.getPrincipal();
        
        Task task = taskService.getTask((id));
        if(task!=null) {
            if(task.getUserId() ==userdet.getId() || userdet.getAuthorities().contains("ADMIN")) {
                return ResponseEntity.ok(task);
            }
            else{
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: the task does not belong to the user"));
            }
         }
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: invalid taskId"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskId(@PathVariable("id")
                                       int taskId)
    {
        
        Authentication authentication = authenticationFacade.getAuthentication();
        UserDetailsImpl userdet = (UserDetailsImpl)authentication.getPrincipal();
        
        Task task = taskService.getTask((taskId));

        if(task!=null) {
             if(task.getUserId() ==userdet.getId() || userdet.getAuthorities().contains("ADMIN")) {
               taskService.deleteTaskById(Long.valueOf(taskId));
                return ResponseEntity.ok("task deleted ");
             }
             else{
              return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: the task does not belong to the user"));
              } 
            }
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: invalid taskId"));
   
    }
        

    @PutMapping("/{id}")
    public ResponseEntity<?>
      updateTask(@RequestBody Task task,
                     @PathVariable("id") int taskId)
    {
            
        Authentication authentication = authenticationFacade.getAuthentication();
        UserDetailsImpl userdet = (UserDetailsImpl)authentication.getPrincipal();
        
        Task taskdetails = taskService.getTask((taskId));

             if(taskdetails!=null) {
             if(taskdetails.getUserId() ==userdet.getId() || userdet.getAuthorities().contains("ADMIN")) {
                 if(task.getColor()!=null) {
                        if(!this.ValidateColor(task.getColor())) {
                            return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: invalid color should conform to a “6 alphanumeric characters prefixed with a #“ format"));
                        }
                 }
                 if(task.getTaskStatus()!=null) {
                   
                    List<String> statuses = Arrays.asList("To Do","In Progress", "Done");
                    if(!statuses.contains(task.getTaskStatus())) {
                        return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: invalid status ,status should be To Do, In Progress or Done"));
                        
                    }
                 }
                 taskService.updateTask(task,Long.valueOf(taskId));

                return ResponseEntity.ok("task updated ");
               }
             else{             
              return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: the task does not belong to the user"));    
              } 
            }
          
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: invalid taskId"));
    }  
    
 
    private boolean ValidateColor (String color) {
     
        char firstChar = color.charAt(0);
        if(firstChar == '#') {
            String colorcode =color.substring(color.lastIndexOf("#") + 1);
            if(colorcode.length() != 6) {
              return false;
            }
            String alphanumericRegex = "^[a-zA-Z0-9]+$";
            Pattern pattern = Pattern.compile(alphanumericRegex);
            Matcher matcher = pattern.matcher(colorcode);

            return matcher.matches();
        }
        return false;

    }
}
