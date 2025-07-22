package com.personal.artbyirushishopapi.service;

import com.personal.artbyirushishopapi.dtos.RegisterCustomerRequest;
import com.personal.artbyirushishopapi.entities.Customer;
import com.personal.artbyirushishopapi.repositories.CustomerRepository;
import com.personal.artbyirushishopapi.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<CustomerResponse> createCustomer(RegisterCustomerRequest customerDto){

        try{
            // validate the input, sanitize the input
            Map<String, String> errorMap = validateCustomer(customerDto);
            if(!errorMap.isEmpty()){
                return new ResponseEntity<>(new CustomerResponse(400, "Failure", "Customer validation failed", errorMap), HttpStatus.BAD_REQUEST);
            }
            //if there are validation errors, return error response
            //if not continue -> save the customer and return the saved customer response

            // if existing customer, return existing customer
            if (customerRepository.existsByEmail(customerDto.getEmail())) {
                Customer existingCustomer = customerRepository.findByEmail(customerDto.getEmail());
                customerDto.setName(existingCustomer.getName());
                customerDto.setEmail(existingCustomer.getEmail());
                customerDto.setAddress(existingCustomer.getAddress());
                customerDto.setPhone(existingCustomer.getPhone());
                customerDto.setDob(existingCustomer.getDob());

                return new ResponseEntity<>(new CustomerResponse(200, "Success", "Customer already exists", customerDto), HttpStatus.OK);
            }
            else{ // else new customer
                Customer customer = new Customer();
                customer.setName(customerDto.getName());
                customer.setEmail(customerDto.getEmail());
                customer.setAddress(customerDto.getAddress());
                customer.setDob(customerDto.getDob());
                customer.setPhone(customerDto.getPhone());

                customerRepository.save(customer);
                return new ResponseEntity<>(new CustomerResponse(200, "Success", "Customer created", customerDto),HttpStatus.CREATED);
            }
           } catch (Exception e) {
            return new ResponseEntity<>(new CustomerResponse(500, "Failure", "Customer create failed",
                    Map.of("service-error", "Internal server error")),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private Map<String, String> validateCustomer(RegisterCustomerRequest customerDto){
        Map<String, String> errorMap = new HashMap<>(); //liscow principle - refer
        //  refactor the validation method
        // 1. handleMandatoryValidations
        // 2. handleFormatValidations
        handleMandatoryValidations(customerDto, errorMap);
        handleFormatValidations(customerDto, errorMap);

        // Research about Spring Validator, how to incorporate it into this implementations and improve them.
        return errorMap;
    }

    private void handleFormatValidations(RegisterCustomerRequest customerDto, Map<String, String> errorMap) {
        int age = LocalDate.now().getYear() - customerDto.getDob().getYear();
        if(age <= 18 || age >= 120)
            errorMap.put("age", "Age must be between 18 and 120");

        //  validate length of name(3 min), address(3 min)
        if(customerDto.getName().length() < 3)
            errorMap.put("name", "Name too short");

        if(customerDto.getAddress().length() < 3)
            errorMap.put("address", "Address too short");

        //  validate email format (@, . include)
        String emailRegex =  "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(customerDto.getEmail()).matches())
            errorMap.put("email", "Invalid email");

        //  validate phone number (+ allowed, numbers allowed, letters not allowed)
        String phoneRegex = "^[0-9+]{12}$";
        Pattern patternPhone = Pattern.compile(phoneRegex);
        if (!patternPhone.matcher(customerDto.getPhone()).matches())
            errorMap.put("phone", "Invalid phone number");
    }

    private void handleMandatoryValidations(RegisterCustomerRequest customerDto, Map<String, String> errorMap) {
        //mandatory validations
        if(customerDto.getName() == null || customerDto.getName().isEmpty())
            errorMap.put("name", "Name is mandatory");
        if(customerDto.getDob() == null)
            errorMap.put("dob", "Dob is mandatory");
        if(customerDto.getAddress() == null || customerDto.getAddress().isEmpty())
            errorMap.put("address", "Address is mandatory");
        if(customerDto.getPhone() == null || customerDto.getPhone().isEmpty())
            errorMap.put("phone", "Phone is mandatory");
        if(customerDto.getEmail() == null || customerDto.getEmail().isEmpty())
            errorMap.put("email", "Email is mandatory");
    }
}
