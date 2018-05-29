import java.util.Date;

public class Worker extends SomeWorker {
    Worker (String lastName, String firstName, String patronymic, Date birthday, Date startWork){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
    }
}
