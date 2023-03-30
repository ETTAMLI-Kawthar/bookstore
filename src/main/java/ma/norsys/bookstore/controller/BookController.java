package ma.norsys.bookstore.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBooks() {
        List<Book> books = bookService.getAllBook();
        return ResponseEntity.ok().body(books);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book saveBook = bookService.saveBook(book);
        return ResponseEntity.ok().body(saveBook);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> getBookbyId(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        book.setId(id);
        Book updateBook = bookService.updateBook(book);
        return ResponseEntity.ok().body(updateBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookbyId(@PathVariable("id") int id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBookByName(@PathVariable("name") String name) {
        if (name != "" && name != null) {
            return bookService.getBookByName(name);
        } else if (name.isEmpty() || name == " " || name == null) {
            return bookService.getAllBook();
        }
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBookByCategory(@PathVariable("category") String category) {
        return ResponseEntity.ok().body(bookService.getBookByCategory(category));
    }

    @GetMapping("/author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getBookByAuthor(@PathVariable("author") String author) {
        return ResponseEntity.ok().body(bookService.getBookByAuthor(author));
    }
    // @GetMapping("/search")
    // public Set<BookDto> searchBook(@RequestParam(value = "name") String name,
    // @RequestParam(value = "categories") String categories)
    // {
    // Set<BookDto> searchedBooks=new HashSet<>();
    // if(name!=null && categories!=null)
    // {
    // for(String category: categories.split(","))
    // {
    // searchedBooks.addAll(bookService.searchByNameByCategory(name,category));
    // }
    // return searchedBooks;
    // }
    // else if(name!=null){
    // searchedBooks.addAll(bookService.searchByName(name));
    // return searchedBooks;
    // }
    // else if(categories!=null)
    // {
    // for(String category: categories.split(","))
    // {
    // searchedBooks.addAll(bookService.searchByCategory(category));
    // }
    // return searchedBooks;
    // }
    // return Collections.EMPTY_SET;
    //     }
}
