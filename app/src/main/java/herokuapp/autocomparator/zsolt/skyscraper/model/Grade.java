package herokuapp.autocomparator.zsolt.skyscraper.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Grade {

    @PrimaryKey(autoGenerate = true)
    private int gradeid;

    @ColumnInfo(name = "neptun")
    private String neptun;

    @ColumnInfo(name = "grade")
    private int grade;

    public Grade(String neptun, int grade) {
        this.neptun = neptun;
        this.grade = grade;
    }

    public int getGradeid() {
        return gradeid;
    }

    public void setGradeid(int gradeid) {
        this.gradeid = gradeid;
    }

    public String getNeptun() {
        return neptun;
    }

    public void setNeptun(String neptun) {
        this.neptun = neptun;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
