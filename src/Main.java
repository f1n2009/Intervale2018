import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private List<Employee> allWorkers;
    private boolean isBaseWorking;
    private UseDataBase db;

    private Main(String file) throws IOException {
        allWorkers = new ArrayList<>();
        this.reader(file);
        BufferedReader inputName = new BufferedReader(new InputStreamReader(System.in));

        String command = "";
        while (!command.equals("exit")){
                System.out.print("Введите команду:");
                command = inputName.readLine();

        switch (command) {
            case "del":
                System.out.println("Введите id работника:");
                int id = Integer.valueOf(inputName.readLine());
                if (isBaseWorking)
                    db.delWorkerFromBase(id);
                delWorker(id);
                System.out.println("Сотрудник с id = "+id+" удален!");
                break;

            case "add":
                if (isBaseWorking){
                    System.out.println("Не реализовано для БД!"); break;}
                addWorker();
                System.out.println("Сотрудники добавлены!");
                break;

            case "changetype":
                if (isBaseWorking){
                    System.out.println("Не реализовано для БД!"); break;}
                System.out.println("Введите id сотрудника:");
                id = Integer.valueOf(inputName.readLine());
                System.out.println("Введите новый тип сотрудника:");
                String type = inputName.readLine();
                String[] employes = {"работник", "менеджер", "другой"};
                while (!Arrays.asList(employes).contains(type)){
                    System.out.println("Тип сотрудника введен неверно!");
                    type = inputName.readLine();}

                switch (type){
                    case "работник":
                        System.out.println("Введите ID менеджера:");
                        int managerId = Integer.valueOf(inputName.readLine());
                        changetype(id, managerId);
                        break;
                    case "менеджер":
                        System.out.println("Введите ID работников через запятую:");
                        String line = inputName.readLine();
                        StringTokenizer tokenizer = new StringTokenizer(line, ",", false);
                        List <Integer> workersId = new ArrayList<>();
                        while (tokenizer.hasMoreTokens()) {
                            int woreker = Integer.valueOf(tokenizer.nextToken());
                            workersId.add(woreker);
                        }
                        changetype(id, workersId);
                        break;
                    case "другой":
                        System.out.println("Введите описание сотрудника:");
                        String description = inputName.readLine();
                        changetype(id, description);
                        break; }
                System.out.println("Работник с id = "+id+" теперь "+type+"!");
                break;

            case "save":
                save();
                System.out.println("Файл сохранен!");
                break;

            case "changemanager":
                if (isBaseWorking){
                    System.out.println("Не реализовано для БД!"); break;}
                System.out.println("Введите id работника:");
                id = Integer.valueOf(inputName.readLine());
                System.out.println("Введите id менеджера:");
                int managerId = Integer.valueOf(inputName.readLine());
                changeManager(id, managerId);
                System.out.println("Менеджер изменен!");
                break;

            case "sortbylastname":
                if (isBaseWorking)
                    db.sortDataBaseByLastName();
                allWorkers.sort(Employee.COMPARE_BY_LAST_NAME);
                System.out.println("Список отсортирован по фамилиям!");
                break;

            case "sortbydate":
                if (isBaseWorking)
                    db.sortDataBaseByDate();
                allWorkers.sort(Employee.COMPARE_BY_START_WORK);
                System.out.println("Список отсортирован по дате приема на работу!");
                break;

            case "base":
                if (isBaseWorking){
                    System.out.println("Вы уже работаете с базой!");
                    break;
                }
                db = new UseDataBase(allWorkers);
                isBaseWorking = true;
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
        System.out.println("del - удаление сотрудника");
        System.out.println("add - добавление сотрудников из файла");
        System.out.println("changetype - изменение типа сотрудника");
        System.out.println("changemanager - изменение менеджера работника");
        System.out.println("sortbylastname - сортировка списка по фамилиям");
        System.out.println("sortbydate - сортировка списка по дате принятия на работу");
        System.out.println("save - сохранение списка в файл");
        System.out.println("exit - выход из программы");
        System.out.println("base - работа с базой данных");
        System.out.println("");
        new Main("input.txt");}

    private void delWorker(int id) {
        for (int i = 0; i < allWorkers.size(); i++){
            if (allWorkers.get(i).getId()==id){
                allWorkers.remove(i);
            break;}
        }
    }

    private void addWorker() {
        this.reader("new_employees.txt");}

    private void changetype(int id, int managerId) {
        for (int i = 0; i < allWorkers.size(); i++){
            if (allWorkers.get(i).getId()==id) {
                allWorkers.set(i, allWorkers.get(i).changetype(managerId));}
            if (allWorkers.get(i).getId()==managerId) {
                if (allWorkers.get(i) instanceof Manager){
                    ((Manager) allWorkers.get(i)).getWorkersId().add(id);
                    }
                }
        }
    }

    private void changetype(int id, List <Integer> workersId) {
        for (int i = 0; i < allWorkers.size(); i++){
            if (allWorkers.get(i).getId()==id) {
                allWorkers.set(i, allWorkers.get(i).changetype(workersId));
                break;
            }
        }
    }

    private void changetype(int id, String description) {
        for (int i = 0; i < allWorkers.size(); i++){
            if (allWorkers.get(i).getId()==id) {
                allWorkers.set(i, allWorkers.get(i).changetype(description));
                break;
            }
        }
    }

    private void changeManager(int id, int managerId) {
        for (Employee allWorker : allWorkers) {
            if (allWorker.getId() == id) {
                if (allWorker instanceof Worker) {
                    ((Worker) allWorker).setManagerId(managerId);
                } else {
                    System.out.println("Id работника введен неверно!");
                }
            }
            if (allWorker.getId() == managerId) {
                if (allWorker instanceof Manager) {
                    ((Manager) allWorker).getWorkersId().add(id);
                } else {
                    System.out.println("Id менеджера введен неверно!");
                }
            }
        }
    }

    private void save(){
        if(isBaseWorking)
            allWorkers = db.saveFromBase();
        BufferedWriter writeFromFile = null;
        try {
            String newFile = "output.txt";
            writeFromFile = new BufferedWriter(new FileWriter(newFile));
            for(Employee entry : allWorkers) {
                try {
                    writeFromFile.write(entry.getAllValues() + '\n');
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

    private void reader(String file) {
            BufferedReader readFromFile = null;
            try {
                readFromFile = new BufferedReader(new FileReader(file));
                String line;
                while ((line = readFromFile.readLine()) != null) {
                    allWorkers.add(parser(line));
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
        StringBuilder value = new StringBuilder();
        while (tokenizer.hasMoreTokens()) {

                curr = tokenizer.nextToken();
                value.append(curr);
                word.add(value.toString());
                value = new StringBuilder();

        }
        int id = Integer.parseInt(word.poll());
        String lastname = word.poll();
        String firstName = word.poll();
        String patronymic = word.poll();
        Date birthday = transformDate(word.poll());
        Date startWork = transformDate(word.poll());
        String type = word.poll();
        switch (type) {
            case "работник":
                int managerId = Integer.parseInt(word.poll());
                newEmployee = new Worker(id, lastname, firstName, patronymic, birthday, startWork, managerId);
                break;
            case "менеджер":
                List<Integer> workersId = new ArrayList<>();
                while (!word.isEmpty())
                    workersId.add(Integer.parseInt(word.poll()));
                newEmployee = new Manager(id, lastname, firstName, patronymic, birthday, startWork, workersId);
                break;
            case "другой":
                StringBuilder description = new StringBuilder();
                while (!word.isEmpty())
                    description.append(word.poll());
                newEmployee = new OtherWorker(id, lastname, firstName, patronymic, birthday, startWork, description.toString());
                break;
        }
        return newEmployee;
    }

    private Date transformDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }
}
