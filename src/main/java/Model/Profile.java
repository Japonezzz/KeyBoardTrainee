package Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Profile {
    private int id;
    private Double typeSpeed;
    private Double typeRhythm;
    private Byte mark;
    private int userId;
    private int levelId;
    private int difficultyId;
    private int languageId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TYPE_SPEED", nullable = true, precision = 0)
    public Double getTypeSpeed() {
        return typeSpeed;
    }

    public void setTypeSpeed(Double typeSpeed) {
        this.typeSpeed = typeSpeed;
    }

    @Basic
    @Column(name = "TYPE_RHYTHM", nullable = true, precision = 0)
    public Double getTypeRhythm() {
        return typeRhythm;
    }

    public void setTypeRhythm(Double typeRhythm) {
        this.typeRhythm = typeRhythm;
    }

    @Basic
    @Column(name = "mark", nullable = true)
    public Byte getMark() {
        return mark;
    }

    public void setMark(Byte mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "user_ID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "level_ID", nullable = false)
    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    @Basic
    @Column(name = "difficulty_ID", nullable = false)
    public int getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId = difficultyId;
    }

    @Basic
    @Column(name = "language_ID", nullable = false)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id &&
                userId == profile.userId &&
                levelId == profile.levelId &&
                difficultyId == profile.difficultyId &&
                languageId == profile.languageId &&
                Objects.equals(typeSpeed, profile.typeSpeed) &&
                Objects.equals(typeRhythm, profile.typeRhythm) &&
                Objects.equals(mark, profile.mark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, typeSpeed, typeRhythm, mark, userId, levelId, difficultyId, languageId);
    }
}
