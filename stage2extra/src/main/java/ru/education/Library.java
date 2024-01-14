package ru.education;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private DynamicArrayQueue<Book> books;

    public Library() {
        this.books = new DynamicArrayQueue<>();
    }

    // добавлять книги
    public void addBook(Book book) {
        books.enqueue(book);
    }

    // удалять книги
    public Book removeBook() {
        return books.dequeue();
    }

    // просматривать все книги
    public void displayBooks() {
        System.out.println("Книги в библиотеке:");
        for (int i = 0; i < books.getSize(); i++) {
            Book book = books.getElement(i);
            System.out.println(book.getTitle());
        }
    }

    public Map<String, Integer> getBookUsageSummary() {
        Map<String, Integer> bookUsageSummary = new HashMap<>();
        for (int i = 0; i < books.getSize(); i++) {
            Book book = books.getElement(i);
            int totalTimeUsed = book.getTotalTimeUsed();
            bookUsageSummary.put(book.getTitle(), totalTimeUsed);
        }
        return bookUsageSummary;
    }

}
