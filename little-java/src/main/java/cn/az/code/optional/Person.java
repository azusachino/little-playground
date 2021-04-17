package cn.az.code.optional;

import java.util.Optional;

/**
 * @author Liz
 */
public class Person {

    private String name;
    private Car car;

    public Person() {
    }

    public Person(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static void main(String[] args) {
        Optional<Car> car = Optional.empty();
        car.ifPresent(System.out::println);
        Optional<Car> c = Optional.of(new Car());
        Optional<String> a = c.map(Car::getName);
    }

    public static String getInsuranceName(Person person) {

        return Optional.of(person)
                .flatMap(p -> Optional.of(p.getCar()))
                .flatMap(pc -> Optional.of(pc.getInsurance()))
                .map(Insurance::getName)
                .orElse("haha");
    }
}
