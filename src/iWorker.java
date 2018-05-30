import java.util.Date;
import java.util.List;

public interface iWorker {
    void changeType(String type);
    String getFirstName();
    String getLastName();
    String getPatronymic();
    Date getBirthday();
    Date getStartWork();
    List <Integer>getWorkersId();
    int getId();
}
