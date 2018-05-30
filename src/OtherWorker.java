import java.util.Date;
import java.util.List;

public class OtherWorker extends Employee {

    private String type = "другой";

    OtherWorker (int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, String description){
        super(id, lastName, firstName, patronymic, birthday, startWork, description);
    }

    @Override
    public void changeType(String type) {

    }

    @Override
    public List<Integer> getWorkersId() {
        return null;
    }

    @Override
    String getAllValues() {
        return String.valueOf(this.getId())+" "+this.getLastName()+" "+this.getFirstName()+" "+this.getPatronymic()+
                " "+this.getBirthday()+" "+this.getStartWork()+" "+type+" "+getDescription();
    }
}
