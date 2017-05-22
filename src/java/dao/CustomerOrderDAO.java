/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.CustomerOrder;
import java.util.List;

/**
 *
 * @author quyqu
 */
public class CustomerOrderDAO extends BaseDAO{
    
    public CustomerOrderDAO(Class classe) {
        super(classe);
    }
    
    public List<CustomerOrder> findAll() {
        return (List<CustomerOrder>)(Object)super.executeNamedQuery("CustomerOrder.findAll");
    }

    public CustomerOrder findById(int Id) {
        return (CustomerOrder)super.findObjectById(Id);
    }

    public CustomerOrder insert(CustomerOrder customerOrder) {
        return (CustomerOrder) super.insertObject(customerOrder);
    }

    public boolean update(CustomerOrder customerOrder) {
        return super.updateObject(customerOrder);
    }

    public boolean delete(CustomerOrder customerOrder) {
        return super.deleteObject(customerOrder);
    }
}
