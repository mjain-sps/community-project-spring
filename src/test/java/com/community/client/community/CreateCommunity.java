package com.community.client.community;

import com.community.client.controllers.CommunityController;
import com.community.client.controllers.MainController;
import com.community.client.controllers.UserObjectController;
import com.community.client.models.Community;
import com.community.client.models.UserObject;
import com.community.client.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CommunityController.class)
public class CreateCommunity {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    @MockBean
    private EventService eventService;

    @MockBean
    private UserObjectService userObjectService;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private ContactService contactService;

    @MockBean
    private AdminViewAboutService adminViewAboutService;


    @Test
    public void createCommunityTest() throws Exception {
        //First we create a user;
        UserObject userObject = new UserObject(1L, "test-user", "test-email", "12345678");
        Mockito.when(userObjectService.getUserById(1L)).thenReturn(userObject);

        //We use this dummy user to create a community
        this.mockMvc.perform(post("/api/add-community/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Community(1L, "test-community", "test-description", "test-image")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());


    }




    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
