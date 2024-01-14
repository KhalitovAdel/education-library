package ru.education;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

public class CustomList<T> implements Iterable<T> {
    @Getter
    private Node<T> head;
    private int size;

    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node<>(data));
        }
        size++;
    }

    public Iterator<T> iterator() {
        return new CustomListIterator<T>(head);
    }

    @Getter @Setter
    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private static class CustomListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public CustomListIterator(Node<T> head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}