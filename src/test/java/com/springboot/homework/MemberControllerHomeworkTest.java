package com.springboot.homework;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.springboot.member.controller.MemberController;
import com.springboot.member.dto.MemberDto;
import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.member.mapper.MemberMapper;
import com.springboot.member.service.MemberService;
import com.springboot.slice.controller.basic.ControllerTestDefaultStructure;
import com.springboot.stamp.Stamp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.springboot.member.entity.Member.MemberStatus.MEMBER_ACTIVE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Test
    void patchMemberTest() throws Exception {
        // TODO MemberController의 patchMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^

        MemberDto.Patch patch = MemberDto.Patch.builder()
                .phone("010-1234-1231")
                .build();

        Member member = new Member();
        member.setStamp(new Stamp());
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setMemberId(1L);
        member.setMemberStatus(MEMBER_ACTIVE);
        member.setPhone("010-1234-1231");

        MemberDto.response response = new MemberDto.response(
                1L, "hgd@gmail.com", "홍길동", "010-1234-1234", MEMBER_ACTIVE, new Stamp()
        );

        given(memberService.updateMember(Mockito.any(Member.class)))
                .willReturn(new Member());

        given(mapper.memberPatchToMember(Mockito.any(MemberDto.Patch.class)))
                .willReturn(new Member());

        given(mapper.memberToMemberResponse(Mockito.any(Member.class)))
                .willReturn(response);

        String content = gson.toJson(patch);

        ResultActions actions = mockMvc.perform(patch("/v11/members/1" )
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.phone").value(response.getPhone()));
    }

    @Test
    void getMemberTest() throws Exception {
        // TODO MemberController의 getMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
        Member member = new Member();
        member.setStamp(new Stamp());
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setMemberId(1L);
        member.setMemberStatus(MEMBER_ACTIVE);
        member.setPhone("010-1111-2222");



        MemberDto.response response = new MemberDto.response(
                1L, "hgd@gmail.com", "홍길동", "010-1234-1234", MEMBER_ACTIVE, new Stamp()
        );

        given(memberService.findMember(Mockito.any(Long.class)))
                .willReturn(member);

        given(mapper.memberToMemberResponse(Mockito.any(Member.class)))
                .willReturn(response);

       // String content = gson.toJson(member);

        ResultActions actions = mockMvc.perform(get("/v11/members/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
             //   .content(content));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(response.getMemberId()))
                .andExpect(jsonPath("$.data.email").value(response.getEmail()))
                .andExpect(jsonPath("$.data.name").value(response.getName()))
                .andExpect(jsonPath("$.data.phone").value(response.getPhone()))
                .andExpect(jsonPath("$.data.memberStatus").value(response.getMemberStatus()));


    }

    @Test
    void getMembersTest() throws Exception {
        // TODO MemberController의 getMembers() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
//        List<Member> members = new ArrayList<>();
//        new Member("hgd1@gmail.com", "홍길동1", "010-1111-1234");
//        new Member("hgd2@gmail.com", "홍길동2", "010-2222-1234");
//
//
//        List<MemberDto.response> responses = new ArrayList<>(List.of(
//        new MemberDto.response(1, "hgd1@gmail.com", "홍길동1", "010-1111-1234", MEMBER_ACTIVE, new Stamp()),
//        new MemberDto.response(2, "hgd2@gmail.com", "홍길동2", "010-2222-1234", MEMBER_ACTIVE, new Stamp())));
//
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//        queryParams.add("page", "1");
//        queryParams.add("size", "10");
//        String page = "1";
//        String size = "10";
//
//        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt()))
//                .willReturn((Page<Member>) queryParams);
//
//        given(mapper.membersToMemberResponses(Mockito.any(List.class)))
//                .willReturn(responses);
//
//        ResultActions actions = mockMvc.perform(get("/v11/members")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON));
//
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andReturn();
//
  }

    @Test
    void deleteMemberTest() throws Exception {
        // TODO MemberController의 deleteMember() 핸들러 메서드를 테스트하는 테스트 케이스를 여기에 작성하세요.
        // TODO Mockito를 사용해야 합니다. ^^
    }
}
