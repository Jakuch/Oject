package pl.sdacademy.sdafinalprojectrest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sdacademy.sdafinalprojectrest.controller.ProjectController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SdaFinalProjectRestApplicationTests {

    @Autowired
    private ProjectController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
