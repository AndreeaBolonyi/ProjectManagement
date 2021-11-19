package ro.ubb.pm.bll;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ubb.pm.model.User;

@SpringBootTest(classes = BLLTest.class)
@RunWith(SpringRunner.class)
public class UserBLLTest {

    @Autowired
    UserBLL userBLL;

    @Test
    public void testLogin() {
        User user = new User();
        user.setEmail("cristina@yahoo.com");
        user.setPassword("christine");
        Throwable exception = Assert.assertThrows(ServerException.class, ()->{ userBLL.login(user); } );
        Assert.assertEquals(exception.getMessage(), "The password you've entered is incorrect!");
    }

}
