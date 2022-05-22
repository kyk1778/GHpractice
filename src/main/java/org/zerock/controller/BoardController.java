package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor			// 생성자로 Service 객체를 주입받기 위해
public class BoardController {
	private BoardService service;	// 스프링으로부터 주입을 받는다.
	
	// 게시글 목록 보기
//	@GetMapping("/list")	// url : /board + /list = /board/list
//	public void list(Model model) {
//		log.info("list...");
//		model.addAttribute("list", service.getList());
//	}	// view page : /board/list.jsp (url 이름과 동일)
	
//	@GetMapping("/list")	// url : /board + /list = /board/list
//	public void list(Criteria cri, Model model) {	// 브라우저로부터 페이징 정보를 가져온다.
//		log.info("list: " + cri);
//		model.addAttribute("list", service.getList(cri));
//	}	// view page : /board/list.jsp (url 이름과 동일)

	@GetMapping("/list")	// url : /board + /list = /board/list
	public void list(Criteria cri, Model model) {	// 브라우저로부터 페이징 정보를 가져온다.
		log.info("list: " + cri);
		// 전체 게시글의 수를 구한다. (123) -> 데이터베이스에서 가져와야 한다.
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}	// view page : /board/list.jsp (url 이름과 동일)
	
	// 게시글 등록 폼 요청
	@GetMapping("/register")
	public void register() {	
	}	// view page : /board/register.jsp
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register..." + board);
		service.register(board);
//		return "/board/result";		// 게시글 쓰기 응답 /board/result.jsp
		// 게시글 목록 보기 페이지로 이동
		rttr.addFlashAttribute("result", board.getBno());	// 추가된 게시글 번호를 출력
		// 특성이 한번만 전달이 된다.
		return "redirect:/board/list";	// board/list.jsp(추가된 게시글 번호를 출력)
	}
	
	// get()메소드가 bno를 입력받아 게시글 정보를 보여주는 것
	// /board/get 이나 /board/modify 똑 같은 동작을 하기 때문에 메소드를 같이 이용
	// url을 수정 : /get -> { "/get", "/modify" }
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or /modify" + bno);
		BoardVO board = service.get(bno);
		model.addAttribute("board", board);
	}	// /board/get -> view page : url과 동일 (/board/get.jsp)
		// /board/modify -> view page : url과 동일 (/board/modify.jsp)
	
	// 게시글 수정
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify..." + board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		// 간단하게 메소드 처리
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list" + cri.getListLink();
	}
	
	// 게시글 수정
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove..." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list" + cri.getListLink();
	}
}
