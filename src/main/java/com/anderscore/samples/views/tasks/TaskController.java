package com.anderscore.samples.views.tasks;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TaskController {

    private int nextId = 1;
    private Map<Integer, Task> fakeRepository = new HashMap<>();

    public Task createTask() {
        return Task.builder().build();
    }

    public Task openTask(Optional<String> taskId) {
        return taskId.map(Integer::parseInt)
                .map(fakeRepository::get)
                .filter(Objects::nonNull)
                .orElseGet(this::createTask);
    }

    public Task saveTask(Task task) {
        Task copy = task.copy();
        if (copy.getId() == null) {
            copy.setId(++nextId);
        }
        fakeRepository.put(copy.getId(), copy);
        return copy;
    }

    public void deleteTask(Task task) {
        fakeRepository.remove(task.getId());
    }
}
