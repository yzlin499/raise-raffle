package top.yzlin.raiseraffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yzlin.raiseraffle.dao.ArchiveDAO;
import top.yzlin.raiseraffle.dao.UserDAO;
import top.yzlin.raiseraffle.entity.Card;
import top.yzlin.raiseraffle.entity.User;

import java.util.*;

@Component
public class ArchiveDispose {
    private ArchiveDAO archiveDAO;
    private UserDAO userDAO;
    private SaveCard saveCard;

    @Autowired
    public void setSaveCard(SaveCard saveCard) {
        this.saveCard = saveCard;
    }

    @Autowired
    public void setArchiveDAO(ArchiveDAO archiveDAO) {
        this.archiveDAO = archiveDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int dispose(User user, List<Card> cardList){
        int score=0;
        Set<Integer> cardIDSet=archiveDAO.existingCardSet(user.getUserID());
        int userID=user.getUserID();
        List<Integer> addList=new ArrayList<>();
        List<Integer> insList=new ArrayList<>();
        for (Card card : cardList) {
            int cardID=card.getCardID();
            if(cardIDSet.contains(cardID)){
                addList.add(cardID);
                score+=card.getScore();
            }else{
                insList.add(cardID);
            }
        }
        saveCard.saveCard(userID,addList,insList);
        user.insertScore(score);
        userDAO.addUserScore(user.getUserID(),score);
        return score;
    }
}
