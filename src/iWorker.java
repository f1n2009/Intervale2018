import java.util.List;

interface iWorker {

    public Employee changetype(int managerId);
    public Employee changetype(List<Integer>workersId);
    public Employee changetype(String description);
}
