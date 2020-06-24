package com.example.demo;

import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @BeforeEach
   public void initDb(){
        {
           User newsUser = new User("test@user.com","testUser", "12345");
           userService.createUser(newsUser);
       }
       {
           User newsUser = new User("admin@user.com","testAdmin", "12345");
           userService.createAdmin(newsUser);
       }
       {
           Task userTask = new Task("03/01/2018", "00:11","11:00", "No need");
           User user = userService.findOne("test@user.com");
           taskService.addTask(userTask,user);
       }
   }
   @Test
    public void testUser(){
       User user = userService.findOne("test@user.com");
       assertNotNull(user);
       User admin = userService.findOne("admin@user.com");
       assertEquals(admin.getEmail(), "admin@user.com");
   }
   @Test
    public void testTask(){
       User user = userService.findOne("test@user.com");
       List<Task> task= taskService.findUserTask(user);
       assertNotNull(task);
   }

}
