package com.pjh.board.springboot.web;

import com.pjh.board.springboot.config.auth.LoginUser;
import com.pjh.board.springboot.config.auth.dto.SessionUser;
import com.pjh.board.springboot.domain.reply.ReplyRepository;
import com.pjh.board.springboot.service.PostsService;
import com.pjh.board.springboot.service.ReplyService;
import com.pjh.board.springboot.web.dto.PostsListResponseDto;
import com.pjh.board.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final ReplyService replyService;
    private static final Integer groupPage=10;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        if(user!=null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userThumb",user.getPicture());
        }
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping("/project")
    public String project(Model model){
        return "project";
    }



    @GetMapping("/board/{page}")
    public String board(@PathVariable Integer page,Model model,@LoginUser SessionUser user){
        List<PostsListResponseDto> postsListResponseDtos=postsService.findAllDesc(page-1);
        model.addAttribute("posts",postsListResponseDtos);
        model.addAttribute("lastPage",postsService.Count()/groupPage+1);
        model.addAttribute("curPage",page);
        if(user!=null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userThumb",user.getPicture());
        }
        return "board";
    }
    @GetMapping("/board")
    public String board(Model model,@LoginUser SessionUser user){
        List<PostsListResponseDto> postsListResponseDtos=postsService.findAllDesc(0);
        model.addAttribute("posts",postsListResponseDtos);
        model.addAttribute("lastPage",postsService.Count()/groupPage+1);
        model.addAttribute("curPage",1);
        if(user!=null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userThumb",user.getPicture());
        }
        return "board";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user){
        if(user!=null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userThumb",user.getPicture());
        }
        return "posts-save";
    }

    @GetMapping("/posts/read/{id}")
    public String postsUpdate(@PathVariable Long id,@LoginUser SessionUser user,Model model){

        model.addAttribute("post",postsService.findById(id));
        model.addAttribute("reply",replyService.findAllDescById(id));
        if(user!=null){
            model.addAttribute("userName",user.getName());
            model.addAttribute("userThumb",user.getPicture());
        }
        return "posts-read";
    }


}
