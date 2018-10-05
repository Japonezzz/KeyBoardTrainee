package Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Level {
    private int id;
    private String lvlFilePath;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LVL_FILE_PATH", nullable = false, length = 300)
    public String getLvlFilePath() {
        return lvlFilePath;
    }

    public void setLvlFilePath(String lvlFilePath) {
        this.lvlFilePath = lvlFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return id == level.id &&
                Objects.equals(lvlFilePath, level.lvlFilePath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, lvlFilePath);
    }
}
