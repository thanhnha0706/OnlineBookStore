/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.BaseDAO;
import models.Customer;
import java.util.Hashtable;
import java.util.List;

public class CustomerDAO extends BaseDAO{
    
    public CustomerDAO(Class classe) {
        super(classe);
    }
    
    public List<Customer> findAll() {
        return (List<Customer>)(Object)super.executeNamedQuery("Customer.findAll");
    }

    public Customer findById(String Id) {
        return (Customer)super.findObjectById(Id);
    }

    public Customer insert(Customer customer) {
        return (Customer) super.insertObject(customer);
    }

    public boolean update(Customer customer) {
        return super.updateObject(customer);
    }

    public boolean delete(Customer customer) {
        return super.deleteObject(customer);
    }
    
    
    public Customer authenticate(String email, String password) {
        Hashtable params = new Hashtable();
        params.put("email", email);
        params.put("password", password);
        
        Customer customer = (Customer) super.executeNamedQuery("Customer.authenticate", params);
        return customer;    
    }
}
