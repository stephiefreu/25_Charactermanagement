package at.htlvillach.bll;

public class Character {
    private int id;
    private String name;
    private int age;
    private String hairColor;
    private String skinColor;
    private String shirtColor;
    private String trouserColor;

    public Character(int id, String name, int age, String hairColor, String skinColor, String shirtColor, String trouserColor) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.shirtColor = shirtColor;
        this.trouserColor = trouserColor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getShirtColor() {
        return shirtColor;
    }

    public String getTrouserColor() {
        return trouserColor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public void setShirtColor(String shirtColor) {
        this.shirtColor = shirtColor;
    }

    public void setTrouserColor(String trouserColor) {
        this.trouserColor = trouserColor;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", name, age);
    }
}