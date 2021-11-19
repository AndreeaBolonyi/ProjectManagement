package ro.ubb.pm.bll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ubb.pm.dal.SprintsRepository;

@SpringBootTest(classes = BLLTest.class)
@RunWith(SpringRunner.class)
public class SprintBLLTest {

    @Autowired
    SprintsRepository sprintRepo;

    @Before
    public void initData(){

    }

    @Test
    public void testGetAll(){
        Assert.assertEquals(1, sprintRepo.findAll().size());
    }
}
