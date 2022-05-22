package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice	// Controller에서 예외가 발생되는 실행되는 클래스를 정의한 Advice
					// AOP의 공통처리부분(예외)
@Log4j
public class CommonExceptionAdvice {
	@ExceptionHandler(Exception.class)	// Exception 예외가 발생되면 처리
	public String except(Exception ex, Model model) {
		log.error("Exception..." + ex.getMessage());
		model.addAttribute("exception", ex);	// view page에 예외 정보를 전달
		log.error(model);
		return "error_page";	// 에러를 응답하는 view page 이름
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)	// 응답 code=404 Not Found
	public String handle404(NoHandlerFoundException ex) {
		return "custom404";	// 404 Not Found가 발생되면 custome404.jsp로 응답
	}
}
