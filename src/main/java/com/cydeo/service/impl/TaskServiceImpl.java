package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapServiceDB<Long,TaskDTO> implements TaskService {
    @Override
    public void save(TaskDTO task) {

        if (task.getId() == null){
            task.setId(UUID.randomUUID().getMostSignificantBits());
        }


        if (task.getTaskStatus() == null){
            task.setTaskStatus(Status.OPEN);
        }

        if (task.getAssignedDate() == null){
            task.setAssignedDate(LocalDate.now());
        }


        super.save(task.getId(),task);
    }

    @Override
    public List<TaskDTO> readAll() {
        return super.readAll();
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return super.findById(taskId);
    }

    @Override
    public void deleteById(Long taskId) {
        super.deleteById(taskId);
    }

    @Override
    public void update(TaskDTO task) {

        //according to database

        task.setTaskStatus(  findById(task.getId()).getTaskStatus()  );
        task.setAssignedDate( findById(task.getId()).getAssignedDate()    );


        super.update(task.getId(),task);
    }

    @Override
    public List<TaskDTO> getTasksNotCompleted() {
        return readAll().stream().filter(task -> task.getTaskStatus() != Status.COMPLETE).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO task) {
        //                                   it comes from UI -Side
        findById(task.getId()).setTaskStatus(task.getTaskStatus());// First ,status is updated
        update(task);// Second, task is updated with the new status information

    }

    @Override
    public List<TaskDTO> getAllTasksAreCompleted() {
        return readAll().stream().filter(task -> task.getTaskStatus() == Status.COMPLETE).collect(Collectors.toList());
    }

}
