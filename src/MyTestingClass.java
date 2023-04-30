import java.util.Arrays;

public class MyTestingClass {
    private String password;
    private int[] grades;
    private int hashCode; // saving result from hashCode() method to avoid repeat computing of it

    public MyTestingClass() {
    }


    public MyTestingClass(String password, int[] grades) {
        this.password = password;
        this.grades = grades;
    }

    @Override
    public int hashCode() { // computing hash code with String's hashCode implementation and 31x + y method for integer array
        int result = hashCode;
        if (hashCode == 0) {
            result = 47 * result + ((password == null) ? 0 : password.hashCode());
            for (int i = 0; i < grades.length; i++) {
                result = 47 * result + grades[i];
            }
            hashCode = result;
        }
        return result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int[] getGrades() {
        return grades;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "MyTestingClass{" +
                "password='" + password + '\'' +
                ", grades=" + Arrays.toString(grades) +
                ", hashCode=" + hashCode +
                '}';
    }
}
