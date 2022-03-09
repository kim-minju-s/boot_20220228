package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

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
	
	// repository로 조회
	// 127.0.0.1:8080/board/selectfind
	@GetMapping(value = "/selectfind")
	public String selectfindGET(Model model,
			@RequestParam(name = "no", 
				required = false) List<Long> nos,
			@RequestParam(name = "type2",
				defaultValue = "0", required = false) int type2,
			@RequestParam(name = "type1",
				defaultValue = "0", required = false) int type1,
			@RequestParam(name = "text1", 
				defaultValue = "", required = false) Long text1,
			@RequestParam(name = "text", 
				defaultValue = "", required = false) String text,
			@RequestParam(name = "type", 
				defaultValue = "", required = false) String type) {

		List<Board> list = null;
		
		// 검색어 정확히 입력
		if(type.equals("title")) {
//			list = bRepository.findByTitle( text );
			list = bRepository.getBoardTitle( text );
		}
		else if(type.equals("writer")) {
//			list = bRepository.findByWriter( text );
			list = bRepository.getBoardWriter( text );
		}
		else if(type.equals("hit")) {
			long hit = 0L;
			try {
				// 문자로 되어있는 숫자를 숫자형으로 변경
				// "1234" => 1234
				// ""	=> X
				hit = Long.parseLong(text);
			}
			catch(Exception e) {
				hit = 0L;
			}
			list = bRepository.getBoardHit( hit );
		}
		
		// 디폴트
		if(text.length() == 0) {
			list = bRepository.findAll();			
		}

		// 이상, 미만
		if(type1 == 1) {
			long hit = 0;
			try {
				hit = text1;
			}
			catch(Exception e) {
				hit = 0;
			}
			list = bRepository.findByHitGreaterThanEqual(hit);
		}
		else if(type1 == 2) {
			long hit = 0;
			try {
				hit = text1;
			}
			catch(Exception e) {
				hit = 0;
			}
			list = bRepository.findByHitLessThan(hit);
		}

		// 포함, 미포함
		if(type2 == 1) {	// 포함
			list = bRepository.findByNoIn(nos);
		}
		if(type2 == 2) {	// 미포함
			list = bRepository.findByNoNotIn(nos);
		}
		
		model.addAttribute("list", list);
		
		return "board/selectfind";
	}

	// 수정 화면
	@GetMapping(value = "/updatebatch")
	public String updateGET(Model model,
			HttpServletRequest request,
			@RequestParam(name = "no") long no ) {

		System.out.println("no-->" + no);

		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map != null) {
			long no1 = (long) map.get("no1");
			System.out.println("no1 --->" + no1);
		}

		//long no = (long)httpSession.getAttribute("rad");
		// System.out.println("세션에서 꺼낸 no" + no);

		// null에 대한 처리
		Board board = bRepository.findById(no).orElse(null);
		// if(optional.isPresent()) {
		// 	Board board = optional.get();
		model.addAttribute("board", board);
		// }

		return "board/updatebatch";
	}

	// 수정하기
	// 추가 => 기본키를 다르게 해서 저장
	// 수정 => 기본키에 해당하는 글번호를 동일하게 해서 새로저장
	@PostMapping(value = "/updatebatch")
	public String updatePOST(Model model,
			@ModelAttribute Board board ) {

		try {
			// 기존의 내용을 읽음
			Board board1 = bRepository.findById(board.getNo()).orElse(null);
	
			// 변경할 항목만 board1에 다시 저장
			board1.setTitle(board.getTitle());
			board1.setContent(board.getContent());
			board1.setWriter(board.getWriter());
			
			// 최종적으로 board1의 내용을 저장
			bRepository.save(board1);
	
			model.addAttribute("msg", "수정 완료");
			model.addAttribute("url", "/board/selectlist");
			return "alert";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}
	
	// selectlist 에서 삭제&수정 버튼 클릭시
	@PostMapping(value = "/action")
	public String actionPOST(Model model,
			RedirectAttributes redirectAttributes,	// post에서 get으로 데이터를 전송
			@RequestParam(name = "btn") String btn,
			@RequestParam(name = "rad") long no ) {
		
		try {
			System.out.println(btn);
			System.out.println(no);
			
			// int, long, char => 비교 가능
			// (btn == "삭제")X -> 문자열은 == 사용불가
			if(btn.equals("삭제")) {
				bRepository.deleteById(no);

				model.addAttribute("msg", "삭제 완료");
				model.addAttribute("url", "/board/selectlist");
				return "alert";	
			}
			else if(btn.equals("수정")) {
				// httpSession.setAttribute("rad", no);	// 세션에 id전송
				
				// 세션에 추가하는 방식
				// get 방식 -> url에 parameter로 붙임
				redirectAttributes.addAttribute("no", no);
				
				// post 방식(1회성, 새로고침시 데이터 소멸) -> 중요한 정보
				redirectAttributes.addFlashAttribute("no1", no);
				
				return "redirect:/board/updatebatch";
			}

			return "redirect:/board/selectlist";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
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
