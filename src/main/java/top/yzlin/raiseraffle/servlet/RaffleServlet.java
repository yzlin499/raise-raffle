package top.yzlin.raiseraffle.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yzlin.raiseraffle.ArchiveDispose;
import top.yzlin.raiseraffle.RaiseRaffle;
import top.yzlin.raiseraffle.UserDispose;
import top.yzlin.raiseraffle.dao.UserDAO;
import top.yzlin.raiseraffle.entity.Card;
import top.yzlin.raiseraffle.entity.RaiseData;
import top.yzlin.raiseraffle.entity.Result;

import java.util.Arrays;

@ResponseBody
@Controller
public class RaffleServlet {

    private RaiseRaffle raiseRaffle;
    private ArchiveDispose archiveDispose;
    private UserDispose userDispose;
    private UserDAO userDAO;

    @Autowired
    public void setRaiseRaffle(RaiseRaffle raiseRaffle) {
        this.raiseRaffle = raiseRaffle;
    }

    @Autowired
    public void setArchiveDispose(ArchiveDispose archiveDispose) {
        this.archiveDispose = archiveDispose;
    }

    @Autowired
    public void setUserDispose(UserDispose userDispose) {
        this.userDispose = userDispose;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/raffle")
    public Object raffleCard(@RequestBody RaiseData raiseData){
        Result result=new Result();
        result.setUser(userDispose.dispose(raiseData.getUserID(),raiseData.getNickName()));
        result.setOldScore(result.getUser().getScore());
        userDAO.addUserScore(raiseData.getUserID(), (int) raiseData.getRaiseMoney());
        result.setCardList(raiseRaffle.raffleCards(raiseData.getRaiseMoney()));
        result.setInsertScore(archiveDispose.dispose(result.getUser(),result.getCardList()));
        return result;
    }
}
