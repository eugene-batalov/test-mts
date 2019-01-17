package mts.testrest.rest;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.Month;
import mts.testrest.model.Task;
import mts.testrest.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskRestController.class)
public class TaskRestControllerTest {

    private static final String ID = "ff95e50c-5c77-4bd3-969b-457066f11834";
    private static final String TASK = "/task";

    @MockBean
    private TaskService taskService;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostTask() throws Exception {
        when(taskService.createTask()).thenReturn(createTask(ID, Task.Status.CREATED));

        mvc.perform(post(TASK)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().string(ID));
    }

    @Test
    public void testGreeting() throws Exception {
        when(taskService.getTask(ID)).thenReturn(createTask(ID, Task.Status.RUNNING));

        mvc.perform(get(TASK + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Task.Status.RUNNING.toString().toLowerCase())));
    }

    private Task createTask(String id, Task.Status status) {
        return Task.builder()
                .id(id)
                .status(status.toString().toLowerCase())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
