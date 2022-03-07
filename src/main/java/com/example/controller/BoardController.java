package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Board;
import com.example.repository.BoardRepository;
import com.example.service.SequenceService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	// 서비스 => mybatis => 설계 + 구현(SQL문)
	// 저장소 => JPA, hibernate => 설계 + 구현(SQL)
	@Autowired
	BoardRepository bRepository;
	
	@Autowired
	SequenceService sequenceService;
	
	@Autowired
    private HttpSession httpSession;
	
	@GetMapping(value = "/updatebatch")
	public String updateGET(Model model) {
		long no = (long)httpSession.getAttribute("rad");
		System.out.println("세션에서 꺼낸 no" + no);
		
		Optional<Board> optional = bRepository.findById(no);
		if(optional.isPresent()) {
			Board board = optional.get();
			model.addAttribute("board", board);
		}
		
		return "board/updatebatch";
	}
	
	
	@PostMapping(value = "/action")
	public String actionPOST(Model model,
			@RequestParam(name = "btn") String btn,
			@RequestParam(name = "rad") long no ) {
		
		System.out.println(btn);
		System.out.println(no);
		
		if(btn.equals("삭제")) {
			bRepository.deleteById(no);
			
			model.addAttribute("msg", "삭제 완료");
			model.addAttribute("url", "/board/selectlist");
			return "alert";	
		}
		else if(btn.equals("수정")) {
			httpSession.setAttribute("rad", no);
			
			return "redirect:/board/updatebatch";
		}
		
		return "redirect:/board/selectlist";
	}
	
	// 목록
	@GetMapping(value = "/selectlist")
	public String selectGET(Model model) {
		List<Board> list = bRepository.findAll();
		model.addAttribute("list", list);
		
		return "board/selectlist";
	}
	
	// 추가 화면
	@GetMapping(value = "/insert")
	public String insertGET() {
		
		return "board/insert";
	}
	
	// 추가하기
	@PostMapping(value = "/insert")
	public String insertPOST(Model model, @ModelAttribute Board board) {
		System.out.println( board.toString() );
		// "SEQ_BOARD4_NO"
		board.setNo(sequenceService.generatorSequence("SEQ_BOARD4_NO"));
		board.setRegdate(new Date());
		
		Board retBoard = bRepository.save(board);
		
		if(retBoard != null) {
			model.addAttribute("msg", "글쓰기 완료");
			model.addAttribute("url", "/board/selectlist");
			return "alert";			
		}
		
		return "redirect:/board/insert";
	}
	
}
