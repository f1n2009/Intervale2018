import java.util.Date;

class OtherWorker extends Employee{

    private final static String type  = "другой";
    private String description;

    OtherWorker (int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, String description){
        super(id, lastName, firstName, patronymic, birthday, startWork);
        this.description = description;
    }

    @Override
    String getAllValues() {
        return String.valueOf(this.getId())+" "+this.getLastName()+" "+this.getFirstName()+" "+this.getPatronymic()+
                " "+this.getBirthday()+" "+this.getStartWork()+" "+type+" "+description;
    }
}
