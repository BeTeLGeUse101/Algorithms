package org.example;

import java.util.Random;
import java.util.Scanner;

class Node {
    int data;
    Node previous;
    Node next;

    public Node(int data) {
        this.data = data;
        this.previous = null;
        this.next = null;
    }
}

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random ran = new Random();

            while (true) {
                try {
                    System.out.print("Введите длину списка: ");
                    int length = scanner.nextInt();
                    DoublyLinkedList list = new DoublyLinkedList();

                    for (int i = 0; i < length; ++i) {
                        list.addNode(ran.nextInt(1, length * 2));
                    }

                    System.out.println("\nИсходный список:");
                    list.display();
                    list.revert();
                    System.out.println("Развёрнутый список:");
                    list.display();
                    break;
                } catch (Exception e) {
                    System.out.println("Ошибка ввода! Введите целое число.");
                    scanner.nextLine();
                }
            }
        }
    }
}