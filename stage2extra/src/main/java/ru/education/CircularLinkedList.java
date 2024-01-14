package ru.education;

public class CircularLinkedList<E> {
    private Node<E> tail;
    private int size;

    public CircularLinkedList() {
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (tail == null) {
            tail = newNode;
            tail.next = tail; // делаем единственный узел циклическим
        } else {
            newNode.next = tail.next; // новый узел указывает на начало списка
            tail.next = newNode; // предыдущий последний узел теперь указывает на новый узел
            tail = newNode; // обновляем указатель на последний узел
        }
        size++;
    }

    public E remove(Reader reader) {
        if (isEmpty()) {
            throw new IllegalStateException("Список пуст");
        }
        Node<E> head = tail.next;
        if (size == 1) {
            tail = null;
        } else {
            tail.next = head.next; // переприсваиваем указатель последнего узла на второй узел
        }
        size--;
        return head.item;
    }

    public void addAfter(E newReader, E existingReader) {
    }

    public E get(int i) {
        return null;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
