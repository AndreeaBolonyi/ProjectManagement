import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import ro.ubb.pm.bll.validator.ValidationException;
import ro.ubb.pm.bll.validator.Validator;
import ro.ubb.pm.bll.validator.ValidatorUser;
import ro.ubb.pm.dal.EpicsRepository;
import ro.ubb.pm.model.User;

/*@SpringBootTest
@ComponentScan(basePackages = {"ro.ubb.pm"})
@EnableJpaRepositories(basePackages = {"ro.ubb.pm.dal"})*/
@SpringBootTest
public class ValidatorUserTest {

    /*@Autowired
    Validator<User> validatorUser;*/
    User user;

    @MockBean
    EpicsRepository epicsRepository;

    /*@Before
    public void initData(){
        //validator = new ValidatorUser();

    }*/

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
    }

    @Test
    public void testValidator() {

        epicsRepository.findAll();

         //Throwable exception;
//       exception = Assert.assertThrows(ValidationException.class,
//                ()->{ validator.validate(user);} );
//        System.out.println(exception.getMessage());
//        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!Parola este invalida!");
//

        user.setEmail("test");
        user.setPassword("notanemptypassword");
        //validatorUser.validate(user);
        /*exception = Assert.assertThrows(ValidationException.class,
                ()->{ validator.validate(user);} );

        Assert.assertEquals(exception.getMessage(), "Adresa de e-mail este invalida!");
        user.setEmail("myemailaccountisvalid");
        validator.validate(user);

        user.setPassword("");
        exception = Assert.assertThrows(ValidationException.class,
                ()->{ validator.validate(user);} );
        Assert.assertEquals(exception.getMessage(), "Parola este invalida!");*/

    }
}
