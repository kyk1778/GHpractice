package org.zerock.service;

import org.springframework.stereotype.Service;

@Service	// 스프링으로부터 주입을 받도록 설정
public class SampleServiceImpl implements SampleService {

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		// TODO Auto-generated method stub
		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

}
