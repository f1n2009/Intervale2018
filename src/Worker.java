import java.util.Date;

class Worker extends Employee{

    private final String type = "работник";
    private int managerId;

    Worker (int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, int managerId){
        super(id, lastName, firstName, patronymic, birthday, startWork);
        this.managerId = managerId;
        super.setType(this.type);
    }

    int getManagerId() {
        return managerId;
    }

    void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    String getAllValues() {
        return String.valueOf(this.getId())+" "+this.getLastName()+" "+this.getFirstName()+" "+this.getPatronymic()+
                " "+this.getBirthday()+" "+this.getStartWork()+" "+type+" "+getManagerId();
    }
}
