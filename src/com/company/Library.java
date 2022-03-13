package com.company;

import java.util.ArrayList;
import java.util.List;

public class Library {
    String address;
    List<Book> books = new ArrayList<>();

    public Library(String address) {
        this.address = address;
    }

    public static void printOpeningHours() {
        System.out.println("9 AM to 5 PM");
    }

    public void printAddress() {
        System.out.println(this.address);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void borrowBook(String title) {
        Book foundBook = this.books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findAny()
                .orElse(null);

        if (foundBook == null) {
            System.out.printf("Sorry, \"%s\" is not in our catalog.\n", title);
        } else {
            if (foundBook.isBorrowed()) {
                System.out.printf("Sorry, \"%s\" is already borrowed.\n", title);
            } else {
                foundBook.borrowed();
                System.out.printf("You successfully borrowed \"%s\"\n", title);
            }
        }
    }

    public void returnBook(String title) {
        for (Book book : this.books) {
            if (book.getTitle().equals(title)) {
                book.returned();
                System.out.printf("You successfully returned \"%s\"\n", title);
                return;
            }
        }
    }

    public void printAvailableBooks() {
        if (this.books.isEmpty()) {
            System.out.println("No book in catalog");
            return;
        }
        for (Book book : this.books) {
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle());
            }
        }
    }

    public static void main(String[] args) {
        // Create two libraries
        Library firstLibrary = new Library("10 Main St.");
        Library secondLibrary = new Library("228 Liberty St.");

        // Add four books to the first library
        firstLibrary.addBook(new Book("The Da Vinci Code"));
        firstLibrary.addBook(new Book("Le Petit Prince"));
        firstLibrary.addBook(new Book("A Tale of Two Cities"));
        firstLibrary.addBook(new Book("The Lord of the Rings"));

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library addresses:");
        firstLibrary.printAddress();
        secondLibrary.printAddress();
        System.out.println();

        // Try to borrow The Lords of the Rings from both libraries
        System.out.println("Borrowing The Lord of the Rings:");
        firstLibrary.borrowBook("The Lord of the Rings");
        firstLibrary.borrowBook("The Lord of the Rings");
        secondLibrary.borrowBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
        System.out.println();
        System.out.println("Books available in the second library:");
        secondLibrary.printAvailableBooks();
        System.out.println();

        // Return The Lords of the Rings to the first library
        System.out.println("Returning The Lord of the Rings:");
        firstLibrary.returnBook("The Lord of the Rings");
        System.out.println();

        // Print the titles of available from the first library
        System.out.println("Books available in the first library:");
        firstLibrary.printAvailableBooks();
    }
} 