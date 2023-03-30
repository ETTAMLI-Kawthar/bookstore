package ma.norsys.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.exception.BookNotFoundException;
import ma.norsys.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with the id " + id));
    }

    @Override
    public List<Book> getBookByName(String name) {
        return bookRepository.findBookByNameContaining(name);
    }

    @Override
    public List<Book> getBookByCategory(String category) {
        return bookRepository.findBookByCategoryContaining(category);
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findBookByAuthorContaining(author);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

}
