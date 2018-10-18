package top.yzlin.raiseraffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.yzlin.raiseraffle.dao.ArchiveDAO;

import java.util.List;

@Component
public class SaveCard {
    private ArchiveDAO archiveDAO;

    @Autowired
    public void setArchiveDAO(ArchiveDAO archiveDAO) {
        this.archiveDAO = archiveDAO;
    }

    @Async("taskExecutor")
    public void saveCard(int userID, List<Integer> addList,List<Integer> insList){
        addList.forEach(i->{
            archiveDAO.addCardNum(userID,i);
        });
        insList.forEach(i->{
            archiveDAO.insertArchive(userID,i);
        });
    }
}
