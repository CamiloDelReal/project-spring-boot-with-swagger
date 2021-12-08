package com.example.springbootwithswagger.repositories;

import com.example.springbootwithswagger.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class CustomerRepository {
    private final List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>() {{
           add(new Customer(
                   1,
                   "John", "Doe",
                   "johndoe@mail.com"
           ));
           add(new Customer(
                   2,
                   "Jane", "Doe",
                   "janedoe@mail.com"
           ));
        }};
    }

    public List<Customer> getAll() {
        return customers;
    }

    public Optional<Customer> getById(Integer id) {
        return customers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
    }

    public Customer create(Customer customer) {
        Integer max = customers.stream().mapToInt(Customer::getId).max().orElse(1);
        customer.setId(max + 1);
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> edit(Integer id, Customer customer) {
        Optional<Customer> customerInCache = customers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
        customerInCache.ifPresent(value -> value.set(customer));
        return customerInCache;
    }

    public boolean delete(Integer id) {
        Optional<Customer> customerInCache = customers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
        AtomicBoolean success = new AtomicBoolean(false);
        customerInCache.ifPresent(value -> {
            customers.remove(value);
            success.set(true);
        });
        return success.get();
    }
}
