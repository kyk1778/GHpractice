package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* 페이징 처리를 위해 필요한 데이터 저장하는 클래스
 * pageNum : 현재 보고자하는 페이지 번호
 * amount : 페이지당 보여줄 게시글의 수 : default 10, 5, 15, 20 변경 가능
 */
@Getter
@Setter
@ToString
public class Criteria {	// 브라우저서도 쓰이고, 서버에서도 사용이 된다.
	private int pageNum;	// 현재 페이지 번호
	private int amount;		// 페이지당 게시글 수
	private String type;	// 검색 유형
	private String keyword;	// 검색어
	
	// default 생성자 : 파라미터가 없을 경우 default로 1페이지와 10개를 설정
	public Criteria() {
		this(1, 10);
	}
	
	// 사용자가 보여줄 페이지와 갯수를 지정
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// type : T, C, W, TC, TW, CW, TCW (7가지 검색 유형)
	// type 문자열을 배열로 변환
	public String[] getTypeArr() {
		return type == null? new String[] { } : type.split("");
	}
	
	// Criteria를 url의 파라미터로 추가하도록 메소드를 만든것
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.pageNum)
			.queryParam("amount", this.amount)
			.queryParam("type", this.getType())
			.queryParam("keyword", this.getKeyword());
		return builder.toUriString();
	}

}
