package com.example.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

public class ReverseLinkedList {
    public static void main(String[] args) {
        Node node5 = new Node(5,null);
        Node node4 = new Node(4,node5);
        Node node3 = new Node(3,node4);
        Node node2 = new Node(2,node3);
        Node node1 = new Node(1,node2);
        display(node1);
        //Node iteratenode = iterate(node1);
        //display(iteratenode);
        Node recusionnode = recusion(node1);
        display(recusionnode);


    }
    public static void display(Node head){
        while(head!=null){
            System.out.print(head.getValue()+"->");
            head = head.getNext();
        }
        System.out.println("-------------");
    }
    public static Node iterate(Node head){
        Node pre = null,next,curr;
        curr = head;
        while(curr!=null){
            next = curr.getNext();
            curr.setNext(pre);
            pre = curr;
            curr = next;
        }
        return pre;
    }
    public static Node recusion(Node head){
        if(head == null || head.getNext() == null){
            return head;
        }
        Node result = recusion(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return result;
    }
}
class Node{
    private int value;
    private Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
