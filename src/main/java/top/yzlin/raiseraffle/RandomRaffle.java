package top.yzlin.raiseraffle;

import top.yzlin.raiseraffle.entity.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomRaffle {

    private static final Random RAND = new Random();
    private int probabilitySum;
    private double minLimit;
    private ArrayList<Card> rafflePrizeList = new ArrayList<>();

    public double getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(double minLimit) {
        this.minLimit = minLimit;
    }

    public int getProbabilitySum() {
        return probabilitySum;
    }

    public Random getRand() {
        return RAND;
    }

    public ArrayList<Card> getRafflePrizeList() {
        return rafflePrizeList;
    }

    public void setRafflePrizeList(ArrayList<Card> rafflePrizeList) {
        this.rafflePrizeList = rafflePrizeList;
        probabilitySum = rafflePrizeList.stream()
                .mapToInt(Card::getProbability)
                .sum();
    }

    public void addRafflePrize(Card... prize) {
        rafflePrizeList.addAll(Arrays.asList(prize));
        probabilitySum = rafflePrizeList.stream()
                .mapToInt(Card::getProbability)
                .sum();
    }


    public Card raffle() {
        int rand = RAND.nextInt(probabilitySum) + 1;
        int tempCount = 0;
        for (Card tempPrize : rafflePrizeList) {
            //随机位置
            tempCount += tempPrize.getProbability();
            if (tempCount >= rand) {
                return tempPrize;
            }
        }
        return rafflePrizeList.get(RAND.nextInt(rafflePrizeList.size()));
    }

    @Override
    public String toString() {
        return "RandomRaffle{" +
                "probabilitySum=" + probabilitySum +
                ", minLimit=" + minLimit +
                ", rafflePrizeList=" + rafflePrizeList +
                '}';
    }
}
