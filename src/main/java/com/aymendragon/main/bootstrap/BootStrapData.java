package com.aymendragon.main.bootstrap;

import com.aymendragon.main.domain.Author;
import com.aymendragon.main.domain.Book;
import com.aymendragon.main.domain.Publisher;
import com.aymendragon.main.repositories.AuthorRepository;
import com.aymendragon.main.repositories.BookRepository;
import com.aymendragon.main.repositories.PublisherRepository;
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

    System.out.println("Started in Bootstrap");

    Publisher publisher =new Publisher();
    publisher.setName("SFG Publishing");
    publisher.setCity("St petersburg");
    publisher.setState("FL");

    publisherRepository.save(publisher);

    System.out.println("Publisher Count:" + publisherRepository.count());

    Author eric = new Author("Eric","Evans");
    Book ddd = new Book("Domain Driven Design","123123");

    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    ddd.setPublisher(publisher);
    publisher.getBooks().add(ddd);

    authorRepository.save(eric);
    bookRepository.save(ddd);
    publisherRepository.save(publisher);

    Author rod = new Author("Rod","Jonnson");
    Book noEJB = new Book("J2EE Development without EJB","1864148465");

    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    noEJB.setPublisher(publisher);
    publisher.getBooks().add(noEJB);

    authorRepository.save(rod);
    bookRepository.save(noEJB);
    publisherRepository.save(publisher);

    System.out.println("Started in Bootstrap");
    System.out.println("Number of Books:"+ bookRepository.count());
    System.out.println("Publisher Number of Books:" + publisher.getBooks().size());

  }
}
