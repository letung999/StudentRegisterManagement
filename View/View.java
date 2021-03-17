package PracticeAfterLearn.Chuong2.Bai5.View;
import PracticeAfterLearn.Chuong2.Bai5.Controller.ControllerUtility;
import PracticeAfterLearn.Chuong2.Bai5.Controller.DataController;
import PracticeAfterLearn.Chuong2.Bai5.Model.SR;
import PracticeAfterLearn.Chuong2.Bai5.Model.Student;
import PracticeAfterLearn.Chuong2.Bai5.Model.Subject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var subjectFileName = "SUBJECT.DAT";
        var studentFileName = "STUDENT.DAT";
        var srmFileName = "SRM.DAT";
        var dataController = new DataController();
        var controllerUtility = new ControllerUtility();
        var subjects = new ArrayList<Subject>();
        var students = new ArrayList<Student>();
        var srms = new ArrayList<SR>();
        int option;
        do {
            System.out.println("*******************************MENU******************************");
            System.out.println("1.Add subject to file");
            System.out.println("2.show information Subject in File");
            System.out.println("3.Add Student to File");
            System.out.println("4.Show Information Student In File");
            System.out.println("5.Controller Information Student Register Management");
            System.out.println("6.Show Information in file srm");
            System.out.println("7.Delete Student In File");
            System.out.println("8.Sort and Search InFormation");
            System.out.println("9.Create list Student Register");
            System.out.println("0.exist");
            System.out.println("you choose!");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0: {
                    System.out.println("exist!");
                    break;
                }
                case 1: {
                    checkSubjectIDAscending(dataController, subjectFileName);
                    String subjectName, subjectType;
                    int totalLesson, tp;
                    System.out.println("Input subjectName");
                    subjectName = scanner.nextLine();
                    System.out.println("Input Total Lesson");
                    totalLesson = scanner.nextInt();
                    String[] types = {"General", "specializations base", "compulsory majors", "specialization options"};
                    do {
                        System.out.println("Input subject type");
                        System.out.println("1.General\n2.specializations base\n3.compulsory majors\n4.specialization options");
                        tp = scanner.nextInt();
                        if (tp < 1 || tp > 4) {
                            System.out.println("please choose types subject in list");
                        } else {
                            subjectType = types[tp - 1];
                            break;
                        }

                    } while (true);
                    Subject subject = new Subject(0, subjectName, totalLesson, subjectType);
                    dataController.writeSubjectToFile(subject, subjectFileName);
                    break;
                }
                case 2: {
                    subjects = dataController.readSubjectFromFile(subjectFileName);
                    System.out.println("*******************Subject Information******************");
                    showInformation(subjects);
                    break;
                }
                case 3: {
                    /**
                     *int studentID, String fullName, String address, String phoneNumber
                     */
                    checkStudentIDAscending(dataController, studentFileName);
                    String fullName, address, phoneNumber;
                    System.out.println("Input fullName");
                    fullName = scanner.nextLine();
                    System.out.println("Input Address");
                    address = scanner.nextLine();
                    System.out.println("Input phone Number");
                    do {
                        String regex1 = "\\(\\d{3}\\)\\d{6}";
                        phoneNumber = scanner.nextLine();
                        Pattern pattern = Pattern.compile(regex1);
                        Matcher matcher = pattern.matcher(phoneNumber);
                        if (matcher.find()) {
                            break;
                        } else {
                            System.out.println("please input with format true");
                        }
                    } while (true);
                    Student student = new Student(0, fullName, address, phoneNumber);
                    dataController.writeStudentToFile(student, studentFileName);
                    break;
                }
                case 4: {
                    System.out.println("********************Information Student In file********************");
                    students = dataController.readStudentFromFile(studentFileName);
                    showInformation(students);
                    break;
                }
                case 5: {
                    subjects = dataController.readSubjectFromFile(subjectFileName);
                    students = dataController.readStudentFromFile(studentFileName);
                    srms = dataController.readSRFromFile(srmFileName);
                    int studentID, subjectID, numOfRegister;
                    boolean allowRegister = false;
                    boolean allowSubject = false;
                    do {
                        System.out.println("*********************Information student In file*************************");
                        showInformation(students);
                        System.out.println("Input studentID, 0 to break");
                        studentID = scanner.nextInt();
                        if (studentID == 0) {
                            break;
                        }
                        allowRegister = checkRegiters(studentID, srms);
                        if (allowRegister == true) {
                            break;
                        } else {
                            System.out.println("over 3 subject to register for a student");
                        }

                    } while (true);
                    do {
                        System.out.println("*********************Information Subject in File****************************");
                        showInformation(subjects);
                        System.out.println("Input subjectID, input 0 to break");
                        subjectID = scanner.nextInt();
                        if (subjectID == 0) {
                            break;
                        }
                        allowSubject = checkSubjectSame(studentID, subjectID, srms);
                        if (allowSubject == true) {
                            break;

                        } else {
                            System.out.println("you registered, please choose other subject");
                        }
                    } while (true);
                    do {
                        System.out.println("Input num of Register");
                        numOfRegister = scanner.nextInt();
                        if(numOfRegister != 1){
                            System.out.println("only Allow A subject apply 1 times");
                        }
                        else {
                            break;
                        }
                    }while (true);
                    System.out.println("Input Time With format: xx/xx/xxxx");
                    String time;
                    Pattern pattern;
                    Matcher matcher;
                    do {
                        time = scanner.nextLine();
                        String regex = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
                        pattern = Pattern.compile(regex);
                        matcher = pattern.matcher(time);
                        if(matcher.find()){
                            break;
                        }
                    }while (!matcher.find());
                    Student currentStudent = getStudent(studentID, students);
                    Subject currentSubject = getSubject(subjectID, subjects);
                    SR sr = new SR(currentStudent, currentSubject,numOfRegister, 0,time);
                    srms = controllerUtility.upDateFile(sr, srms);
                    dataController.updateFile(srms, srmFileName);
                    showInformation(srms);
                    break;
                }
                case 6:{
                    srms = dataController.readSRFromFile(srmFileName);
                    System.out.println("***********************Information srm In File***************************");
                    showInformation(srms);
                    break;
                }
                case 7:{
                    int readerID, subjectID;
                    srms =  dataController.readSRFromFile(srmFileName);
                    System.out.println("***********************Information srm In File***************************");
                    showInformation(srms);
                    System.out.println("Input readerId to delete");
                    readerID = scanner.nextInt();
                    System.out.println("Input subjectID to delete");
                    subjectID = scanner.nextInt();
                    srms = controllerUtility.deleteStudentInFile(srms, readerID, subjectID);
                    dataController.updateFile(srms, srmFileName);
                    showInformation(srms);
                    break;
                }
                case 8:{
                    int choose;
                    var results = dataController.readSRFromFile(srmFileName);
                    do {
                        System.out.println("*************************MENU*************************");
                        System.out.println("1.Sort by Time");
                        System.out.println("2.Sort by Name");
                        System.out.println("3.Search Name Student");
                        System.out.println("4.Sort Total Register");
                        System.out.println("0.return main menu");
                        System.out.println("your option?");
                        choose = scanner.nextInt();
                        if(choose == 0){
                            break;
                        }
                        switch (choose){
                            case 1:{
                                controllerUtility.sortByTime(results);
                                showInformation(results);
                                break;
                            }
                            case 2:{
                                controllerUtility.sortByName(results);
                                showInformation(results);
                                break;
                            }
                            case 3:{
                                var result = new ArrayList<SR>();
                                String key;
                                System.out.println("input key to Search");
                                scanner.nextLine();
                                key = scanner.nextLine();
                                result = controllerUtility.searchByName(srms, key);
                                if (result.size() == 0){
                                    System.out.println("No Information to search");
                                }
                                else {
                                    showInformation(result);
                                }
                                break;
                            }
                            case 4:{
                                srms = dataController.readSRFromFile(srmFileName);
                                var resultTotal = new ArrayList<SR>();
                                resultTotal = controllerUtility.updateTotal(srms);
                                controllerUtility.sortByToTalRegister(srms);
                                showInformation(resultTotal);
                                break;

                            }
                        }
                    }while (choose != 0);
                    break;
                }
                case 9:{
                    System.out.println("**********************List Student Register*********************");
                    srms = dataController.readSRFromFile(srmFileName);
                    controllerUtility.sortByTime(srms);
                    ArrayList resultStudent;
                    resultStudent = controllerUtility.getStudent(srms);
                    Set list = new HashSet(resultStudent);
                    ArrayList<SR> resultName = new ArrayList<>(list);
                    showInformation(resultName);
                    break;
                }
                default: {
                    System.out.println("please choose option in menu");

                }
            }

        } while (option != 0);

    }

    private static Subject getSubject(int subjectID, ArrayList<Subject> subjects) {
        for (int i = 0; i < subjects.size(); ++i){
            if(subjects.get(i).getSubjectID() == subjectID){
                return subjects.get(i);
            }
        }
        return null;
    }

    private static Student getStudent(int studentID, ArrayList<Student> students) {
        for(int i = 0; i < students.size(); ++i){
            if(students.get(i).getStudentID() == studentID){
                return students.get(i);
            }
        }
        return null;
    }

    private static boolean checkSubjectSame(int studentID, int subjectID, ArrayList<SR> srms) {
        for (var x : srms) {
            if (x.getSubject().getSubjectID() == subjectID
                    && x.getStudent().getStudentID() == studentID
                    && x.getNumOfRegister() >= 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRegiters(int studentID, ArrayList<SR> srms) {
        int count = 0;
        for (var x : srms) {
            if (studentID == x.getStudent().getStudentID()) {
                count += x.getNumOfRegister();
            }
        }
        if (count >= 3) {
            return false;
        } else return true;
    }

    private static void checkStudentIDAscending(DataController dataController, String studentFileName) {
        var result = dataController.readStudentFromFile(studentFileName);
        if (result.size() == 0) {
            /**
             * do nothing
             */
        } else {
            Student.setId(result.get(result.size() - 1).getStudentID() + 1);
        }
    }

    private static void checkSubjectIDAscending(DataController dataController, String subjectFileName) {
        var result = dataController.readSubjectFromFile(subjectFileName);
        if (result.size() == 0) {
            /**
             * do nothing
             */
        } else {
            Subject.setId(result.get(result.size() - 1).getSubjectID() + 1);
        }
    }

    public static void showInformation(ArrayList list) {
        for (var x : list) {
            System.out.println(x);
        }
    }
}
