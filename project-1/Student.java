public class Student implements Comparable<Student> {
    private String id;
    private String name;
    private String math;
    private String chinese;
    private String english;
    private int sum;
    public Student() {
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public String getMath() {
        return math;
    }
    public void setMath(String math) {
        this.math = math;
    }
    public String getChinese() {
        return chinese;
    }
    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
    public String getEnglish() {
        return english;
    }
    public void setEnglish(String english) {
        this.english = english;
    }
    public void setSum(int sum){
        this.sum=sum;
    }
    public int getSum(){
        return this.sum;
    }
    public int compareTo(Student o) {
        if(o.sum==this.sum) {
            return this.name.compareTo(o.name);
        }
        return o.sum-this.sum;
    }
    public String toString() {
        return String.format("学号: %s,姓名: %s,数学成绩: %s,语文成绩: %s,英语成绩: %s","平均分:%d","总分:%d",
                this.getId(), this.getName(), this.getMath(), this.getChinese(), this.getEnglish(),this.getSum()/3,this.getSum());
    }
}
