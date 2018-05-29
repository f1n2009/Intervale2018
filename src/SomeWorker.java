import java.util.ArrayList;
import java.util.Date;

abstract class SomeWorker {

    int id;
    String firstName;
    String lastName;
    String patronymic;
    Date birthday;
    Date startWork;
    Manager manager;
    String type;

    abstract void changeType(String type);
}
