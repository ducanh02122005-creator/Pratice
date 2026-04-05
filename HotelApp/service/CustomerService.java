package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService instance;
    private final Map<String, Customer> customers = new HashMap<>();

    private CustomerService() {}

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        if (customers.containsKey(email.toLowerCase())) {
            throw new IllegalArgumentException("An account with email '" + email + "' already exists.");
        }
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email.toLowerCase(), customer);
    }

    public Customer getCustomer(String email) {
        return customers.get(email.toLowerCase());
    }

    public Collection<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
}
