import java.util.Date;

class OtherWorker extends Employee{

    private final String type  = "другой";
    private String description;

    OtherWorker (int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, String description){
        super(id, lastName, firstName, patronymic, birthday, startWork);
        this.description = description;
        super.setType(this.type);
    }

    @Override
    String getAllValues() {
        return String.valueOf(this.getId())+" "+this.getLastName()+" "+this.getFirstName()+" "+this.getPatronymic()+
                " "+this.getBirthday()+" "+this.getStartWork()+" "+type+" "+description;
    }

    String getDescription() {
        return description;
    }
}
