package ro.ubb.pm.bll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ubb.pm.bll.exceptions.InvalidCredentialsException;
import ro.ubb.pm.bll.validator.Validator;
import ro.ubb.pm.model.User;

@SpringBootTest(classes = BLLTest.class)
@RunWith(SpringRunner.class)
public class ValidatorUserTest {

    @Autowired
    Validator<User> validator;

    User user;

    @Before
    public void initData(){
        user = new User();
    }

    @Test
    public void testValidator(){

        Throwable exception;
        exception = Assert.assertThrows(InvalidCredentialsException.class,
                ()->{ validator.validate(user);} );
        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!Parola este invalida!");


        user.setEmail("test");
        user.setPassword("notanemptypassword");
        exception = Assert.assertThrows(InvalidCredentialsException.class,
                ()->{ validator.validate(user);} );

        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!");
        user.setEmail("myemailaccountisvalid");
        //validator.validate(user);

        user.setPassword("");
        exception = Assert.assertThrows(InvalidCredentialsException.class,
                ()->{ validator.validate(user);} );
        Assert.assertEquals(exception.getMessage(), "Parola este invalida!");
    }
}