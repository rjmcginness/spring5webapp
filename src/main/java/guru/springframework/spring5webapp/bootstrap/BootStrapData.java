package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
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

    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author herman = new Author("Herman", "Melville");
        Book moby = new Book("Moby Dick", "984999098");

        herman.getBooks().add(moby);
        moby.getAuthors().add(herman);

        Author rod = new Author("Rod", "Johnson");
        Book j2ee = new Book("J2EE Development without EJB", "978673728");

        rod.getBooks().add(j2ee);
        j2ee.getAuthors().add(rod);

        Publisher pub = new Publisher("Wiley Interscience",
                                        "21 Causeway St",
                                        "Boston",
                                        "Massachusetts",
                                        "01225");

        this.authorRepository.save(rod);
        this.authorRepository.save(herman);
        this.bookRepository.save(j2ee);
        this.bookRepository.save(moby);
        pub.getBooks().add(moby);
        pub.getBooks().add(j2ee);
        this.publisherRepository.save(pub);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books: " + this.bookRepository.count());
        System.out.println("Publishers: " + this.publisherRepository.count());
    }
}
