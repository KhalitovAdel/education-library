package ru.education;

public class DynamicArrayQueue<E> {
    private E[] elements;
    private int size;
    private int capacity;
    private int head;
    private int tail;

    public DynamicArrayQueue() {
        capacity = 10; // начальная емкость массива
        elements = (E[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(E element) {
        if (size == capacity) {
            resizeArray();
        }
        elements[tail] = element;
        tail = (tail + 1) % capacity; // используем остаток от деления для циклического сдвига
        size++;
    }

    public E dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Очередь пустая");
        }
        E element = elements[head];
        elements[head] = null; // для избегания утечек памяти
        head = (head + 1) % capacity; // использование остатка от деления для циклического сдвига
        size--;
        return element;
    }

    private void resizeArray() {
        int newCapacity = capacity * 2;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(head + i) % capacity]; // копируем элементы с учетом циклического сдвига
        }
        elements = newElements;
        capacity = newCapacity;
        head = 0;
        tail = size;
    }

    public E getElement(int i) {
        return elements[i];
    }
}
