import java.util.Date;
import java.util.List;

class Manager extends Employee {

    private final String type = "менеджер";

    private List<Integer> workersId;

    Manager(int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, List<Integer> workersId){
        super(id, lastName, firstName, patronymic, birthday, startWork);
        this.workersId = workersId;
        super.setType(this.type);
    }

    @Override
    String getAllValues() {
        return String.valueOf(this.getId())+" "+this.getLastName()+" "+this.getFirstName()+" "+this.getPatronymic()+
                " "+this.getBirthday()+" "+this.getStartWork()+" "+type+" "+lineWorkersId();
    }

    private String lineWorkersId (){
        StringBuilder lineWorkersId= new StringBuilder();
        for (Integer aWorkersId : workersId) lineWorkersId.append(String.valueOf(aWorkersId)).append(" ");
        return lineWorkersId.toString();
    }

    List<Integer> getWorkersId() {
        return workersId;
    }
}
