package top.yzlin.raiseraffle.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Result {
    private User user;
    private List<Card> cardList;
    private int insertScore;
    private int oldScore;

    @JSONField(name = "user")
    public User getUser() {
        return user;
    }

    @JSONField(name = "user")
    public void setUser(User user) {
        this.user = user;
    }

    @JSONField(name = "card_list")
    public List<Card> getCardList() {
        return cardList;
    }

    @JSONField(name = "card_list")
    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @JSONField(name = "insert_score")
    public int getInsertScore() {
        return insertScore;
    }

    @JSONField(name = "insert_score")
    public void setInsertScore(int insertScore) {
        this.insertScore = insertScore;
    }

    @JSONField(name = "old_score")
    public int getOldScore() {
        return oldScore;
    }

    @JSONField(name = "old_score")
    public void setOldScore(int oldScore) {
        this.oldScore = oldScore;
    }
}
