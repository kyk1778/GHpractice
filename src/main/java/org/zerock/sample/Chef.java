package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component	// 스프링 콘텍스트에게 자바 빈이라는 것을 알린다.
@Data		// Lombok의 getter/setter, 생성자, toString등을 자동으로 생성
public class Chef {

}
