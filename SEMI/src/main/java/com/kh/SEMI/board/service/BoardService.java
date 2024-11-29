package com.kh.SEMI.board.service;

import com.kh.SEMI.board.mapper.BoardMapper;
import com.kh.SEMI.board.vo.AttachmentVo;
import com.kh.SEMI.board.vo.BoardVo;
import com.kh.SEMI.board.vo.CategoryVo;
import com.kh.SEMI.util.page.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoardService {

    private final BoardMapper mapper;

    /**
     * 게시글 작성
     * 게시글 내용과 첨부파일을 DB 에 저장하는 메서드 이다.
     * @인서트결과 가 양수이면 성공, 음수이면 실패
     * @param vo
     * @param changeNameList
     * @return 인서트결과
     */
    public int write(BoardVo vo, List<String> changeNameList) {

        // 게시글 작성
        int result1 = mapper.write(vo); // 성공하면 1

        // 첨부파일 저장
        int result2 = 1;
        if(!changeNameList.isEmpty()){
            result2 = mapper.insertBoardAttachment(changeNameList); // 성공하면 양수
        }

        // 결과 반환
        return result1 * result2;
    }

    // 카테고리 항목 조회
    public List<CategoryVo> categoryList() {
        return mapper.categoryList();
    }
    
    // 게시글 전체 목록 조회
    public List<BoardVo> getBoardList(PageVo pvo, String searchType, String searchValue) {
        return mapper.getBoardList(pvo,searchType,searchValue);
    }

    // 조회할 게시글 갯수 조회
    public int getBoardCount(String searchType, String searchValue) {
        return mapper.getBoardCount(searchType, searchValue);
    }

    // 게시글 조회 By No
    public BoardVo getBoard(String bno) {
        int result = mapper.increaseHit(bno);
        if(result != 1){
            String errMsg = "BOARD > SERVICE > 상세조회 > 조회수증가 에러";
            log.error(errMsg);
            throw new IllegalStateException(errMsg);
        }
        return mapper.getBoard(bno);
    }
    
    // 게시글 삭제
    public int del(String bno) {
        return mapper.del(bno);
    }

    // 게시글 수정
    public void update(BoardVo vo, List<String> changeNameList){

        int result1 = mapper.update(vo);
        if(result1 != 1){
            throw new IllegalStateException("ERROR ~ BOARd > update > result1 error ~~~");
        }

        int result2 = 1;
        if(!changeNameList.isEmpty()){
            result2 = mapper.updateBoardAttachment(changeNameList , vo.getNo());
        }
        if(result2 < 1){
            throw new IllegalStateException("ERROR ~ BOARd > update > result2 error ~~~");
        }

    }

    // 게시글 첨부파일 목록 조회
    public List<AttachmentVo> getAttachmentVoList(String bno) {
        return mapper.getAttachmentVoList(bno);
    }

    // 게시글 첨부파일 삭제
    public int delAttachment(String ano) {
        return mapper.delAttachment(ano);
    }

}
