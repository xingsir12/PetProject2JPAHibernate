package ru.xing.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xing.springcourse.models.Book;
import ru.xing.springcourse.models.Person;
import ru.xing.springcourse.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortedByYear) {
        if (sortedByYear) {
            return booksRepository.findAll(Sort.by("year"));
        }
        return booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage,boolean sortedByYear) {
        if (sortedByYear) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        }
        else {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }
    }

    public Book findOne(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public List<Book> searchByTitle(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());

        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Person getOwnerBook(int id) {
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    //Освобождает книгу
    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> {
           book.setOwner(null);
           book.setTakenAt(null);
        });
    }

    //Назначает человеку книгу
    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(book -> {
           book.setOwner(selectedPerson);
           book.setTakenAt(new Date());
        });
    }
}
