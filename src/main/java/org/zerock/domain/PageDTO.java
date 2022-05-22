package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {	// 페이징의 화면처리를 위한 클래스
	private int startPage;	// 보여줄 페이지의 시작 페이지
	private int endPage;	// 마지막 페이지
	private boolean prev, next;	// 이전 버튼이 보여주어야 하는지?
	private int total;		// 전체 게시글의 수
	private Criteria cri;	// 페이징 조건(현재 페이지 번호, 페이지당 갯수)
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		if (realEnd <= this.endPage) {
	      		this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
