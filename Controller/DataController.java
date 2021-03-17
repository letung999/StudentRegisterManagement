package PracticeAfterLearn.Chuong2.Bai5.Controller;

import PracticeAfterLearn.Chuong2.Bai5.Model.SR;
import PracticeAfterLearn.Chuong2.Bai5.Model.Student;
import PracticeAfterLearn.Chuong2.Bai5.Model.Subject;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    /**
     * write
     */
    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterWrite(String fileName) {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeStudentToFile(Student student, String fileName) {
        /**
         * int studentID, String fullName, String address, String phoneNumber
         */
        openFileToWrite(fileName);
        printWriter.println(student.getStudentID() + "|" + student.getFullName() + "|" + student.getAddress() + "|" + student.getPhoneNumber());
        closeFileAfterWrite(fileName);
    }

    public void writeSubjectToFile(Subject subject, String fileName) {
        /**
         * int subjectID, String subjectName, int totalLesson, String subjectType
         */
        openFileToWrite(fileName);
        printWriter.println(subject.getSubjectID() + "|" + subject.getSubjectName() + "|" + subject.getTotalLesson() + "|" + subject.getSubjectType());
        closeFileAfterWrite(fileName);
    }

    public void writeSRMToFile(SR sr, String fileName) {
        /**
         * Student student, Subject subject, int numOfRegister, int totalOfRegister
         */
        openFileToWrite(fileName);
        printWriter.println(sr.getStudent().getStudentID() + "|" + sr.getSubject().getSubjectID() + "|" +
                sr.getNumOfRegister()+"|"+sr.getTimeRegister());
        closeFileAfterWrite(fileName);
    }

    /**
     * readFile
     */
    public void openFileToRead(String fileName) {
        try {
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterRead(String fileName) {
        scanner.close();
    }

    public ArrayList<Subject> readSubjectFromFile(String fileName) {
        var subjects = new ArrayList<Subject>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Subject subject = convertDataFromSubject(data);
            subjects.add(subject);
        }
        closeFileAfterRead(fileName);
        return subjects;
    }

    public ArrayList<Student> readStudentFromFile(String fileName) {
        var students = new ArrayList<Student>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Student student = convertDataFromStudent(data);
            students.add(student);
        }
        closeFileAfterRead(fileName);
        return students;
    }

    public ArrayList<SR> readSRFromFile(String fileName) {
        var students =  readStudentFromFile("STUDENT.DAT");
        var subjects = readSubjectFromFile("SUBJECT.DAT");
        var srs = new ArrayList<SR>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            SR sr = convertDataFromSR(data, students, subjects);
            srs.add(sr);
        }
        closeFileAfterRead(fileName);
        return srs;
    }

    private SR convertDataFromSR(String data, ArrayList<Student> students, ArrayList<Subject> subjects) {

        String[] datas = data.split("\\|");
        SR sr = new SR(getStudent(Integer.parseInt(datas[0]), students),
                getSubject(Integer.parseInt(datas[1]), subjects),
                Integer.parseInt(datas[2]), 0, datas[3]);
        return sr;

    }

    private Student convertDataFromStudent(String data) {
        /**
         * int studentID, String fullName, String address, String phoneNumber
         */

        String[] datas = data.split("\\|");
        Student student = new Student(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3]);
        return student;
    }

    private Subject convertDataFromSubject(String data) {
        /**
         * int subjectID, String subjectName, int totalLesson, String subjectType
         */
        String[] datas = data.split("\\|");
        Subject subject = new Subject(Integer.parseInt(datas[0]), datas[1], Integer.parseInt(datas[2]), datas[3]);
        return subject;
    }

    public void updateFile(ArrayList<SR> srms, String srmFileName) {
        File file = new File(srmFileName);
        if(file.exists()){
            file.delete();
        }
        openFileToWrite(srmFileName);
        for (var sr: srms){
            printWriter.println(sr.getStudent().getStudentID() + "|" + sr.getSubject().getSubjectID() + "|" + sr.getNumOfRegister() + "|"+ sr.getTimeRegister());
        }
        closeFileAfterWrite(srmFileName);
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
}
