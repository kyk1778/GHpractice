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
	// �ʵ�
	private Chef chef;
	// ������ : �ʵ带 �Ű������� ������ ������
	// ������ 4.3���Ŀ��� ���� �Ķ���͸� �̿��ϴ� �����ڿ� ���ؼ�
	// Ư�� Ÿ���� ��ü�� �ڵ����� ������ ���ش�.
	// ������ ���ؼ� @Autowired ������̼��� ���� �ʾƵ� �ȴ�.
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}
}

/*
 * ��ü�� ���Թ޴� ��� : 2����
 * 1. Setter�� �̿��ؼ� ���� �޴´�.
 * 2. �����ڸ� �̿��ؼ� ���� �޴´�.
 */