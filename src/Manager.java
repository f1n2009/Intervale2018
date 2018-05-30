import java.util.Date;
import java.util.List;

class Manager extends Employee {
    private List<Integer> workersId;
    Manager(int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, List<Integer> workersId){
        super(id, lastName, firstName, patronymic, birthday, startWork, workersId);
    }

    @Override
    public List<Integer> getWorkersId() {
        return super.workersId;
    }

    @Override
    String getAllValues() {
        return String.valueOf(this.getId())+" "+this.getLastName()+" "+this.getFirstName()+" "+this.getPatronymic()+
                " "+this.getBirthday()+" "+this.getStartWork();
    }

    @Override
    public void changeType(String type) {

    }
}
