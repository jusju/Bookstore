package com.example.Bookstore;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger Log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository){
		return args -> {
			Log.info("Saving some books");
			repository.save(new Book("War and Peace", "Leo Tolstoy", "979-8551169109", 2020, 9.99));
			Log.info("fetch all books");
			for(Book book: repository.findAll()){
				Log.info(book.toString());
			}
		};
	}
}
