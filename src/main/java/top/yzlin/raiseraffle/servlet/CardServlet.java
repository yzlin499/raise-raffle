package top.yzlin.raiseraffle.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yzlin.raiseraffle.dao.CardDAO;

@ResponseBody
@Controller
@RequestMapping("/card")
public class CardServlet {

    private CardDAO cardDAO;

    @Autowired
    public void setCardDAO(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @RequestMapping(value = "/{cardID}" ,method = RequestMethod.GET)
    public Object index(@PathVariable("cardID")int cardID){
        return cardDAO.selectCardByID(cardID);
    }
}
