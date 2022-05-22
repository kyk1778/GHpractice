package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {		// 댓글에 대한 페이지 처리를 위한 객체
	private int replyCnt;		// 댓글의 수
	private List<ReplyVO> list;	// 댓글 목록
}	// 페이징 처리를 위한 부분을 javascript로 위임(이전에는 controller가 했음)
