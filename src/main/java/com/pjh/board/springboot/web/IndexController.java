package com.pjh.board.springboot.web;

import com.pjh.board.springboot.config.auth.LoginUser;
import com.pjh.board.springboot.config.auth.dto.SessionUser;
import com.pjh.board.springboot.service.PostsService;
import com.pjh.board.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/board")
    public String board(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "board";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user){
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "posts-save";
    }

    @GetMapping("/posts/read/{id}")
    public String postsUpdate(@PathVariable Long id,@LoginUser SessionUser user,Model model){
        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post",dto);
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        return "posts-read";
    }


}
