import java.util.Date;
import java.util.List;

class Manager extends Employee {

    private String type = "менеджер";
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
                " "+this.getBirthday()+" "+this.getStartWork()+" "+type+" "+lineWorkersId();
    }

    @Override
    public void changeType(String type) {

    }

    private String lineWorkersId (){
        StringBuilder lineWorkersId= new StringBuilder();
        for (Integer aWorkersId : workersId) lineWorkersId.append(String.valueOf(aWorkersId)).append(" ");
        return lineWorkersId.toString();
    }
}
