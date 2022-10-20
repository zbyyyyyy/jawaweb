import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Arrays.sort;
public class StudentManager {
    public static ArrayList<Student> studentArrayList() {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream("D:\\students.txt"), "utf8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(isr);
        ArrayList<Student> ar = new ArrayList<Student>();
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] strArray1 = line.split("\t", 2);
            int index = line.indexOf("\t");
            Student s = new Student();
            s.setId(line.substring(0, index + 1));
            s.setName(line.substring(index + 1));
            ar.add(s);
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        //for (Student s : ar) {
        // System.out.println(String.format("姓名:%s学号:%s", s.getId(), s.getName()));
        //}
        return ar;
    }
    public static ArrayList<Student> studentGrades() throws IOException {
        String studentMath = abc.openFile("http://139.186.26.196/javaweb/data/math.txt");
        String studentChinese = abc.openFile("http://139.186.26.196/javaweb/data/english.txt");
        String studentEnglish = abc.openFile("http://139.186.26.196/javaweb/data/english.txt");
        ArrayList<Student> students = StudentManager.studentArrayList();
        ArrayList<Student> studentsMain1 = new ArrayList<>();
        ArrayList<Student> studentsMain2 = new ArrayList<>();
        ArrayList<Student> studentsMainFinnal = new ArrayList<>();
        ArrayList<Student> studentsMathGrades = new ArrayList<>();
        ArrayList<Student> studentsChineseGrades = new ArrayList<>();
        ArrayList<Student> studentsEnglishGrades = new ArrayList<>();
        String[] stuM = studentMath.split("\n");
        String[] stuC = studentChinese.split("\n");
        String[] stuE = studentEnglish.split("\n");
        for (String s : stuM) {
            int index = s.indexOf("\t");
            Student stu = new Student();
            stu.setId(s.substring(0, index + 1));
            stu.setMath(s.substring(index + 1));
            studentsMathGrades.add(stu);
        }
        for (Student temp1 : students) {
            for (Student temp2 : studentsMathGrades) {
                if (temp2.getId().equals(temp1.getId())) {
                    temp1.setMath(temp2.getMath());
                    studentsMain1.add(temp1);
                }
            }
        }
        // -------------
        for (String s : stuC) {
            int index = s.indexOf("\t");
            Student stu = new Student();
            stu.setId(s.substring(0, index + 1));
            stu.setChinese(s.substring(index + 1));
            studentsChineseGrades.add(stu);
        }
        for (Student temp1 : studentsMain1) {
            for (Student temp2 : studentsChineseGrades) {
                if (temp2.getId().equals(temp1.getId())) {
                    temp1.setChinese(temp2.getChinese());
                    studentsMain2.add(temp1);
                }
            }
        }
        //--------------
        for (String s : stuE) {
            int index = s.indexOf("\t");
            Student stu = new Student();
            stu.setId(s.substring(0, index + 1));
            stu.setEnglish(s.substring(index + 1));
            studentsEnglishGrades.add(stu);
        }
        for (Student temp1 : studentsMain2) {
            for (Student temp2 : studentsEnglishGrades) {
                if (temp2.getId().equals(temp1.getId())) {
                    temp1.setEnglish(temp2.getEnglish());
                    Integer avager = Avager(temp1);
                    temp1.setSum(avager * 3);
                    studentsMainFinnal.add(temp1);
                }
            }
        }
        //--------------
        return studentsMainFinnal;
    }
    public static Integer Avager(Student s) {
        return (Integer.valueOf(s.getChinese()) + Integer.valueOf(s.getMath()) + Integer.valueOf(s.getEnglish())) / 3;
    }
    public static void printAll() throws IOException {
        ArrayList<Student> students = StudentManager.studentGrades();
        for (Student s : students) {
            Integer avager = Avager(s);
            System.out.println(String.format("学号:%s,姓名:%s,数学成绩:%s,语文成绩:%s,英语成绩:%s,平均分:%d,总分:%d", s.getId(), s.getName(), s.getMath(), s.getChinese(), s.getEnglish(), avager, s.getSum()));
        }
    }
    public static void getStudentById(String id) throws IOException {
        ArrayList<Student> students = StudentManager.studentGrades();
        for (Student s : students) {
            if (s.getId().equals(id)) {
                Integer avager = Avager(s);
                System.out.println(String.format("学号:%s,姓名:%s,数学成绩:%s,语文成绩:%s,英语成绩:%s,平均分:%d,总分:%d", s.getId(), s.getName(), s.getMath(), s.getChinese(), s.getEnglish(), avager, avager * 3));
            }
        }
    }
    public static void findByScore(String classType, int min, int max) throws IOException {
        ArrayList<Student> studentArrayList = studentGrades();
        int num = 0;
        for (Student stu : studentArrayList) {
            if (classType.equals("English") || classType.equals("english")) {
                num = Integer.parseInt(stu.getEnglish());
            } else if (classType.equals("Chinese") || classType.equals("chinese")) {
                num = Integer.parseInt(stu.getChinese());
            } else if (classType.equals("Math") || classType.equals("chinese")) {
                num = Integer.parseInt(stu.getMath());
            } else {
                System.out.println("print error");
            }
            if (min < num && num < max) {
                Integer avager = Avager(stu);
                System.out.println(String.format("学号:%s,姓名:%s,数学成绩:%s,语文成绩:%s,英语成绩:%s,平均分:%d,总分:%d", stu.getId(), stu.getName(), stu.getMath(), stu.getChinese(), stu.getEnglish(), avager, avager * 3));
            }
        }
    }
    public static void sortByTotal() throws IOException {
        ArrayList<Student> students = StudentManager.studentGrades();
        Collections.sort(students);
        for (Student stu : students) {
            Integer avager = Avager(stu);
            System.out.println(String.format("学号:%s,姓名:%s,数学成绩:%s,语文成绩:%s,英语成绩:%s,平均分:%d,总分:%d", stu.getId(), stu.getName(), stu.getMath(), stu.getChinese(), stu.getEnglish(), avager, stu.getSum()));
        }
    }
}






