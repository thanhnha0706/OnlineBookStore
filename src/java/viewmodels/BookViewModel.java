/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodels;

import dao.BookDAO;
import models.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.annotation.Init;

public class BookViewModel implements Serializable {
    
    private final BookDAO dao = new BookDAO(Book.class);
    
    // data for the view
    List<Book> books;
    
    // return list of books
    public List<Book> getBooks() {        
        return books;
    }
            
    @Init
    public void init() {
        
        // retrieve books
        books = dao.findAll();

    }
}
