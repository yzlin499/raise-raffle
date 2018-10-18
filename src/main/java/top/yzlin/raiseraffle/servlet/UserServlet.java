package top.yzlin.raiseraffle.servlet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.yzlin.raiseraffle.dao.ArchiveDAO;
import top.yzlin.raiseraffle.dao.UserDAO;
import top.yzlin.raiseraffle.entity.User;

import java.util.Objects;

@ResponseBody
@Controller
@RequestMapping("/user")
public class UserServlet {
    private UserDAO userDAO;
    private ArchiveDAO archiveDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setArchiveDAO(ArchiveDAO archiveDAO) {
        this.archiveDAO = archiveDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object index(@RequestParam(value = "nick_name",required = false) String name){
        if(name==null || "".equals(name)){
            return null;
        }else{
            return userDAO.selectUserByName(name);
        }
    }

    @RequestMapping(value = "/{userID}",method = RequestMethod.GET)
    public Object read(@PathVariable("userID")int userID){
        return userDAO.selectUserByID(userID);
    }

    @RequestMapping(value = "/{userID}/card",method = RequestMethod.GET)
    public Object getUserCard(@PathVariable("userID")int userID){
        return archiveDAO.selectAllCard(userID);
    }
}
