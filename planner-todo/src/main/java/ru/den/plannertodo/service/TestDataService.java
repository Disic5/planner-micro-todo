package ru.den.plannertodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.den.planner.entity.Category;
import ru.den.planner.entity.Priority;
import ru.den.planner.entity.Task;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TestDataService {
    private final TaskService taskService;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    @KafkaListener(topics = "topic-test")
    public void listenKafka(Long userId){
        init(userId);
        System.out.println("new userId = " + userId);
    }

    public void init(Long userId) {
        Priority priority = new Priority();
        priority.setColor("#fff");
        priority.setTitle("High");
        priority.setUserId(userId);

        Priority priority2 = new Priority();
        priority2.setColor("#rrr");
        priority2.setTitle("Medium");
        priority2.setUserId(userId);

        priorityService.addPriority(priority);
        priorityService.addPriority(priority2);

        Category category = new Category();
        category.setTitle("Family");
        category.setUserId(userId);

        Category category2 = new Category();
        category2.setTitle("Work");
        category2.setUserId(userId);

        categoryService.addCategory(category);
        categoryService.addCategory(category2);

        Date tomorrow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tomorrow);
        calendar.add(Calendar.DATE, 1);
        tomorrow = calendar.getTime();

        Date oneWeek = new Date();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(oneWeek);
        calendar2.add(Calendar.DATE, 7);
        oneWeek = calendar2.getTime();

        Task task = new Task();
        task.setTitle("Walk");
        task.setCategory(category);
        task.setTaskData(tomorrow);
        task.setUserId(userId);
        task.setCompleted(true);
        task.setPriority(priority);

        Task task2 = new Task();
        task2.setTitle("Buy");
        task2.setCategory(category2);
        task2.setTaskData(oneWeek);
        task2.setUserId(userId);
        task2.setCompleted(false);
        task2.setPriority(priority2);

        taskService.addTask(task);
        taskService.addTask(task2);

    }
}
