package Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Difficulty {
    private int id;
    private String diffName;
    private int errorLimit;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DIFF_NAME", nullable = false, length = 45)
    public String getDiffName() {
        return diffName;
    }

    public void setDiffName(String diffName) {
        this.diffName = diffName;
    }

    @Basic
    @Column(name = "ERROR_LIMIT", nullable = false)
    public int getErrorLimit() {
        return errorLimit;
    }

    public void setErrorLimit(int errorLimit) {
        this.errorLimit = errorLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Difficulty that = (Difficulty) o;
        return id == that.id &&
                errorLimit == that.errorLimit &&
                Objects.equals(diffName, that.diffName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, diffName, errorLimit);
    }
}
