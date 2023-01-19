package com.happystays.book.query.api.controllers;

import com.happystays.cqrs.core.infrastucture.QueryDispatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MyTripsController.class})
@ExtendWith(SpringExtension.class)
class MyTripsControllerTest {
    @Autowired
    private MyTripsController myTripsController;

    @MockBean
    private QueryDispatcher queryDispatcher;

    @Test
    void testGetMyTrips() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/myTrips");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(myTripsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Failed to get trips by ID request!\",\"myTrips\":null}"));
    }

    @Test
    void testGetMyTrips2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/myTrips", "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(myTripsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Failed to get trips by ID request!\",\"myTrips\":null}"));
    }
}

