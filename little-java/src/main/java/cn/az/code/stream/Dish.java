package cn.az.code.stream;

/**
 * @author Liz
 * @date 2020/1/7
 */
public class Dish {

    private String name;
    private Boolean vegetable;
    private Integer calories;

    public Dish() {
    }

    public Dish(String name, Boolean vegetable, Integer calories) {
        this.name = name;
        this.vegetable = vegetable;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVegetable() {
        return vegetable;
    }

    public void setVegetable(Boolean vegetable) {
        this.vegetable = vegetable;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
