package PracticeAfterLearn.Chuong2.Bai5.Controller;

import PracticeAfterLearn.Chuong2.Bai5.Model.SR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControllerUtility {
    public ArrayList<SR> upDateFile(SR sr, ArrayList<SR> srms) {
        boolean isUpdate = false;
        for (int i = 0; i < srms.size(); ++i) {
            if (sr.getStudent().getStudentID() == srms.get(i).getStudent().getStudentID()
                    && sr.getSubject().getSubjectID() == srms.get(i).getSubject().getSubjectID()) {
                isUpdate = true;
                srms.set(i, sr);

            }
        }
        if (isUpdate == false) {
            srms.add(sr);
        }
        return srms;
    }

    public ArrayList<SR> deleteStudentInFile(ArrayList<SR> srms, int studentID, int subjectID) {
        for (int i = 0; i < srms.size(); ++i) {
            if (studentID == srms.get(i).getStudent().getStudentID() &&
                    subjectID == srms.get(i).getSubject().getSubjectID()) {
                srms.remove(i);
            }
        }
        return srms;
    }

    public void sortByTime(ArrayList<SR> results) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < results.size(); ++i) {
            for (int j = results.size() - 1; j > i; j--) {
                SR bj = results.get(j);
                SR bjj = results.get(j - 1);
                try {
                    Date time1 = simpleDateFormat.parse(bj.getTimeRegister());
                    Date time2 = simpleDateFormat.parse(bjj.getTimeRegister());
                    long resTime1 = time1.getTime();
                    long resTime2 = time2.getTime();
                    if (resTime2 > resTime1) {
                        results.set(j, bjj);
                        results.set(j - 1, bj);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void sortByName(ArrayList<SR> results) {
        for (int i = 0; i < results.size(); ++i) {
            for (int j = results.size() - 1; j > i; j--) {
                SR bj = results.get(j);
                SR bjj = results.get(j - 1);
                if (bjj.getStudent().getFullName().
                        substring(bjj.getStudent().getFullName().lastIndexOf(" ")).compareTo
                        (bj.getStudent().getFullName().
                                substring(bj.getStudent().getFullName().lastIndexOf(" "))) > 0) {
                    results.set(j, bjj);
                    results.set(j - 1, bj);
                }

            }
        }
    }

    public ArrayList<SR> searchByName(ArrayList<SR> results, String key) {
        var resultName = new ArrayList<SR>();
        String regex = ".*" + key.toLowerCase() + ".*";
        for (int i = 0; i < results.size(); ++i) {
            if (results.get(i).getStudent().getFullName().toLowerCase().matches(regex)) {
                resultName.add(results.get(i));
            }
        }
        return resultName;
    }

    public ArrayList<SR> updateTotal(ArrayList<SR> srms) {
        for (int i = 0; i < srms.size(); ++i) {
            int count = 0;
            for (int j = 0; j < srms.size(); ++j) {
                if (srms.get(i).getStudent().getStudentID() == srms.get(j).getStudent().getStudentID()) {
                    count += srms.get(j).getNumOfRegister();
                    srms.get(i).setTotalOfRegister(count);
                    srms.set(i, srms.get(i));
                }
            }
        }
        return srms;
    }

    public void sortByToTalRegister(ArrayList<SR> results) {
        for (int i = 0; i < results.size(); ++i) {
            for (int j = results.size() - 1; j > i; j--) {
                SR bj = results.get(j);
                SR bjj = results.get(j - 1);
                if (bj.getTotalOfRegister() < bjj.getTotalOfRegister()) {
                    results.set(j, bjj);
                    results.set(j - 1, bj);
                }

            }
        }
    }

    public ArrayList getStudent(ArrayList<SR> srms) {
        ArrayList results = new ArrayList<>();
        for (int i = 0; i < srms.size(); ++i) {
            results.add(srms.get(i).getStudent().getFullName());
        }
        return results;
    }
}
