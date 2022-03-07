package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.entity.Book;
import com.example.service.BookDB;
import com.example.service.SequenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    BookDB bookDB;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping(value = "/updatebatch")
    public String updateGET(Model model) {
    	// 형변환을 하면 데이터가 안전하지 않음을 경고
    	// 세션에 추가할 때와 가지고 올 때의 타입을 정확하게 매칭
    	@SuppressWarnings({"unchecked"})
        // 페이지를 이동 후에 세션에서 꺼내기
        List<Long> code = (List<Long>) httpSession.getAttribute("CHK");
    	
    	List<Book> list = bookDB.selectListWhereIn(code);
    	model.addAttribute("list", list);

        // DB에서 code에 해당하는 항목 정보만 가져옴
        // jsp로 전달
        // jsp를 표시함

        return "/admin/updatebatch";
    }
    
    // 
    @PostMapping(value = "/updatebatch")
    public String updatePost(Model model,
    		@RequestParam(name = "code") long[] code,
    		@RequestParam(name = "title") String[] title,
    		@RequestParam(name = "price") long[] price,
    		@RequestParam(name = "writer") String[] writer,
    		@RequestParam(name = "category") String[] category ) {
    	
    	List<Book> list = new ArrayList<Book>();
    	for(int i=0;i<code.length;i++) {
    		Book book = new Book();
    		book.setCode(code[i]);
    		book.setTitle(title[i]);
    		book.setPrice(price[i]);
    		book.setWriter(writer[i]);
    		book.setCategory(category[i]);
    		
    		list.add(book);
    	}
    	
    	long ret = bookDB.updateBatchBook(list);
    	if(ret == 1) {
    		model.addAttribute("msg", "일괄수정 되었습니다.");
    		model.addAttribute("url", "/admin/selectlist");
    		return "alert";    		
    	}
    	// jsp를 만들어서 알림을 띄우고 redirect 실행
    	model.addAttribute("msg", "일괄수정 실패했습니다.");
		model.addAttribute("url", "/admin/selectlist");
    	return "alert";
    }
    

    // 일괄삭제 & 일괄수정
    @PostMapping(value = "/action")
    public String actionPOST(Model model,
            @RequestParam(name = "btn") String btn,
            @RequestParam(name = "chk") List<Long> code){
               
    		// Java wrapper 클래스
    		// long: 변수 -> Long: 오브젝트 => 클래스화
    		// byte(기본타입)/Byte(래퍼 클래스)
    	
    		// List<object> 안에는 오브젝트만 가능
    		// long[] == List<Long>
    	
            // System.out.println("버튼 ---> " + btn);
    	for(Long tmp : code) {
    		System.out.println("tmp--->" + tmp);
    	}

        if (btn.equals("일괄삭제")) {
        	// DB 삭제 구현 
        	bookDB.deleteBatchBook(code);
        	
// 			System.out.println("체크버튼1 ---> " + Arrays.toString(code));
//        	for(int i=0;i<code.size();i++) {
//        		System.out.println(code);
//        	}
            
            // 회원 목록, 물품목록 검색기능 추가하기 
            return "redirect:/admin/selectlist";
        }
        else if (btn.equals("일괄수정")) {
            // long[] 의 code를 세션에 넣음
            httpSession.setAttribute("CHK", code);

            return "redirect:/admin/updatebatch";
        }

        // 목록으로 이동
        return "redirect:/admin/selectlist";
    }
    
    // 도서추가 화면
    @GetMapping(value = "/insertbatch")
    public String insertGET(){

        return "admin/insertbatch";
    }

    // 도서 일괄 추가
    @PostMapping(value = "/insertbatch")
    public String insertPOST(
            @RequestParam(name = "title") String[] title,
            @RequestParam(name = "price") long[] price,
            @RequestParam(name = "writer") String[] writer,
            @RequestParam(name = "category") String[] category) {

        // 1. 빈 리스트 만들기
        List<Book> list = new ArrayList<Book>();
        for(int i=0;i<title.length;i++){
            System.out.println(
                title[i] + "," + price[i] + "," 
                + writer[i] + "," + category[i] );

            // 2. book 객체 만들기
            Book book = new Book();
            book.setCode(sequenceService.generatorSequence("SEQ_BOOK4_CODE"));
            book.setTitle(title[i]);
            book.setPrice(price[i]);
            book.setWriter(writer[i]);
            book.setCategory(category[i]);
            book.setRegdate(new Date());

            // 3. 리스트에 추가하기
            list.add(book);
        }

        // 배열 4개로 => List<Book>
        // 시퀀스를 코드를 채워, 날짜도 채워
        bookDB.insertBatchBook(list);

        return "redirect:/admin/insertbatch";
    }

    // 도서 목록
    @GetMapping(value = "/selectlist")
    public String selectlistGET(Model model,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "text", defaultValue = "") String text){
        
        List<Book> list = bookDB.selectListPageSearchBook(page, text);  // 페이지
        
        System.out.println(list.size());

        long pages = bookDB.countSearchBook(text);

        // jsp로 전달 (변수, 값) => 변수를 사용
        model.addAttribute("list", list);
        model.addAttribute("pages", (pages-1)/10+1);

        return "/admin/selectlist";
    }

}
