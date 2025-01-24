package com.kh.app0121;

import lombok.Builder;

@Builder
public class BoardVo {

    private String no;
    private String title;
    private String content;

    private BoardVo(){

    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    static class BoardVoBuilder {

        private String no;
        private String title;
        private String content;

        public BoardVoBuilder(){}

        public BoardVoBuilder no(String no) {
            this.no = no;
            return this;
        }

        public BoardVoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BoardVoBuilder content(String content) {
            this.content = content;
            return this;
        }

        public BoardVo build(){
            BoardVo vo = new BoardVo();
            vo.setNo(no);
            vo.setTitle(title);
            vo.setContent(content);

            return vo;
        }

    }

    public static BoardVoBuilder builder(){
        return new BoardVo.BoardVoBuilder();
    }

}
