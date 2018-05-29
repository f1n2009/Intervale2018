import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main implements iWorker{

    private static ArrayList<SomeWorker> workers;

    private Main(String file){
        workers = new ArrayList<>();
        this.readerWriter(file);
    }

    public static void main(String[] args) {
        String birthday = "27.04.1989";
        String startWork = "01.09.2018";
        SomeWorker someWorker= null;
        try {
            someWorker = new Worker("Василевский", "Павел", "Николаевич",
                    new SimpleDateFormat("dd.MM.yyyy").parse(birthday),
                    new SimpleDateFormat("dd.MM.yyyy").parse(startWork));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        new Main("input.txt");
        System.out.println(workers.get(0).birthday);
        System.out.println(workers.get(0).firstName);
        System.out.println(workers.get(0).lastName);
        System.out.println(workers.get(0).startWork);
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

    private void readerWriter(String file) {
            BufferedReader readFromFile = null;
            try {
                readFromFile = new BufferedReader(new FileReader(file));
                String line;
                while ((line = readFromFile.readLine()) != null) {
                    workers.add(parser(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                if (readFromFile != null)
                    try {
                        readFromFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
    }

    private SomeWorker parser (String line) throws ParseException {
        StringTokenizer tokenizer = new StringTokenizer(line, " ", true);
        Queue <String> word = new ArrayDeque<>();
        String curr;
        String value = "";
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            value+=curr;
            if (curr.equals(" ")) {
                //System.out.println(value);
                word.add(value);
                value="";
            }}
        String lastname = word.poll();
        String firstName = word.poll();
        String patronymic = word.poll();
        Date birthday = transformDate(word.poll());
        Date startWork = transformDate("01.10.2018");

        return new Worker(lastname, firstName, patronymic, birthday, startWork );
    }

    private Date transformDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }

            /*BufferedWriter writeFromFile = null;
            try {
                String newFile = file.replace("in", "out");
                writeFromFile = new BufferedWriter(new FileWriter(newFile));
                for (String mas : mass) {
                    List<String> expression = calculator.parse(mas);
                    try {
                        writeFromFile.write(mas + " = " + format(calculator.calc(expression)) + '\n');
                    }
                    catch (ArithmeticException e){
                        writeFromFile.write(e.getMessage() + '\n');}
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writeFromFile != null)
                    try {
                        writeFromFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }*/
    }
