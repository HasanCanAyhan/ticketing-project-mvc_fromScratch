package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl extends AbstractMapServiceDB<Long,TaskDTO> implements TaskService {
    @Override
    public void save(TaskDTO task) {

        task.setId(UUID.randomUUID().getMostSignificantBits());

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
        super.update(task.getId(),task);
    }
}
