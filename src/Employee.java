import java.util.Comparator;
import java.util.Date;
import java.util.List;

abstract class Employee implements Comparable<Employee>, iWorker {

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthday;
    private Date startWork;

    Employee(int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
    }

    Employee() {
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    String getPatronymic() {
        return this.patronymic;
    }

    Date getBirthday() {
        return this.birthday;
    }

    Date getStartWork() {
        return this.startWork;
    }

    int getId() {
        return id;
    }

    abstract String getAllValues();

    @Override
    public int compareTo(Employee o) {
        return this.getLastName().compareTo(o.getLastName());
    }

    static final Comparator<Employee> COMPARE_BY_LAST_NAME = Comparator.comparing(Employee::getLastName);
    static final Comparator<Employee> COMPARE_BY_START_WORK = Comparator.comparing(Employee::getStartWork);

    @Override
    public Employee changetype(int managerId) {
        return new Worker(getId(), getLastName(), getFirstName(), getPatronymic(), getBirthday(), getStartWork(), managerId);
    }

    @Override
    public Employee changetype(List<Integer> workersId) {
        return new Manager(getId(), getLastName(), getFirstName(), getPatronymic(), getBirthday(), getStartWork(), workersId);
    }

    @Override
    public Employee changetype(String description) {
        return new OtherWorker(getId(), getLastName(), getFirstName(), getPatronymic(), getBirthday(), getStartWork(), description);
    }
}
