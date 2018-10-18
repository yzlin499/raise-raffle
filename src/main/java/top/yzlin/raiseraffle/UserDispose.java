package top.yzlin.raiseraffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yzlin.raiseraffle.dao.UserDAO;
import top.yzlin.raiseraffle.entity.User;

import java.util.Objects;

@Component
public class UserDispose {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User dispose(int userID, String nickName){
        User user=userDAO.selectUserByID(userID);
        if(user==null){
            user=new User();
            user.setUserID(userID);
            user.setScore(0);
            user.setNickName(nickName);
            userDAO.insertUser(user);
        }else if(!Objects.equals(user.getNickName(),nickName)){
            user.setNickName(nickName);
            userDAO.updateUserName(user);
        }
        return user;
    }

}
