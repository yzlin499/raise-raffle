package top.yzlin.raiseraffle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yzlin.raiseraffle.dao.ArchiveDAO;
import top.yzlin.raiseraffle.dao.CardDAO;
import top.yzlin.raiseraffle.dao.UserDAO;
import top.yzlin.raiseraffle.entity.Card;
import top.yzlin.raiseraffle.entity.RaiseData;
import top.yzlin.raiseraffle.entity.Result;
import top.yzlin.raiseraffle.entity.User;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RaiseRaffleServletApplicationTests {

    private RaiseRaffle raiseRaffle;
    private ArchiveDispose archiveDispose;
    private UserDispose userDispose;

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
    private ArchiveDAO archiveDAO;

    @Test
    public void contextLoads() {


    }

}
