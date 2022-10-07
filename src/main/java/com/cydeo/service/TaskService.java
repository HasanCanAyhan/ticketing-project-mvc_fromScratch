package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService extends CRUDService<Long, TaskDTO>{

    List<TaskDTO> getTasksNotCompleted(); // for pending tasks


    void updateStatus(TaskDTO task); // for inside pending task page update button

    List<TaskDTO> getAllTasksAreCompleted();// for archive tasks

}
