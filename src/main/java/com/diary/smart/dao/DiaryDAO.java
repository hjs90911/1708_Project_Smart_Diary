package com.diary.smart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.javassist.expr.Instanceof;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.smart.vo.Diary;

@Repository
public class DiaryDAO {

	@Autowired
	SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(DiaryDAO.class);
	
	/**
	 * 최근 추가한 일정번호 출력(중간예매단계에서 일정 db에 저장. 결제가완료되면 결제플래그 y로변경)
	 * @return 최근 추가한 일정번호
	 */
	public int lastSchedule(){
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		int result = 0;

		try {
			result = mapper.lastSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 결제후 결제플래그 Y로 변경하는 메서드
	 * @param scno 일정번호
	 * @return 완료되면 1리턴.
	 */
	public int paymentFin(int scno){
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		int result = 0;

		try {
			result = mapper.paymentFin(scno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param diary
	 *            입력할 일정 정보
	 * @return 입력된 일정의 번호
	 */
	public int insertDiary(Diary diary) {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		int result = 0;

		try {
			result = mapper.insertDiary(diary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertCompanions(int sc_no_fk, int user_no_fk, int sc_frno) {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		int result = 0;

		try {
			result = mapper.insertCompanions(sc_no_fk, user_no_fk, sc_frno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param scno
	 *            삭제할 일정번호
	 * @return 삭제된 일정의 개수 (실제 삭제가아닌 업데이트로 삭제플래그를 바꾸기 때문에 업데이트 된 개수)
	 */
	public int deleteDiary(int scno) {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		int result = 0;

		try {
			result = mapper.deleteDiary(scno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param diary
	 *            수정된 일정정보
	 * @return 수정된 데이터의 개수
	 */
	public Diary updateDiary(Diary diary) {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		Diary result = null;

		try {
			result = mapper.updateDiary(diary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Diary selectSchedule(int scno) {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		Diary result = null;

		try {
			result = mapper.selectSchedule(scno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param idno
	 *            회원아이디
	 * @return 특정회원에 대한 일정 출력
	 */
	public List<HashMap<String, Object>> selectDiaryList(int user_no) {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		List<HashMap<String, Object>> result = null;
		ArrayList<String> compList = new ArrayList<>();

		try {
			result = mapper.selectDiaryList(user_no);
			for (HashMap<String, Object> schedule : result) {
				String test = String.valueOf(schedule.get("SC_NO_PK"));
				int sc_no_pk = Integer.parseInt(test);
				
				if (schedule.get("SC_FIN").equals("Y")) {
					compList = mapper.selectCompanions(sc_no_pk);
					schedule.put("SC_COMPLIST", compList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Diary> todaySchedule(int user_no, String sc_stdt) {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		List<Diary> result = null;

		try {
			result = mapper.todaySchedule(user_no, sc_stdt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
