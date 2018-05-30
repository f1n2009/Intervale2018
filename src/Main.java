import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private List<Employee> allWorkers;

    private Main(String file) throws IOException {
        allWorkers = new ArrayList<>();
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

            case "sortbylastname":
                //sortByLastName((ArrayList) allWorkers);
                System.out.println("Список отсортирован по фамилиям!");
                break;

            case "sortbydate":
                //sortByDate((ArrayList) allWorkers);
                System.out.println("Список отсортирован по дате приема на работу!");
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
        for (int i = 0; i < allWorkers.size(); i++){
            if (allWorkers.get(i).getId()==id)
                allWorkers.remove(i);
        }

    }

    private void addWorker() {
        this.readerWriter("new_employees.txt");}

    //public void changeTypeWorker(Employee someWorker) {}

    //public void changeManager(Employee someWorker) {}

    //private void sortByLastName(ArrayList arrayList) {}

    //private void sortByDate(ArrayList arrayList) {}

    private void save(){
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

    private void readerWriter(String file) {
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
