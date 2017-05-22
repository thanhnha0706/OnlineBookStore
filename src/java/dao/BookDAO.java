/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Book;
import java.util.List;

/**
 *
 * @author quyqu
 */
public class BookDAO extends BaseDAO {
    
    public BookDAO(Class classe) {
        super(classe);
    }
    
    public List<Book> findAll() {
        return (List<Book>)(Object)super.executeNamedQuery("Book.findAll");
    }

    public Book findById(int Id) {
        return (Book)super.findObjectById(Id);
    }

    public Book insert(Book book) {
        return (Book) super.insertObject(book);
    }

    public boolean update(Book book) {
        return super.updateObject(book);
    }

    public boolean delete(Book book) {
        return super.deleteObject(book);
    }
    
}