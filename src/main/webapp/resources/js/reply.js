/**
 * 서버와 통신을 하는 객체를 정의 : replyService
 */

// 객체 함수
var replyService = (function() {	// 자동실행함수를 정의
  // 메소드를 정의
  // 매개변수 : reply, callback, error
  // reply : 댓글을 추가하기 위한 댓글 정보 객체(bno, reply, replyer)
  // callback : get.jsp에서 응답을 받기 위해서 넣어준 함수
  // reply.js에서 서버와 통신을 해서 받은 응답을 callback 함수를 통해서 get.jsp에 전달
  // error : 서버와 통신해서 에러가 발생하면 처리하는 callback 함수를 받는다.
  function add(reply, callback, error) {
    console.log("add reply...");
    // 서버로 댓글 추가 : Ajax
    $.ajax({
      type: 'post',	// method
      url: '/replies/new',
      data: JSON.stringify(reply),	// reply 객체를 JSON 객체 문자열 형태로 변환
      contentType: "application/json; charset=UTF-8",
      success: function(result, status, xhr) {	// 서버와 통신을 성공
      	if(callback) {		// get.jsp에서 callback함수를 제공을 하면
      	  callback(result);	// callback함수를 호출하면서 결과(result)를 준다.
      	}
      },
      error: function(xhr, status, er) {	// 서버와 통신 시 에러 발생되면 호출됨
        if(error) {		// 에러가 발생되면
          error(er);	// callback함수 error를 호출하면서 에러원인을 제공
        }
      }
    });
  }
  
  // 댓글 목록을 가져오는 메소드
  function getList(param, callback, error) {
	var bno = param.bno;
	var page = param.page || 1;
	// 서버로 댓글목록을 요청
	$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
		function(data) {	// data : 댓글목록(List<ReplyVO>)
			// data : PageReplyDTO (replyCnt, List<ReplyVO>)
			if(callback) {
				callback(data.replyCnt, data.list);	// 댓글 목록만 가져오는 경우
			}
	}).fail(function(xhr, status, err) {
		if(error) {
			error();
		}
	});
  }
  
  // 댓글의 작성시간을 출력 : 하루 미만인 경우와 하루 이상으로 나누어 출력 (facebook)
  function displayTime(timeValue) {
	var today = new Date();
	var gap = today.getTime() - timeValue;
	var dateObj = new Date(timeValue);
	var str = "";

	// 댓글을 작성한 시간이 하루 미만이면 시간을 출력하고
	if (gap < (1000 * 60 * 60 * 24)) {
		var hh = dateObj.getHours();
		var mi = dateObj.getMinutes();
		var ss = dateObj.getSeconds();
		return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
				':', (ss > 9 ? '' : '0') + ss ].join('');
	} else {	// 하루 이상이면 날짜를 출력한다.
		var yy = dateObj.getFullYear();
		var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
		var dd = dateObj.getDate();
		return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
				(dd > 9 ? '' : '0') + dd ].join('');
	}
  }
  
  // 댓글을 가져온다.
  function get(rno, callback, error) {
	$.get("/replies/" + rno + ".json", function(result) {
		if(callback) {
			callback(result);
		}
	}).fail(function(xhr, status, err) {
		if(error) {
			error();
		}
	});
  }
  
  // 댓글 수정
  function update(reply, callback, error) {
	console.log("RNO: " + reply.rno);
		
	$.ajax({
		type: 'put',
		url: '/replies/' + reply.rno,
		data: JSON.stringify(reply),
		contentType: "application/json; charset=utf-8",
		success: function(result, status, xhr) {
			if(callback) {
				callback(result);
			}
		},
		error: function(xhr, status, er) {
			if(error) {
				error(er);
			}
		}
	});
  }
  
  // 댓글 삭제
  function remove(rno, callback, error) {
	$.ajax({
		type: 'delete',
		url: '/replies/' + rno,
		success: function(deleteResult, status, xhr) {
			if(callback) {
				callback(deleteResult);
			}
		},
		error: function(xhr, status, er) {
			if(error) {
				error(er);
			}
		}
	});
  }
  
  return {
    add: add,	// add method를 추가
    	// 첫 번째 add : get.jsp에서 호출하는 함수 이름
    	// 두 번째 add : reply.js에서 동작하는 메소드 이름
    getList: getList,	// 댓글 목록을 가져오는 메소드
    displayTime: displayTime,
    get: get,
    update: update,
    remove: remove
  };
})();