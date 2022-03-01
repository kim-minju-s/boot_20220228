package com.example.controller;

import java.util.List;

import com.example.entity.Member;
import com.example.service.MemberDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// 파일을 만들어서 관리하는게 편함
@RequestMapping(value = "/member")
public class MemberController {

    // BD에 일의 수행하는 클래스
    // 클래스명 obj = new 클래스명();
    @Autowired
    private MemberDB memberDB;

    // 업데이트 페이지 불러오기
    // 127.0.0.1:8080/member/update
    @GetMapping(value = {"/update"})
    public String updateGET(){
        
        // member_update로 생성
        // member 폴더에 있는 update.jsp 표시
        return "member/update";
    }

    @PutMapping(value = {"/update"})
    public String updatePUT(@ModelAttribute Member mem1){
        System.out.println( mem1.toString() );

        // 설계 부분을 사용
        memberDB.updateMember(mem1);

        return "redirect:/member/update";
    }

    // 삭제하기
    // 127.0.0.1:8080/member/delete?id=12
    // <form action="" method="get">
    // <input type="text" name="id" value="12">

    // <a href="/member/delete?id=12"></a>
    @GetMapping(value = {"/delete"})
    public String deleteGET(@RequestParam(name="id") String id){
        int ret = memberDB.deleteMember(id);

        if (ret == 1) {
            return "redirect:/member/selectlist";
        }

        return "redirect:/member/selectlist";
    }

    // 조회하기
    // 127.0.0.1:8080/member/selectlist
    @GetMapping(value = {"/selectlist"})
    public String selectlistGET(Model model){

        // 1. DB에서 목록 받아오기
        List<Member> list = memberDB.selectListMember();

        // 2. jsp로 전달하기(jsp에서의 변수명, 실제 전송값)
        model.addAttribute("list", list);

        // 3. member 폴더의 select.jsp를 표시하라
        return "member/select";
    }
    
    // 127.0.0.1:8080/member/insert
    @GetMapping(value = {"/insert"})
    public String insertGET(){
        // member_insert.jsp로 생성
        // member 폴더에 있는 insert.jsp 표시
        return "member/insert";
    }

    // post는 사용자가 입력한 내용이 전달되고 DB 작업을 위해서 필요한 시점
    // jsp를 표시하는게 아니라 주소창에 입력후 엔터키를 누름
    @PostMapping(value = {"/insert"})
    public String insertPOST(
            // 객체를 하나로 묶는 것.
            // jsp 와 entity 각 객체의 변수명을 일치시켜줘야함.
            @ModelAttribute Member mem ){

        System.out.println( mem.toString() );

        // 설계 부분을 사용
        memberDB.insertMember(mem);

        // 주소창에 /member/insert를 입력후
        // 엔터키를 누르는것과 같은 역할
        return "redirect:/member/insert";
    }

}
