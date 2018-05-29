import java.util.Date;

class Worker extends SomeWorker {
    Worker (String lastName, String firstName, String patronymic, Date birthday, Date startWork){
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.startWork = startWork;
    }

    @Override
    void changeType(String type) {
        switch (type){
            case ("manager"):
                type = "manager";
        }
    }
}
