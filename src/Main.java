import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private Map<Integer, Employee> allWorkers;

    private Main(String file) throws IOException {
        allWorkers = new HashMap<>();
        this.readerWriter(file);
        BufferedReader inputName = new BufferedReader(new InputStreamReader(System.in));

        String command = "";
        while (!command.equals("exit")){
            try {
                System.out.print("Введите команду:");
                command = inputName.readLine();

            } catch (IOException ex) {
                System.out.println("Команда введена неверно!");
            }
        switch (command) {

            case "del":
                System.out.println("Введите id работника:");
                int id = Integer.valueOf(inputName.readLine());
                delWorker(id);
                System.out.println("Работник с id = "+id+" удален!");
                break;

            case "add":
                addWorker();
                System.out.println("Работники добавлены!");
                break;

            case "save":
                save();
                System.out.println("Файл сохранен!");
                break;

            case "exit":
                command = "exit";
                break;

                default:
                    System.out.println("Команда введена не верно!");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Список команд:");
        System.out.println("del - удаление работника");
        System.out.println("add - добавление работников из файла");
        System.out.println("changetype - изменение типа работника");
        System.out.println("changemabager изменение менеджера работника");
        System.out.println("sortbylastname - сортировка списка по фамилиям");
        System.out.println("sortbydate - сортировка списка по дате принятия на работу");
        System.out.println("save - сохранение списка в файл");
        System.out.println("");
        new Main("input.txt");


    }

    private void delWorker(int id) {
        allWorkers.remove(id);

    }

    public void addWorker() {
        this.readerWriter("new_employees.txt");
    }

    public void changeTypeWorker(Employee someWorker) {

    }

    public void changeManager(Employee someWorker) {

    }

    public void sortByLastName(ArrayList arrayList) {

    }

    public void sortByDate(ArrayList arrayList) {

    }

    public void save (){
        BufferedWriter writeFromFile = null;
        try {
            String newFile = "output.txt";
            writeFromFile = new BufferedWriter(new FileWriter(newFile));
            for(Map.Entry<Integer, Employee> entry : allWorkers.entrySet()) {
                try {
                    writeFromFile.write(entry.getValue().getAllValues() + '\n');
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
    }

    private void readerWriter(String file) {
            BufferedReader readFromFile = null;
            try {
                readFromFile = new BufferedReader(new FileReader(file));
                String line;
                while ((line = readFromFile.readLine()) != null) {
                    allWorkers.put(parser(line).getId(), parser(line));
                }
            } catch (IOException | ParseException e) {
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

    private Employee parser (String line) throws ParseException {
        Employee newEmployee = null;
        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
        Queue <String> word = new ArrayDeque<>();
        String curr;
        String value = "";
        while (tokenizer.hasMoreTokens()) {

                curr = tokenizer.nextToken();
                value+=curr;
                word.add(value);
                value="";

        }
        int id = Integer.parseInt(word.poll());
        String lastname = word.poll();
        String firstName = word.poll();
        String patronymic = word.poll();
        Date birthday = transformDate(word.poll());
        Date startWork = transformDate(word.poll());
        String type = word.poll();
        if (type.equals("работник")){
            int managerId = Integer.parseInt(word.poll());
            newEmployee = new Worker(id, lastname, firstName, patronymic, birthday, startWork, managerId);}
        else if (type.equals("менеджер")){
            List <Integer> workersId = new ArrayList<>();
            while (!word.isEmpty())
                workersId.add(Integer.parseInt(word.poll()));
            newEmployee = new Manager(id, lastname, firstName, patronymic, birthday, startWork, workersId);}
        else if (type.equals("другой")){
            String description = "";
            while (!word.isEmpty())
                description += word.poll();
            newEmployee = new OtherWorker(id, lastname, firstName, patronymic, birthday, startWork, description);}
        return newEmployee;
    }

    private Date transformDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }
}
