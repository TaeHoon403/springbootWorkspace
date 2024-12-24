package com.kh.SEMI.notice.service;

import com.kh.SEMI.admin.vo.NoticeReplyVo;
import com.kh.SEMI.notice.mapper.NoticeMapper;
import com.kh.SEMI.notice.vo.NoticeVo;
import com.kh.SEMI.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final NoticeMapper mapper;

    // 공지사항 작성
    public int write(NoticeVo vo) {
        return mapper.write(vo);
    }//write

    // 공지사항 목록 조회
    public List<NoticeVo> getNoticeList(PageVo pvo, String searchValue) {

        // 검색어 있는 경우 검색 조건 추가
        String str = "";
        if(searchValue != null && searchValue.length()>0){
            str = "AND TITLE LIKE '%"+searchValue+"%'";
        }

        return mapper.getNoticeList(pvo,str);
    }//list

    // 전체 공지사항 갯수 조회
    public int getNoticeCnt(String searchValue) {

        // 검색어 있는 경우 검색 조건 추가
        String str = "";
        if(searchValue != null && searchValue.length()>0){
            str = "AND TITLE LIKE '%"+searchValue+"%'";
        }

        return mapper.getNoticeCnt(str);
    }//getNoticeCnt
    
    // 공지사항 상세 조회
    public NoticeVo getNoticeByNo(String no) {
        return mapper.getNoticeByNo(no);
    }//detail

    // 공지사항 삭제
    public int delete(List<String> noticeNoList) {

        String x = String.join(",",noticeNoList);

        return mapper.delete(x);
    }//delete

    // 공지사항 수정
    public int edit(NoticeVo vo) {
        return mapper.edit(vo);
    }//edit

    // 공지사항 댓글 작성
    public int replyWrite(NoticeReplyVo vo) {
        return mapper.replyWrite(vo);
    }//replyWrite

    // 공지사항 댓글 목록 조회
    public List<NoticeReplyVo> getNoticeReplyList(String noticeNo) {
        return mapper.getNoticeReplyList(noticeNo);
    }//getNoticeReplyList

}//class
