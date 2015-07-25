package session.hackevent.mygola.com.mygola.model;

import java.util.Date;
import java.util.List;

/**
 * Created by  nitinraj.arvind on 06/07/15.
 */
public class TaskResult {

    Date startTime;
    Date endTime;
    List<GameItem> gameItemList;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<GameItem> getGameItemList() {
        return gameItemList;
    }

    public void setGameItemList(List<GameItem> gameItemList) {
        this.gameItemList = gameItemList;
    }
}
