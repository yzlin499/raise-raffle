package top.yzlin.raiseraffle.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.yzlin.raiseraffle.entity.Card;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface CardDAO {

    @Insert("INSERT INTO card(cardID,prize,probability,level,picturePath,score) " +
            "VALUES(#{cardID}, #{prize}, #{probability}, #{level},#{picturePath}, #{score})")
    int insertCard(Card user);

    @Select("Select * from card")
    ArrayList<Card> selectAllCard();

    @Select("Select * from card where level=#{level}")
    ArrayList<Card> selectCardByLevel(@Param("level") String level);

    @Select("Select * from card where cardID=#{id}")
    Card selectCardByID(@Param("id") int id);

//    @Select("SELECT * FROM user WHERE userID=#{name}")
//    Card selectCardByName(@Param("name") String name);
//
//    @Update("UPDATE user SET nickName=#{nickName} WHERE userID=#{userID}")
//    int updateCardName(Card user);
//
//    @Update("UPDATE user SET score=#{score} WHERE userID=#{userID}")
//    int updateCardScore(Card user);
}
