package com.springboot.slice.controller.basic;

import com.google.gson.Gson;
import com.springboot.member.dto.MemberDto;
import com.springboot.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest         // (1)
@AutoConfigureMockMvc   // (2)
public class ControllerTestDefaultStructure {
    // (3)
    @Autowired
    private MockMvc mockMvc;

    // (4)
    @Autowired
    private Gson gson;

//    @MockBean
//    private MemberService memberService;
    @Test
    public void postMemberTest() throws Exception {
        // (5) 테스트용 request body 생성
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com", "홍길동", "010-1111-1111");
        String content = gson.toJson(post);

        ResultActions actions = mockMvc.perform(
                post("/v11/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        actions.andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/v11/members/"))));
        // (6) MockMvc 객체로 테스트 대상 Controller 호출

        // (7) Controller 핸들러 메서드에서 응답으로 수신한 HTTP Status 및 response body 검증
    }
}
