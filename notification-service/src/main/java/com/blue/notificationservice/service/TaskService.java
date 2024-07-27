package com.blue.notificationservice.service;

import com.blue.notificationservice.enitity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "task-service")
public interface TaskService {

    @GetMapping("/task/userId/{userId}")
    ResponseEntity<List<Task>> getTasksByUserId(@PathVariable("userId") Long userId);
}
