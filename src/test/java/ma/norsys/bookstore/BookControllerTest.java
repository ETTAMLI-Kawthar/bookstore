// package ma.norsys.bookstore;

// import static org.mockito.Mockito.when;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.ArrayList;
// import java.util.List;

// import ma.norsys.bookstore.entity.Book;
// import ma.norsys.bookstore.exception.BookNotFoundException;

// import ma.norsys.bookstore.service.BookServiceImpl;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = BookstoreApplication.class)
// @AutoConfigureMockMvc
// public class BookControllerTest {

// @MockBean
// BookServiceImpl bookService;

// @Autowired
// private MockMvc mockMvc;

// private Book firstBook, secondBook;

// @BeforeEach
// void init() {
// firstBook = new Book(1, "123-5444", "science_de_vie", "John", "science",
// "science");
// secondBook = new Book(2, "123679", "science_de_terre", "Been", "science",
// "science de terre");
// }

// @Test
// void givenNonExistentBookId_whenGetById_thenThrowsBookNotFoundException()
// throws Exception{
// when(bookService.getBookById(5)).thenThrow(new BookNotFoundException("Book
// Not Found"));

// this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", 5)
// .contentType(MediaType.APPLICATION_JSON))
// .andExpect(status().isNotFound());

// verify(bookService, times(1)).getBookById( 5);
// }

// @Test
// void givenExistentBookName_whenGetByName_thenStatus200() throws Exception {

// List<Book> listBook = new ArrayList<>();
// listBook.add(firstBook);
// when(bookService.getBookByName(firstBook.getName())).thenReturn(listBook);

// this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/search")
// .param("name", firstBook.getName())
// .param("category", "")
// .contentType(MediaType.APPLICATION_JSON))
// .andExpect(status().isOk());
// }

// @Test
// void givenExistentBookCategory_whenGetByCategory_thenStatus200() throws
// Exception {
// List<Book> listBook = new ArrayList<>();
// listBook.add(firstBook);
// listBook.add(secondBook);
// when(bookService.getBookByCategory(firstBook.getCategory())).thenReturn(listBook);

// this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/search")
// .param("name", "")
// .param("category", firstBook.getCategory())
// .contentType(MediaType.APPLICATION_JSON))
// .andExpect(status().isOk());
// }

// @Test
// void
// givenExistentBookNameAndCategory_whenGetByNameAndCategory_thenStatus200()
// throws Exception {

// List<Book> listBook = new ArrayList<>();
// listBook.add(firstBook);
// listBook.add(secondBook);

// when(bookService.getBookByNameAndCategory(firstBook.getName(),
// secondBook.getCategory())).thenReturn(listBook);

// this.mockMvc.perform(MockMvcRequestBuilders.get("/api/books/search")
// .param("name", firstBook.getName())
// .param("category", secondBook.getCategory())
// .contentType(MediaType.APPLICATION_JSON))
// .andExpect(status().isOk());
// }

// }
