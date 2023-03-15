package hellomin.hellomin.controller;

import hellomin.hellomin.Domain.Member;
import hellomin.hellomin.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 처음으로 돌아감
     }
     @GetMapping(value = "/members")
     public String list(Model model){
         List<Member> members = memberService.findMembers();
         return "/members/memberList";
    }



}
