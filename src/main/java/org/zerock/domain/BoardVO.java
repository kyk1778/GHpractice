package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data	// lombok Getter/Setter/생성자/toString
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
}
