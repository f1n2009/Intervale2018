import java.util.Date;

public class OtherWorker extends SomeWorker {
    private String description;
    OtherWorker (String lastName, String firstName, String patronymic, Date birthday, Date startWork, String description){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
        this.description = description;
    }
}
