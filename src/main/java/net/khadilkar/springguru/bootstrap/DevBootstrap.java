package net.khadilkar.springguru.bootstrap;

import net.khadilkar.springguru.model.Author;
import net.khadilkar.springguru.model.Book;
import net.khadilkar.springguru.model.Publisher;
import net.khadilkar.springguru.repository.AuthorRepository;
import net.khadilkar.springguru.repository.BookRepository;
import net.khadilkar.springguru.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Author1
        Author eric = new Author("Eric","Evans");
        Publisher harper = new Publisher("Harper Collins", "1 Lee Hwy Fairfax VA");
        Book ddd = new Book("Domain Driven Design", "1234", harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(harper);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Author 2
        Author rod = new Author("Rod","Johnson");
        Publisher worx = new Publisher("Worx", "2 Lee Hwy Fairfax VA");
        Book ejb = new Book("J2EE development without EJB", "2345", worx);
        rod.getBooks().add(ejb);
        ejb.getAuthors().add(rod);

        publisherRepository.save(worx);
        authorRepository.save(rod);
        bookRepository.save(ejb);
    }
}
