package PracticeAfterLearn.Chuong2.Bai5.Model;

public class SR {
    private Student student;
    private Subject subject;
    private int numOfRegister;
    private int totalOfRegister;
    private String timeRegister;

    public SR(Student student, Subject subject, int numOfRegister, int totalOfRegister, String timeRegister) {
        this.student = student;
        this.subject = subject;
        this.numOfRegister = numOfRegister;
        this.totalOfRegister = totalOfRegister;
        this.timeRegister = timeRegister;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getNumOfRegister() {
        return numOfRegister;
    }

    public void setNumOfRegister(int numOfRegister) {
        this.numOfRegister = numOfRegister;
    }

    public int getTotalOfRegister() {
        return totalOfRegister;
    }

    public void setTotalOfRegister(int totalOfRegister) {
        this.totalOfRegister = totalOfRegister;
    }

    public String getTimeRegister() {
        return timeRegister;
    }

    public void setTimeRegister(String timeRegister) {
        this.timeRegister = timeRegister;
    }

    @Override
    public String toString() {
        return "SR{" +
                "studentID=" + student.getStudentID() +
                ", studentName=" + student.getFullName() +
                ", subjectID=" + subject.getSubjectID() +
                ", subjectName=" + subject.getSubjectName() +
                ", numOfRegister=" + numOfRegister +
                ", totalOfRegister=" + totalOfRegister +
                ", timeRegister='" + timeRegister + '\'' +
                '}';
    }
}
