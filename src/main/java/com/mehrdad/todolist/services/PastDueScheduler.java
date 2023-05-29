package com.mehrdad.todolist.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PastDueScheduler {
    private final TodoItemService todoItemService;

    public PastDueScheduler(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @Scheduled(cron = "${app.scheduler.delay-cron}")
    public void handleTodoItemStatusPastDue() {
        //ideally this scheduled task should be called in some limited event based domain. for example, we should load all the item just for
        // a particular member. possibly an event should  be sent for example when a user logs into the app and our event
        // contains a member id, and we could retrieve items for that member


       todoItemService.handleTodoItemStatusPastDue();
    }
}
