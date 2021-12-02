package ro.ubb.pm.bll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.pm.bll.tasks.TaskFactory;
import ro.ubb.pm.bll.tasks.TaskMapper;
import ro.ubb.pm.model.Task;
import ro.ubb.pm.model.User;
import ro.ubb.pm.model.UserStory;
import ro.ubb.pm.model.dtos.TaskDTO;

import java.time.LocalDate;

public class TaskFactoryTest {

    private TaskFactory taskFactory;
    private TaskMapper taskMapper;
    private Task taskToTest;
    private TaskDTO taskDTOToTest;

    @Before
    public void initData(){
        taskFactory = new TaskFactory();
        taskMapper = taskFactory.taskMapper();
        taskToTest = new Task();
        taskDTOToTest = new TaskDTO();
    }

    @Test
    public void testTaskToTaskDTO(){

        User myUser= new User();
        UserStory myUserStory = new UserStory();
        myUser.setId(10);
        myUserStory.setId(18);

        LocalDate myDate= LocalDate.parse("2021-11-09");
        taskToTest.setTitle("Great title.");
        taskToTest.setCreated(myDate);
        taskToTest.setDescription("A very long description.");
        taskToTest.setId(11);
        taskToTest.setAssignedTo(myUser);
        taskToTest.setCreatedBy(myUser);
        taskToTest.setUserStory(myUserStory);

        TaskDTO localTaskDTO= taskMapper.taskToTaskDTO(taskToTest);

        //test id
        Assert.assertEquals(String.valueOf(11),String.valueOf(localTaskDTO.getId()));

        //test title
        Assert.assertEquals("Great title.", localTaskDTO.getTitle());

        //test assignedToId
        Assert.assertEquals(String.valueOf(10), String.valueOf(localTaskDTO.getAssignedToId()));

        //test createdById
        Assert.assertEquals(String.valueOf(10), String.valueOf(localTaskDTO.getCreatedById()));

        //test created
        Assert.assertEquals(myDate, localTaskDTO.getCreated());

        //test description
        Assert.assertEquals("A very long description.", localTaskDTO.getDescription());

        //test userStoryId
        Assert.assertEquals(String.valueOf(18),String.valueOf(localTaskDTO.getUserStoryId()));

    }


    @Test
    public void testTaskDTOToTask(){

        LocalDate myDate= LocalDate.parse("2020-01-09");
        taskDTOToTest.setTitle("Great title.");
        taskDTOToTest.setCreated(myDate);
        taskDTOToTest.setDescription("A very long description.");
        taskDTOToTest.setId(11);


        Task localTask= taskMapper.taskDTOToTask(taskDTOToTest);

        //test id
        Assert.assertEquals(String.valueOf(11),String.valueOf(localTask.getId()));

        //test title
        Assert.assertEquals("Great title.", localTask.getTitle());

        //test created
        Assert.assertEquals(myDate, localTask.getCreated());

        //test description
        Assert.assertEquals("A very long description.", localTask.getDescription());

        //test userStory & assignedTo & createdBy
        Assert.assertNull(localTask.getAssignedTo());
        Assert.assertNull(localTask.getCreatedBy());
        Assert.assertNull(localTask.getUserStory());

    }
}
