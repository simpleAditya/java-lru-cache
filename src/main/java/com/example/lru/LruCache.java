package com.example.lru;

import java.util.HashMap;
import java.util.Map;

public class LruCache<T> {
    private final int capacity;
    private int size;
    private final Map<String, Node> cache;
    private final DoublyLinkedList internalQueue;

    public LruCache(final int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.internalQueue = new DoublyLinkedList();
        this.size = 0;
    }

    public T get(final String key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        internalQueue.moveNodeToFront(node);
        return cache.get(key).value;
    }

    public void put(final String key, final T value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            internalQueue.moveNodeToFront(node);
        }
        if (capacity == size) {
            String rearKey = internalQueue.getRearKey();
            internalQueue.removeNodeFromRear();
            cache.remove(rearKey);
            size--;
        }
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        internalQueue.addNodeToFront(newNode);
        size++;
    }

    class Node {
        String key;
        T value;
        Node next, prev;

        public Node(final String key, final T value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    class DoublyLinkedList {
        Node front, rear;
        public DoublyLinkedList() {
            front = rear = null;
        }

        public void addNodeToFront(final Node node) {
            if (rear == null) {
                front = rear = node;
                return;
            }
            node.next = front;
            front.prev = node;
            front = node;
        }

        public void moveNodeToFront(final Node node) {
            if (front == node) {
                return;
            }
            if (node == rear) {
                rear = rear.prev;
                rear.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            node.prev = null;
            node.next = front;
            front.prev = node;
            front = node;
        }

        public void removeNodeFromRear() {
            if (rear == null) {
                return;
            }
            System.out.println("Deleting key : " + rear.key);
            if (front == rear) {
                front = rear = null;
            } else {
                rear = rear.prev;
                rear.next = null;
            }
        }

        public String getRearKey() {
            return rear.key;
        }
    }
}