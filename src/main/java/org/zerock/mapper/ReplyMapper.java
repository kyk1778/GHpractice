package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

// 구현객체는 MyBatis가 내부적으로 자동으로 생성을 해준다.
// 프로그래머는 신경쓸 필요가 없음
public interface ReplyMapper {
	// 댓글 추가 : 메소드 이름, 매개변수, 반환형
	public int insert(ReplyVO vo);	// 반환형: 추가된 댓글의 수
	public ReplyVO read(Long rno);	// 특정 댓글 : 매개변수로 댓글번호, 반환형 댓글
	public int delete(Long rno);	// 특정 댓글
	public int update(ReplyVO reply);
	// 특정 게시글의 댓글 목록을 가져오기
	// MyBatis에서 매개변수가 여러개일 때, @Param 어노테이션을 사용
	// Map, 객체, @Param
	public List<ReplyVO> getListWithPaging(@Param("cri")
		Criteria cri, @Param("bno") Long bno);
	public int getCountByBno(Long bno);	// 해당 게시글의 댓글의 수
}
