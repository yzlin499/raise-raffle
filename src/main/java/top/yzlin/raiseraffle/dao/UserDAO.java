package top.yzlin.raiseraffle.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.yzlin.raiseraffle.entity.User;

import java.util.List;

@Mapper
@Repository
public interface UserDAO {

    @Insert("INSERT INTO user(userID,nickName,score) VALUES(#{userID}, #{nickName}, #{score})")
    int insertUser(User user);

    @Select("Select * from user where userID=#{id}")
    User selectUserByID(@Param("id") int id);

    @Select("SELECT * FROM user WHERE nickName=#{name}")
    User selectUserByName(@Param("name") String name);

    @Update("UPDATE user SET nickName=#{nickName} WHERE userID=#{userID}")
    int updateUserName(User user);

    @Update("UPDATE user SET score=#{score} WHERE userID=#{userID}")
    int updateUserScore(User user);

    @Update("UPDATE user " +
            "SET score=(SELECT score FROM user where userID=#{userID})+#{score} " +
            "WHERE userID=#{userID}")
    int addUserScore(@Param("userID") int userID,@Param("score") int score);
}
