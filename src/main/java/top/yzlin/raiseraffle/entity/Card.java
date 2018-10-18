package top.yzlin.raiseraffle.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

/**
 * 抽奖的实体类
 * 设置奖品与中奖几率
 *
 */
public class Card implements Comparable<Card> {
    public static final String UR="UR";
    public static final String SSSR="SSSR";
    public static final String SSR="SSR";
    public static final String SR="SR";
    public static final String S="S";
    public static final String R="R";

    public static Card EMPTY;

    static {
        EMPTY = new Card();
        EMPTY.prize="bug奖品";
        EMPTY.probability=0x7fffffff;
    }

    private int cardID;
    private String prize;
    private String level;
    private int probability;
    private String picturePath;
    private int score;

    @JSONField(name = "level")
    public String getLevel() {
        return level;
    }

    @JSONField(name = "level")
    public void setLevel(String level) {
        this.level = level;
    }

    @JSONField(name = "card_id")
    public int getCardID() {
        return cardID;
    }

    @JSONField(name = "card_id")
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    @JSONField(name = "probability")
    public int getProbability() {
        return probability;
    }

    @JSONField(name = "probability")
    public void setProbability(int probability) {
        this.probability = probability;
    }

    @JSONField(name = "prize")
    public String getPrize() {
        return prize;
    }

    @JSONField(name = "prize")
    public void setPrize(String prize) {
        this.prize = prize;
    }

    @JSONField(name = "picture_path")
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @JSONField(name = "picture_path")
    public String getPicturePath() {
        return picturePath;
    }

    @JSONField(name = "score")
    public int getScore() {
        return score;
    }

    @JSONField(name = "score")
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return cardID == card.cardID &&
                probability == card.probability &&
                score == card.score &&
                Objects.equals(prize, card.prize) &&
                Objects.equals(picturePath, card.picturePath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardID, prize, probability, picturePath, score);
    }

    @Override
    public int compareTo(Card o) {
        int i=Integer.compare(this.getProbability(), o.getProbability());
        if(i==0){
            return Integer.compare(this.getScore(), o.getScore());
        }else{
            return i;
        }
    }
}
