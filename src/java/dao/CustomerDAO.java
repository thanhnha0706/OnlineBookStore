/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.BaseDAO;
import entity.Customer;
import java.util.List;

/**
 *
 * @author quyqu
 */
public class CustomerDAO extends BaseDAO{
    
    public CustomerDAO(Class classe) {
        super(classe);
    }
    
    public List<Customer> findAll() {
        return (List<Customer>)(Object)super.executeNamedQuery("Customer.findAll");
    }

    public Customer findById(int Id) {
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
}
