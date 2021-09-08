package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("kastanjelaan", "Eindhoven", "Noord Brabant", "5616LH");
        publisher.setName("Fikas Publisher");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book dde = new Book("Domain Driven Entites", "234-5454-656");

        ttt(publisher, eric, dde);
        Author rod = new Author("Rodd", "Jhonson");
        Book its = new Book("Intro To Spring", "234-5454-333");

        ttt(publisher, rod, its);

        System.out.println("number of books" + bookRepository.count() + " " + publisherRepository.count() );

    }

    private void ttt(Publisher publisher, Author author, Book book) {
        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);
        publisherRepository.save(publisher);


    }
}
