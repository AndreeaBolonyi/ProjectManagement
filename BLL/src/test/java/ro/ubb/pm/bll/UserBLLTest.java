package ro.ubb.pm.bll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.model.User;

@SpringBootTest(classes = BLLTest.class)
@RunWith(SpringRunner.class)
public class UserBLLTest {

    @Autowired
    UserBLL userBLL;

    User user;
    boolean foundException;

    @Before
    public void initData(){
        user = new User();
        foundException = false;
    }

    @Test
    public void testLogin() {

        //invalid user
        Throwable exception = Assert.assertThrows(ValidationException.class, ()->{
            userBLL.login(user.getEmail(),user.getPassword()); } );
        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!Parola este invalida!");


        //invalid password

        user.setEmail("cristina@yahoo.com");
        user.setPassword("notmypass");
        exception = Assert.assertThrows(ServerException.class, ()->{
            userBLL.login(user.getEmail(),user.getPassword()); } );
        Assert.assertEquals(exception.getMessage(), "The password you've entered is incorrect!");

        //correct
        user.setPassword("cristina");
        try{
            userBLL.login(user.getEmail(), user.getPassword());
        } catch (ServerException e) {
            this.foundException = true;
        }

        Assert.assertFalse(foundException);

    }

}
