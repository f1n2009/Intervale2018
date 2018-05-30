import java.util.Date;
import java.util.List;

class Worker extends Employee {

    Worker (int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, int managerId){
        super(id, lastName, firstName, patronymic, birthday, startWork, managerId);
    }

    @Override
    public void changeType(String type) {
        switch (type){
            case ("manager"):
                type = "manager";
        }
    }

    public int getManagerId() {
        return managerId;
    }

    @Override
    public List<Integer> getWorkersId() {
        return null;
    }

    @Override
    String getAllValues() {
        return String.valueOf(this.getId())+" "+this.getLastName()+" "+this.getFirstName()+" "+this.getPatronymic()+
                " "+this.getBirthday()+" "+this.getStartWork();
    }
}
