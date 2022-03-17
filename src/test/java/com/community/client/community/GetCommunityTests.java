package com.community.client.community;

import com.community.client.controllers.MainController;
import com.community.client.models.Community;
import com.community.client.services.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class GetCommunityTests {

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

    //Test to get the communities list page content loaded and check
    @Test
    public void getCommunityLightWeightTest() throws Exception{
        Community community = new Community(1L, "test community", "test-description", "test-image");
        Set<Community> communitySet = new HashSet<>();
        communitySet.add(community);
        given(this.communityService.getAllCommunities()).willReturn(communitySet);

        this.mockMvc.perform(get("/communities")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test community")));
    }

}
