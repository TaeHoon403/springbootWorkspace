package com.kh.app.member.vo;

public class MemberVo {

    private String id;
    private String pw;
    private String nick;

    public MemberVo() {
    }

    public MemberVo(String id, String pw, String nick) {
        this.id = id;
        this.pw = pw;
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "MemberVo{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

}
