package ro.ubb.pm.bll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.model.Epic;
import ro.ubb.pm.model.Task;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest(classes = BLLTest.class)
@RunWith(SpringRunner.class)
public class TaskBLLTest {
    @Autowired
    TaskBLL taskBLL;

    List<Task> result;

    @Test
    public void testGetAllTasksForASprint(){

        //send valid sprint id (expect 2 rows in db)
        result= taskBLL.getAllTasksForASprint(1);
        Assert.assertEquals(2, result.size());

        //invalid sprint id(there is no sprint with this id)
        Throwable exception = Assert.assertThrows(EntityNotFoundException.class, ()->{
            taskBLL.getAllTasksForASprint(-1); } );
        Assert.assertEquals(exception.getMessage(), "Unable to find ro.ubb.pm.model.Sprint with id -1");



    }
}
