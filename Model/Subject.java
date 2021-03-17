package PracticeAfterLearn.Chuong2.Bai5.Model;

public class Subject {
    /**
     * subjectID, subjectName, totalLesson,
     * subjectType
     */
    private static int id = 1000000;
    private int subjectID;
    private String subjectName;
    private int totalLesson;
    private String subjectType;


    public Subject(int subjectID){
        this.subjectID = subjectID;
    }

    public Subject(int subjectID, String subjectName, int totalLesson, String subjectType) {
        if(subjectID == 0){
            this.subjectID = id;
        }
        else {
            this.subjectID = subjectID;
        }

        this.subjectName = subjectName;
        this.totalLesson = totalLesson;
        this.subjectType = subjectType;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Subject.id = id;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(int totalLesson) {
        this.totalLesson = totalLesson;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID=" + subjectID +
                ", subjectName='" + subjectName + '\'' +
                ", totalLesson=" + totalLesson +
                ", subjectType='" + subjectType + '\'' +
                '}';
    }
}
