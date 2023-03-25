package ma.norsys.bookstore;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ma.norsys.bookstore.entity.Book;
import ma.norsys.bookstore.repository.BookRepository;
import ma.norsys.bookstore.service.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void should_return_list_of_books() {
        List<Book> expectedBooks = List.of(new Book(1, "12-543", "Java", "ben", "info", "informatique"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> books = bookService.getAllBook();

        assertThat(books).hasSize(1);
        assertThat(books).extracting("name").containsOnly("Java");
        assertThat(books).extracting("ISBN").containsOnly("12-543");
        assertThat(books).extracting("category").containsOnly("info");
        assertThat(books).extracting("author").containsOnly("ben");
        verify(bookRepository).findAll();
    }

    @Test
    void should_be_able_to_save_one_book() throws Exception {
        Book book = new Book();
        book.setName("Docker");

        bookService.saveBook(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        assertThat(bookArgumentCaptor.getValue())
                .extracting("name")
                .isEqualTo("Docker");
    }

}