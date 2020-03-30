package cn.az.code.optional;

import lombok.Data;

import java.util.Optional;

/**
 * @author Liz
 * @date 1/9/2020
 */
@Data
public class Person {

    private String name;
    private Optional<Car> car;


    public static void main(String[] args) {
        Optional<Car> car = Optional.empty();
        car.ifPresent(System.out::println);
        Optional<Car> c = Optional.of(new Car());
        Optional<String> a = c.map(Car::getName);
    }

    public static String getInsuranceName(Person person) {

        return Optional.of(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("haha");
    }
}
