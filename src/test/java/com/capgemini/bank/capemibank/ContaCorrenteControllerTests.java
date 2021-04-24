package com.capgemini.bank.capemibank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ContaCorrenteControllerTests extends CapemibankApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getFind() throws Exception {
        mvc.perform(get("/conta")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

//    @Test
//    public void postSaldo() throws Exception {
//        mvc.perform(post("/conta/saldo")
//                .content(contaCorrenteAbstract.toString())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("result", 0L))
//                .andReturn();
//    }

}
