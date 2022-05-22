package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data				// Default 생성자
@AllArgsConstructor	// 모든 필드를 가지는 생성자
@NoArgsConstructor	// default 생성자
public class SampleVO {
	// 필드(정보)
	private Integer mno;		// 번호
	private String firstName;	// 이름
	private String lastName;	// 성
}
