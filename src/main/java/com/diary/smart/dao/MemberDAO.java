package com.diary.smart.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.smart.vo.Member;

@Repository
public class MemberDAO {

	@Autowired
	SqlSession sqlSession;
	private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	/**
	 * @param member
	 *            회원가입할 회원의 정보
	 * @return DB에 insert된 회원의 명수
	 */
	public int joinMember(Member member) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		int result = 0;

		try {
			result = mapper.joinMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param member
	 *            정보수정할 회원의 정보
	 * @return 수정된 회원의 명수
	 */
	public void updateMember(Member member) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

		try {
			mapper.updateMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param idno
	 *            탈퇴할 회원의 회원번호
	 * @return 탈퇴된 회원의 명수 (실제 삭제가아닌 업데이트로 탈퇴플래그를 바꾸기 때문에 업데이트 된 개수)
	 */
	public int deleteMember(int idno) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		int result = 0;
		try {
			result = mapper.deleteMember(idno);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @param id
	 *            조회할 회원의 아이디
	 * @return 조회된 회원정보
	 */
	public Member selectMember(String id) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		Member result = new Member();
		try {
			result = mapper.selectMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param user_no_pk
	 *            접속한 회원 번호
	 * @return 친구 목록
	 */
	public ArrayList<HashMap<String, Object>> getFriendList(int user_no_pk) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		ArrayList<HashMap<String, Object>> result = null;

		try {
			result = mapper.getFriendList(user_no_pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param user_no_pk
	 *            접속한 회원 번호
	 * @return 친구 요청 목록
	 */
	public ArrayList<HashMap<String, Object>> getFriendRequestList(int user_no_pk) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		ArrayList<HashMap<String, Object>> result = null;

		try {
			result = mapper.getFriendRequestList(user_no_pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param user_no_pk
	 *            접속한 회원 번호
	 * @param user_nm
	 *            검색할 글자
	 * @return 친구 요청 목록
	 */
	public ArrayList<HashMap<String, Object>> selectMemberList(int user_no_pk, String user_nm) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		ArrayList<HashMap<String, Object>> result = null;

		try {
			result = mapper.selectMemberList(user_no_pk, user_nm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param user_no_fk
	 *            접속한 회원 번호
	 * @param user_frno
	 *            요청한 친구의 회원 번호
	 */
	public void addFriend(int user_no_fk, int user_frno) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

		try {
			mapper.addFriend(user_no_fk, user_frno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param user_no_fk
	 *            접속한 회원 번호
	 * @param user_frno
	 *            친구 승인한 친구의 회원 번호
	 */
	public void acceptFriend(int user_no_fk, int user_frno) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

		try {
			mapper.addFriend(user_no_fk, user_frno);
			mapper.acceptFriend(user_no_fk, user_frno);
			mapper.acceptFriend(user_frno, user_no_fk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param user_no_fk
	 *            접속한 회원 번호
	 * @param user_frno
	 *            친구 거절한 회원 번호
	 */
	public void rejectFriend(int user_no_fk, int user_frno) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

		try {
			mapper.rejectFriend(user_no_fk, user_frno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param user_no_fk
	 *            접속한 회원 번호
	 * @param user_frno
	 *            삭제 요청된 친구의 회원 번호
	 */
	public void deleteFriend(int user_no_fk, int user_frno) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

		try {
			mapper.deleteFriend(user_no_fk, user_frno);
			mapper.deleteFriend(user_frno, user_no_fk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 *            입력한 아이디
	 * @return 로그인 되었을 때 그 사람의 member 객체
	 */
	public Member login(String id) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		Member member = null;
		try {
			member = mapper.selectMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public int authenticated(String id) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		int result = 0;

		try {
			result = mapper.authenticated(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
