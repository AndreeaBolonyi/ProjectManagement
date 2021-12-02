import org.junit.Assert;
import org.junit.Test;
import ro.ubb.pm.model.dtos.TaskDTO;

import java.time.LocalDate;

public class TaskDTOTest {

    private TaskDTO taskDTO;


    @Test
    public void testTaskDTOProperties(){
        Assert.assertNull(taskDTO);

        taskDTO = new TaskDTO();
        Assert.assertNotNull(taskDTO);

        //test created
        taskDTO.setCreated(LocalDate.parse("2020-09-10"));
        Assert.assertEquals(2020, taskDTO.getCreated().getYear());
        Assert.assertEquals(9, taskDTO.getCreated().getMonthValue());

        //test title
        taskDTO.setTitle("MyTask");
        Assert.assertEquals("MyTask", taskDTO.getTitle());

        //test description
        taskDTO.setDescription("TaskDesc");
        Assert.assertEquals("TaskDesc",taskDTO.getDescription());

        //test assignedToId
        taskDTO.setAssignedToId(1);
        Assert.assertEquals(String.valueOf(1), String.valueOf(taskDTO.getAssignedToId()));

        //test createdById
        taskDTO.setCreatedById(11);
        Assert.assertEquals(String.valueOf(11), String.valueOf(taskDTO.getCreatedById()));

        //test userStoryId
        taskDTO.setUserStoryId(10);
        Assert.assertEquals(String.valueOf(10),String.valueOf(taskDTO.getUserStoryId()));

        //test id
        taskDTO.setId(1);
        Assert.assertEquals(String.valueOf(1), taskDTO.getId().toString());


    }


}
