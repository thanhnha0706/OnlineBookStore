/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Staff;
import java.util.List;

/**
 *
 * @author quyqu
 */
public class StaffDAO extends BaseDAO {
    
    public StaffDAO(Class classe) {
        super(classe);
    }
    
    public List<Staff> findAll() {
        return (List<Staff>)(Object)super.executeNamedQuery("Staff.findAll");
    }

    public Staff findById(String Id) {
        return (Staff)super.findObjectById(Id);
    }

    public Staff insert(Staff staff) {
        return (Staff) super.insertObject(staff);
    }

    public boolean update(Staff staff) {
        return super.updateObject(staff);
    }

    public boolean delete(Staff staff) {
        return super.deleteObject(staff);
    }
}
