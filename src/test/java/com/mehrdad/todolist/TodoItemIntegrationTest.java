package com.mehrdad.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehrdad.todolist.model.TodoItemDTO;
import com.mehrdad.todolist.models.TodoItem;
import com.mehrdad.todolist.repositories.TodoItemRepository;
import com.mehrdad.todolist.services.PastDueScheduler;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TodoItemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Before
    public void setUp() {

        // reset DB
        todoItemRepository.deleteAll();
    }

    @After
    public void tearDown() {

        // reset DB
        todoItemRepository.deleteAll();
    }

    @Test
    public void givenEmployees_whenGetAll_thenReturnEmployess() throws Exception {

        // given
        final String shoppingDes = "shop";
        final String writingDes = "write";
        createTodoItem(shoppingDes, false);
        createTodoItem(writingDes, false);

        mockMvc
                // when
                .perform(
                        MockMvcRequestBuilders.get("/todos")
                                .accept(MediaType.APPLICATION_JSON)
                )

                // then
                .andExpect(
                        status().isOk()
                )
                .andExpect(

                        content()
                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        jsonPath("$", Matchers.hasSize(2))
                )
                .andExpect(
                        jsonPath("$[0]", Matchers.notNullValue())
                )
                .andExpect(
                        jsonPath("$[0].description", CoreMatchers.is(shoppingDes))
                )
                .andExpect(
                        jsonPath("$[1]", Matchers.notNullValue())
                )
                .andExpect(
                        jsonPath("$[1].description", CoreMatchers.is(writingDes))
                )
        ;

    }

    @Test
    @MethodSource
    void givenTodoItem_whenPost_thenCreateAndTodoItem() throws Exception {

        // given
        final String des = "todo";
        final TodoItemDTO todoItemDTO = new TodoItemDTO(null, des, null, null, null);

        mockMvc
                // when
                .perform(
                        MockMvcRequestBuilders
                                .post("/todos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(todoItemDTO))
                )

                // then
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        content()
                                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(
                        jsonPath("$", Matchers.notNullValue())
                )
                .andExpect(
                        jsonPath("$.description", Matchers.is(des))
                )
        ;

    }

    @SpyBean private PastDueScheduler scheduleHandler;

    @Test
    void scheduleIsTriggered() {
        await()
                .atMost(Duration.of(1500, ChronoUnit.MILLIS))
                .untilAsserted(() -> verify(scheduleHandler, atLeast(1)).handleTodoItemStatusPastDue());
    }

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAndStatusIsChanged() {
        final String before = "before";
        createTodoItem(before, true);
        await()
                .atMost(1, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    List<TodoItem> all = todoItemRepository.findAll();
                    Assertions.assertThat(all).hasSize(1);
                    Assertions.assertThat(all.get(0).getStatus()).isEqualTo(TodoItem.StatusEnum.PAST_DUE);
                });

    }

    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAndStatusIsNotChanged() {
        final String after = "after";
        createTodoItem(after, false);
        await()
                .atMost(2, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    List<TodoItem> all = todoItemRepository.findAll();
                    Assertions.assertThat(all).hasSize(1);

                    Assertions.assertThat(all.get(0).getStatus()).isEqualTo(TodoItem.StatusEnum.NOT_DONE);
                });


    }

    private void createTodoItem(final String desc, boolean inPast) {

        TodoItem todoItem = new TodoItem();
        todoItem.setDescription(desc);
        todoItem.setStatus(TodoItem.StatusEnum.NOT_DONE);
        if (inPast)
            todoItem.setDueDateTime(LocalDateTime.now().minusDays(1));
        else todoItem.setDueDateTime(LocalDateTime.now().plusDays(1));

        todoItemRepository.saveAndFlush(todoItem);
    }
}

