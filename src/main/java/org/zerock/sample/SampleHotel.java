package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Component
@ToString
@Getter
@AllArgsConstructor
public class SampleHotel {
	// 필드
	private Chef chef;
	// 생성자 : 필드를 매개변수로 가지는 생성자
	// 스프링 4.3이후에는 단일 파라미터를 이용하는 생성자에 한해서
	// 특정 타입의 객체를 자동으로 주입을 해준다.
	// 주입을 위해서 @Autowired 어노테이션을 하지 않아도 된다.
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}
}

/*
 * 객체를 주입받는 방법 : 2가지
 * 1. Setter를 이용해서 주입 받는다.
 * 2. 생성자를 이용해서 주입 받는다.
 */