package top.yzlin.raiseraffle.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    private int userID;
    private String nickName;
    private int score;

    @JSONField(name = "user_id")
    public int getUserID() {
        return userID;
    }

    @JSONField(name = "user_id")
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @JSONField(name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    @JSONField(name = "nick_name")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @JSONField(name = "score")
    public int getScore() {
        return score;
    }

    @JSONField(name = "score")
    public void setScore(int score) {
        this.score = score;
    }

    public int insertScore(int score){
        return this.score+=score;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", nickName='" + nickName + '\'' +
                ", score=" + score +
                '}';
    }
}
