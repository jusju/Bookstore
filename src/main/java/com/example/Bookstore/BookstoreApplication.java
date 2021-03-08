package com.example.Bookstore;

import com.example.Bookstore.domain.*;
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
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository categoryRepository, UserRepository userRepository){
		return args -> {
			Log.info("Saving some books");
			categoryRepository.save(new Category("Romance"));
			categoryRepository.save(new Category("Crime"));
			categoryRepository.save(new Category("Drama"));
			repository.save(new Book("War and Peace", "Leo Tolstoy", "979-8551169109", 2020, 9.99, categoryRepository.findCategoryByName("Romance").get(0)));
			userRepository.deleteAll();
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@gmail.com");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@gmail.com");
			userRepository.save(user1);
			userRepository.save(user2);

			Log.info("fetch all books");
			for(Book book: repository.findAll()){
				Log.info(book.toString());
			}
		};
	}
}
