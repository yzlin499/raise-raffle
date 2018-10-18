package top.yzlin.raiseraffle.dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.yzlin.raiseraffle.entity.Card;
import top.yzlin.raiseraffle.entity.UserCard;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
@Repository
public interface ArchiveDAO {

    @Insert("INSERT INTO archive(userID,cardID,num) " +
            "VALUES(#{userID}, #{cardID},1)")
    int insertArchive(@Param("userID") int userID,@Param("cardID") int cardID);

    @Select("SELECT count(1)>0 FROM archive WHERE userID=#{userID} AND cardID=#{cardID}")
    boolean isExistArchive(@Param("userID") int userID,@Param("cardID") int cardID);


    @Select("SELECT cardID FROM archive WHERE userID=#{userID}")
    Set<Integer> existingCardSet(@Param("userID") int userID);


    @Update("UPDATE archive " +
            "SET num=(SELECT num FROM archive where userID=#{userID} AND cardID=#{cardID})+1 " +
            "WHERE userID=#{userID} AND cardID=#{cardID}")
    int addCardNum(@Param("userID") int userID,@Param("cardID") int cardID);


    @Select("select * From card,archive where userID=#{userID} and card.cardID=archive.cardID order by card.cardID")
    List<UserCard> selectAllCard(@Param("userID") int userID);

}
