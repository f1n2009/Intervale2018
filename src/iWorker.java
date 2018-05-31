import java.util.List;

interface iWorker {

    Employee changetype(int managerId);
    Employee changetype(List<Integer> workersId);
    Employee changetype(String description);
}
