package top.yzlin.raiseraffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yzlin.raiseraffle.dao.CardDAO;
import top.yzlin.raiseraffle.entity.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 策略类，只负责抽不负责记录
 */
@Component
public class RaiseRaffle {

    private final int once=10;
    private final static double ZERO=-0.001;
    private RandomRaffle randomRaffle;
    private RandomRaffle ssrRandom;

    @Autowired
    public void setCardAOP(CardDAO cardDAO){
        ArrayList<Card> cards=cardDAO.selectAllCard();
        randomRaffle=new RandomRaffle();
        randomRaffle.setRafflePrizeList(cards);
        ssrRandom=new RandomRaffle();
        cards= cards.stream()
                .filter(c -> Objects.equals(c.getLevel(), Card.SSR))
                .collect(Collectors.toCollection(ArrayList::new));
        ssrRandom.setRafflePrizeList(cards);
    }

    public List<Card> raffleCards(double money){
        double tempMoney=money;
        ArrayList<Card> cards=new ArrayList<>((int)(money/once));
        int upLv=0;
        while((tempMoney-=once)>=ZERO){
            Card card=randomRaffle.raffle();
            cards.add(card);
            switch (card.getLevel().toUpperCase()){
                case Card.SSR: case Card.SSSR: case Card.UR: upLv++;break;
                default:
            }
        }
        int count=0;
        for (int i = 0,l=(int)(money/once/10)-upLv; i < l; i++) {
            Card card=cards.get(count);
            switch (card.getLevel().toUpperCase()){
                case Card.S: case Card.R: case Card.SR: count++;break;
                default:
                    cards.set(count,ssrRandom.raffle());
            }
        }
        cards.sort(Card::compareTo);
        return cards;
    }
}
