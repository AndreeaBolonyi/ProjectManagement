import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.bll.validator.Validator;
import ro.ubb.pm.model.User;

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@ComponentScan({"ro.ubb.pm.bll"})
public class ValidatorUserTest {

    @Autowired
    Validator<User> validator;

    User user;

    @Before
    public void initData(){
       // validator = new ValidatorUser();
        user = new User();
    }

    @Test
    public void testValidator(){

        Throwable exception;
       exception = Assert.assertThrows(ValidationException.class,
                ()->{ validator.validate(user);} );
        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!Parola este invalida!");


        user.setEmail("test");
        user.setPassword("notanemptypassword");
        exception = Assert.assertThrows(ValidationException.class,
                ()->{ validator.validate(user);} );

        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!");
        user.setEmail("myemailaccountisvalid");
        validator.validate(user);

        user.setPassword("");
        exception = Assert.assertThrows(ValidationException.class,
                ()->{ validator.validate(user);} );
        Assert.assertEquals(exception.getMessage(), "Parola este invalida!");





    }
}
