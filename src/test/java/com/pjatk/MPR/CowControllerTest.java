package com.pjatk.MPR;

import com.pjatk.MPR.controller.CowController;
import com.pjatk.MPR.model.Cow;
import com.pjatk.MPR.service.MyCowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CowControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MyCowService service;

    @InjectMocks
    private CowController controller;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

//    @Test
//    public void getByNameReturns200WhenCowIsPresent() throws Exception {
//        Cow cow = new Cow(1L,"Bernard", 6);
//        when(service.getCowByName("Bernard")).thenReturn(cow);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/cow/Bernard"))
//                .andExpect(status().isOk());
//    }

    @Test
    public void cowDelDeletesCowsReturns204() throws Exception {
        String requestedName = "Deleter";
        mockMvc.perform(delete("/cowDel/{name}", requestedName))
                .andExpect(result -> {
                    if (result.getResponse().getStatus() != 204) {
                        System.out.println("Expected status 204, but got: " + result.getResponse().getStatus());
                        System.out.println("Response content: " + result.getResponse().getContentAsString());
                    }
                })
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteCowByName(requestedName);
    }
}
