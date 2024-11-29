package com.kh.SEMI.util.page;

import lombok.Data;

@Data
public class PageVo {

    private int listCount; // 전체 게시글 갯수
    private int currentPage; // 현재 페이지 번호
    private int pageLimit; // 아래 페이지 nav 에서 보여줄 페이지 번호 갯수
    private int boardLimit; // 리스트 화면에서 보여줄 최대 리스트 수

    private int maxPage; // 가장 마지작 페이지 번호
    private int startPage; // nav 에서 첫 페이지 번호
    private int endPage; // nav 에서 마지막 페이지 번호

    private int offset; // SQL 에 사용될 값 (몇개를 건너뛰고 읽을지)

    public PageVo(int listCount, int currentPage, int pageLimit, int boardLimit) {
        this.listCount = listCount;
        this.currentPage = currentPage;
        this.pageLimit = pageLimit;
        this.boardLimit = boardLimit;

        this.maxPage = (int)Math.ceil((double)listCount / boardLimit);
        this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1; //
        this.endPage = startPage + pageLimit - 1; // nav 처음 번호 + 최대 수 - 1 = 해당 nav의 마지막 번호
        if(endPage > maxPage) {
            endPage = maxPage;
        }
        this.offset = boardLimit * (currentPage-1); // 앞에서 보여준 리스트 수
    }

}
