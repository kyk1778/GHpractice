package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration	// Controller(브라우저에서 올라오는 것처럼 동작을 하도록)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {
	@Setter(onMethod_=@Autowired)
	private WebApplicationContext ctx;	// ctx 객체를 주입을 받는다.
	private MockMvc mockMvc;
	
	@Before	// 초기화를 하는 절차 : Test 전에 실행된다.
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
	public void testList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()		// 
				.getModelAndView()	// 
				.getModelMap());	// 결과(List<BoardVO>를 빼낸다.
	}
	
//	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 새글 목록")
				.param("content", "테스트 새글 내용")
				.param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);	// redirect:/board/list
	}
	
//	@Test
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.
				get("/board/get")
				.param("bno", "2"))
				.andReturn().getModelAndView().getModelMap());	// BoardVO를 출력
	}
	
//	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(
				MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "37")	// 데이터베이스에 있는 게시글로
				.param("title", "테스트 새글 목록 수정")
				.param("content", "테스트 새글 내용 수정")
				.param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);	// redirect:/board/list
	}
	
//	@Test
	public void testRemove() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.
			post("/board/remove")
			.param("bno", "2"))
			.andReturn().getModelAndView().getViewName());	// redirect:/board/list
	}
	
	@Test
	public void testListPaging() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")	// 페이징 정보를 파라미터로 추가
				.param("amount", "10"))
				.andReturn()		// 
				.getModelAndView()	// 
				.getModelMap());	// 결과(List<BoardVO>를 빼낸다.
	}
}
