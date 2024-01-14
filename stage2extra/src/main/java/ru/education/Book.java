package ru.education;

public class Book {
    private CircularLinkedList<Reader> readers;
    private String title;

    public Book(String title) {
        this.title = title;
        this.readers = new CircularLinkedList<>();
    }

    // добавлять читателя (в запись формуляра_ в любое место списка (до или после какого-то читателя)
    public void addReader(Reader newReader, Reader existingReader) {
        readers.addAfter(newReader, existingReader);
    }

    // удалять любого читателя из формуляра
    public void removeReader(Reader reader) {
        readers.remove(reader);
    }

    // просматривать все записи формуляра
    public void displayReaders() {
        System.out.println("Читатель книги '" + title + "':");
        for (int i = 0; i < readers.getSize(); i++) {
            Reader reader = readers.get(i);
            System.out.println(reader.getName());
        }
    }

    // подсчет суммарного времени пользования каждой книгой
    public int getTotalTimeUsed() {
        int totalTimeUsed = 0;
        for (int i = 0; i < readers.getSize(); i++) {
            Reader reader = readers.get(i);
            totalTimeUsed += reader.getUsageTime();
        }
        return totalTimeUsed;
    }

    public String getTitle() {
        return title;
    }
}
