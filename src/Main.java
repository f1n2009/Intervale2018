import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main implements iWorker{

    public static void main(String[] args) {
        List<SomeWorker> workers = new ArrayList<>();
        //SomeWorker = new Worker("Василевский", "Павел", "Николаевич", 16165151561561561, 326165156156156156156);
        System.out.println(new Date());
    }

    @Override
    public void delWorker(SomeWorker someWorker) {

    }

    @Override
    public void addWorker(SomeWorker someWorker) {

    }

    @Override
    public void changeTypeWorker(SomeWorker someWorker) {

    }

    @Override
    public void changeManager(SomeWorker someWorker) {

    }

    @Override
    public void sortByLastName(ArrayList arrayList) {

    }

    @Override
    public void sortByDate(ArrayList arrayList) {

    }
}
