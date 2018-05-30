import java.util.Comparator;
import java.util.Date;
import java.util.List;

abstract class Employee implements iWorker , Comparable<Employee>{

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthday;
    private Date startWork;
    int managerId;
    List<Integer> workersId;
    private String description;

    Employee(int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, int managerId){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
        this.managerId = managerId;
    }

    Employee(int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, List<Integer> workersId){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
        this.workersId = workersId;
    }

    Employee(int id, String lastName, String firstName, String patronymic, Date birthday, Date startWork, String description){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
        this.description = description;
    }

    @Override
    abstract public void changeType(String type);

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getPatronymic() {
        return this.patronymic;
    }

    @Override
    public Date getBirthday() {
        return this.birthday;
    }

    @Override
    public Date getStartWork() {
        return this.startWork;
    }

    @Override
    abstract public List<Integer> getWorkersId();

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    abstract String getAllValues();

    @Override
    public int compareTo(Employee o) {
        return this.getLastName().compareTo(o.getLastName());
    }

    static final Comparator<Employee> COMPARE_BY_LAST_NAME = Comparator.comparing(Employee::getLastName);
    static final Comparator<Employee> COMPARE_BY_START_WORK = Comparator.comparing(Employee::getStartWork);
}
