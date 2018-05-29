import java.util.ArrayList;
import java.util.Date;

class Manager extends SomeWorker {
    private ArrayList<SomeWorker> employees;
    Manager (String lastName, String firstName, String patronymic, Date birthday, Date startWork, ArrayList <SomeWorker> employees){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
        this.employees = employees;
    }

    @Override
    void changeType(String type) {

    }
}
