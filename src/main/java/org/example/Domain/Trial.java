package org.example.Domain;

import java.util.Objects;

public abstract class Trial {

    protected int Id;
    private SEX Sex;
    protected category Category;

    public Trial(int id, SEX sex, category category) {
        Id = id;
        Sex = sex;
        Category = category;
    }

    public Trial() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public SEX getSex() {
        return Sex;
    }

    public void setSex(SEX sex) {
        Sex = sex;
    }

    public category getCategory() {
        return Category;
    }

    public void setCategory(category category) {
        Category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trial trial = (Trial) o;
        return Id == trial.Id && Sex == trial.Sex && Category == trial.Category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Sex, Category);
    }

    @Override
    public String toString() {
        return "Trial{" +
                "Id=" + Id +
                ", Sex=" + Sex +
                ", Category=" + Category +
                '}';
    }
}
