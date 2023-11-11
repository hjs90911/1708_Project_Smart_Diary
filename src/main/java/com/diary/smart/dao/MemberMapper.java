package com.diary.smart.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.diary.smart.vo.Member;

public interface MemberMapper {

	/**
	 * @param member
	 *            회원가입할 회원의 정보
	 * @return int DB에 insert된 회원의 명수
	 */
	public int joinMember(Member member); // 회원가입

	/**
	 * @param member
	 *            정보수정할 회원의 정보
	 * @return int 수정된 회원의 명수
	 */
	public void updateMember(Member member); // 회원정보수정

	/**
	 * @param idno
	 *            탈퇴할 회원의 회원번호
	 * @return int 탈퇴된 회원의 명수 (실제 삭제가아닌 업데이트로 탈퇴플래그를 바꾸기 때문에 업데이트 된 개수)
	 */
	public int deleteMember(int idno); // 실제 삭제가아닌 delete flag만 update해준다.

	/**
	 * @param user_no_fk
	 *            접속한 회원의 회원번호
	 * @param user_frno
	 *            추가할 친구의 회원번호
	 */
	public void addFriend(int user_no_fk, int user_frno); // 친구 등록

	/**
	 * @param user_no_fk
	 *            접속한 회원의 회원번호
	 * @param user_frno
	 *            승인할 친구의 회원번호
	 */
	public void acceptFriend(int user_no_fk, int user_frno); // 친구 등록 승인

	/**
	 * @param user_no_fk
	 *            접속한 회원의 회원번호
	 * @param user_frno
	 *            거절할 친구의 회원번호
	 */
	public void rejectFriend(int user_no_fk, int user_frno); // 친구 등록 거절

	/**
	 * @param user_no_fk
	 *            접속한 회원의 회원번호
	 * @param user_frno
	 *            삭제할 친구의 회원번호
	 */
	public void deleteFriend(int user_no_fk, int user_frno);

	/**
	 * @param id
	 *            조회할 회원의 아이디
	 * @return 조회된 회원정보
	 */
	public Member selectMember(String id); // 한명의 회원조회(id중복검사 등)

	/**
	 * @param id
	 *            조회할 회원의 아이디
	 * @return 메일 인증 여부
	 */
	public int authenticated(String id); // 회원 인증처리

	/**
	 * @param user_no_pk
	 *            접속한 회원의 회원번호
	 * @param user_nm
	 *            회원 이름 검색에 쓰인 문자열
	 * @return 전체 회원 정보
	 */
	public ArrayList<HashMap<String, Object>> selectMemberList(@Param("user_no_pk") int user_no_pk,
			@Param("nm") String user_nm);

	/**
	 * @param user_no_pk
	 *            접속한 회원의 회원번호
	 * @return 접속한 회원의 친구 목록
	 */
	public ArrayList<HashMap<String, Object>> getFriendList(int user_no_pk);

	/**
	 * @param user_no_pk
	 *            접속한 회원의 회원번호
	 * @return 친구 요청한 회원 목록
	 */
	public ArrayList<HashMap<String, Object>> getFriendRequestList(int user_no_pk);

}
