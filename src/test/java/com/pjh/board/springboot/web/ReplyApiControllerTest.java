package com.pjh.board.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjh.board.springboot.domain.posts.PostsRepository;
import com.pjh.board.springboot.domain.reply.Reply;
import com.pjh.board.springboot.domain.reply.ReplyRepository;
import com.pjh.board.springboot.web.dto.PostsSaveRequestDto;
import com.pjh.board.springboot.web.dto.ReplySaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReplyApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    @Before
    public void setup(){
        mvc= MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        postRepository.deleteAll();
        replyRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void PostsAndReply_등록된다() throws Exception{
        String title="title";
        String content="content";

        String replyAuthor="author";
        String replyContent="replyContent";

        PostsSaveRequestDto requestDto=PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url="http://localhost:"+port+"/api/v1/posts";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper()
                        .writeValueAsString(requestDto))).andExpect(status().isOk());

        //댓글을 1번 posts에 저장한다
        ReplySaveRequestDto saveRequestDto=ReplySaveRequestDto.builder()
                .author(replyAuthor)
                .content(replyContent)
                .id(1L)
                .build();

        url="http://localhost:"+port+"/api/v1/reply";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper()
                        .writeValueAsString(saveRequestDto))).andExpect(status().isOk());

        List<Reply> all=replyRepository.findAllDescById(1L);
        assertThat(all.get(0).getAuthor()).isEqualTo(replyAuthor);
        assertThat(all.get(0).getContent()).isEqualTo(replyContent);
    }
}
