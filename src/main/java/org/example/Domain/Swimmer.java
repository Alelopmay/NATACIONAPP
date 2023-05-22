package org.example.Domain;

public class Swimmer {

    private int Cod_Swimmer;
    private  String Name;
    private  String Last_Name;
    private  String Category;
    private int Age;
    private  SEX Sex;



    public Swimmer(int codSwimmer, String name, String lastName, int age, SEX sex, String category) {
        this.Cod_Swimmer = codSwimmer;
        this.Name = name;
        this.Last_Name = lastName;
        this.Category = calculateCategory(age);
        this.Age = age;
        this.Sex = sex;
    }

    public Swimmer() {
        super();
    }



    public int getCod_Swimmer() {
        return Cod_Swimmer;
    }

    public void setCod_Swimmer(int cod_Swimmer) {
        Cod_Swimmer = cod_Swimmer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public SEX getSex() {
        return Sex;
    }

    public void setSex(SEX sex) {
        Sex = sex;
    }

    @Override
    public String toString() {
        return "swimmer{" +
                "Cod_Swimmer=" + Cod_Swimmer +
                ", Name='" + Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Category='" + Category + '\'' +
                ", Age=" + Age +
                ", Sex=" + Sex +
                '}';
    }

    public String calculateCategory(int age) {
        String category;
        if (age <= 6) {
            category = "Pre-Benjamin";
        } else if (age <= 8) {
            category = "Benjamin";
        } else if (age <= 10) {
            category = "Alevin";
        } else if (age <= 12) {
            category = "Infantil";
        } else if (age <= 14) {
            category = "Juvenil";
        } else if (age <= 17) {
            category = "Junior";
        } else {
            category = "Senior";
        }
        return category;
    }
}

