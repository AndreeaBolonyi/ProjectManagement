package ro.ubb.pm.bll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ro.ubb.pm.dal.SprintsRepository;
import ro.ubb.pm.model.Sprint;

import java.util.List;

//
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan({"ro.ubb.pm.bll", "ro.ubb.pm.dal"})
//@EnableJpaRepositories(basePackages = {"ro.ubb.pm.dal"})
//@EnableAutoConfiguration
//@ComponentScan(basePackages={"ro.ubb.pm.bll", "ro.ubb.pm.dal"})
//@EnableJpaRepositories(basePackages="ro.ubb.pm.dal")
//@EnableTransactionManagement
//@EntityScan(basePackages="ro.ubb.pm.model")
//@DataJpaTest
//@Import(SprintsRepository.class)
@EnableConfigurationProperties
@RunWith(SpringRunner.class)
public class SprintBLLTest {

//    @Autowired
//    SprintBLL  sprintBLL;

    @Autowired
    SprintsRepository repo;


    @Before
    public void initData(){

    }

    @Test
    public void testGetAll(){
        List<Sprint> all ;//= sprintBLL.getAllSprints();
        all = repo.findAll();
       // Assert.assertEquals(0, sprintBLL.getAllSprints().size());
    }
}
