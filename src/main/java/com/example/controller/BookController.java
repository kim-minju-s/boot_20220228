package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import com.example.service.SequenceService;

@Controller
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	SequenceService seqService;
	
	// insert 추가
	// 추가 화면
	@GetMapping(value = "/insert")
	public String insertGET() {
		
		return "/book/insert";
	}
	// 추가하기
	@PostMapping(value = "/insert")
	public String insertPOST(
			@ModelAttribute Book book) {
		
		book.setCode(seqService.generatorSequence("SEQ_BOOK4_CODE"));
		book.setRegdate(new Date());
		
		Book result = bookRepository.save(book);
		System.out.println("책 추가함" + result);
		
		return "redirect:/book/insert";
	}
	
	// selectOne 상세 조회
	// update 수정

	// delete 삭제
	@PostMapping(value = "/action")
	public String actionPOST(Model model,
			@RequestParam(name = "btn") String btn) {
			
			System.out.println(btn);

		if (btn.equals("삭제")) {
			// bookRepository.deleteById(code);

			model.addAttribute("msg", "삭제 완료");
			model.addAttribute("url", "/book/select");
			return "alert";
		}
		else if(btn.equals("수정")){

			return "redirect:/book/update";
		}
		return "redirect:/book/select";
	}
	
	// 페이지네이션 목록 조회
	// 127.0.0.1:8080/book/select?page=1&text=한글
	@GetMapping(value = "/select")
	public String selectGET(Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "text", defaultValue = "") String text ) {
		
		PageRequest pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "code");
		
		
		// 페이지네이션(0, 개수) + code를 기준으로 내림차순 정렬
		List<Book> list = bookRepository.getBookList(text, pageable);
		model.addAttribute("list", list);
		
		// 페이지네이션 번호 생성
		long pages = bookRepository.getBookCount(text);
		model.addAttribute("pages", (pages-1)/10+1);
		
		return "book/select";
	}
}
