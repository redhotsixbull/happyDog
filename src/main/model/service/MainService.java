package main.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import main.model.vo.MainVO;
import siAdoptionBoard.model.adoptionBoardDao.AdoptionBoardDao;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;
import siNotice.model.noticeDao.NoticeDao;
import siNotice.model.noticeVo.Notice;
import siTemplete.JDBCTemplete;
import volunteer.model.dao.VoluntaryDao;
import volunteer.model.vo.VoluntaryRegister;

public class MainService {
	
	public MainVO mainList(){
		Connection conn = JDBCTemplete.getConnection();
		MainVO mainList = new MainVO();
		/* 메인 :: 후기게시판 게시글 노출 */
		ArrayList<AdoptionBoard> adoptionBoardList = new AdoptionBoardDao().adoptionBoardAll(conn);
		/* 메인 :: 공지사항 게시글 노출 */
		ArrayList<Notice> noticeList = new NoticeDao().noticeAll(conn);
		/* 메인 :: 봉사활동 게시판 공고 노출 */
		ArrayList<VoluntaryRegister> volunList = new VoluntaryDao().mainVoluntaryList(conn, 1, 5);
		
		mainList.setAdoptionBoardList(adoptionBoardList);
		mainList.setNoticeList(noticeList);
		mainList.setVolunList(volunList);
		
		JDBCTemplete.close(conn);
		return mainList;
	}

}
