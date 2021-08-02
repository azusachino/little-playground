package cn.az.webflux.mongo.repo;

import cn.az.webflux.entity.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * TODO
 *
 * @author az
 * @since 2021/8/2 23:18
 */
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {

    @Query("{ 'name': ?0 }")
    Flux<Employee> findByName(String name);
}
