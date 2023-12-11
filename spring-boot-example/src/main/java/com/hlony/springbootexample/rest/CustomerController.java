package com.hlony.springbootexample.rest;

import com.hlony.springbootexample.model.Customer;
import com.hlony.springbootexample.service.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age){}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable(name = "customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable(name = "customerId") Integer id, @RequestParam(name = "name") String name){
        Customer customer = customerRepository.getReferenceById(id);
        customer.setName(name);
        customerRepository.save(customer);
    }
}
