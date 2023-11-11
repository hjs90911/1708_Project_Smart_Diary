package com.diary.smart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.diary.smart.vo.Diary;

public interface DiaryMapper {

	/**
	 * @param diary
	 *            입력할 일정 정보
	 * @return 입력된 일정의 개수
	 */
	public int insertDiary(Diary diary); // 일정 입력

	/**
	 * @param diary
	 *            입력할 일정 정보
	 * @return 입력된 일정의 개수
	 */

	public int insertCompanions(int sc_no_fk, int user_no_fk, int sc_frno);

	/**
	 * @param diary의
	 *            sc_no
	 * @return 같이 가는 친구 이름 목록
	 */
	public ArrayList<String> selectCompanions(int sc_no_fk);

	/**
	 * @param scno
	 *            삭제할 일정번호
	 * @return 삭제된 일정의 개수 (실제 삭제가아닌 업데이트로 삭제플래그를 바꾸기 때문에 업데이트 된 개수)
	 */
	public int deleteDiary(int scno); // 일정 삭제
										// 실제 삭제가아니라 delete flag값 update해준다.

	/**
	 * @param diary
	 *            수정된 일정정보
	 * @return 수정된 데이터의 개수
	 */
	public Diary updateDiary(Diary diary); // 일정 수정

	public Diary selectSchedule(int scno);

	/**
	 * 최근 추가한 일정번호 출력(중간예매단계에서 일정 db에 저장. 결제가완료되면 결제플래그 y로변경)
	 * 
	 * @return 최근 추가한 일정번호
	 */
	public int lastSchedule();

	/**
	 * 결제후 결제플래그 Y로 변경하는 메서드
	 * 
	 * @param scno
	 *            일정번호
	 * @return 완료되면 1리턴.
	 */
	public int paymentFin(int scno);

	public List<Diary> todaySchedule(int user_no, String sc_stdt);

	/**
	 * @param idno
	 *            회원아이디
	 * @return 특정회원에 대한 일정 출력
	 */
	public List<HashMap<String, Object>> selectDiaryList(int user_no); // 특정회원에
																		// 대한 일정
																		// 가져오기.

}
