package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {			// 댓글 정보
	private Long rno;			// 댓글 번호
	private Long bno;			// 게시글 번호
	private String reply;		// 댓글 내용
	private String replyer;		// 댓글 작성자
	private Date replyDate;
	private Date updateDate;
}
