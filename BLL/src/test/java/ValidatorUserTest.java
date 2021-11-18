import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.bll.validator.ValidatorUser;
import ro.ubb.pm.model.User;

public class ValidatorUserTest {

    ValidatorUser validator;
    User user;

    @Before
    public void initData(){
        validator = new ValidatorUser();
        user = new User();
    }

    @Test
    public void testValidator(){

         Throwable exception;
//       exception = Assert.assertThrows(ValidationException.class,
//                ()->{ validator.validate(user);} );
//        System.out.println(exception.getMessage());
//        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!Parola este invalida!");
//

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
