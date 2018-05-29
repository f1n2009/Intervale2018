import javax.xml.crypto.Data;
import java.util.ArrayList;

public interface iWorker {
    void delWorker(SomeWorker someWorker);
    void addWorker(SomeWorker someWorker);
    void changeTypeWorker(SomeWorker someWorker);
    void changeManager(SomeWorker someWorker);
    void sortByLastName(ArrayList arrayList);
    void sortByDate(ArrayList arrayList);
}
