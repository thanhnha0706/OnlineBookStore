/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import java.util.List;

/**
 *
 * @author quyqu
 */
public class CategoryDAO extends BaseDAO{
    
    public CategoryDAO(Class classe) {
        super(classe);
    }
    
    public List<Category> findAll() {
        return (List<Category>)(Object)super.executeNamedQuery("Category.findAll");
    }

    public Category findById(String Id) {
        return (Category)super.findObjectById(Id);
    }

    public Category insert(Category category) {
        return (Category) super.insertObject(category);
    }

    public boolean update(Category category) {
        return super.updateObject(category);
    }

    public boolean delete(Category category) {
        return super.deleteObject(category);
    }
    
}
