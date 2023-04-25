package com.example.demo.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CircularLinkedList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1,null);
        ListNode node2 = new ListNode(2,node1);
        node1.next = node2;
        System.out.println(hasCycle(node1));

    }
    public static boolean hasCycle(ListNode head){
        Set seen = new HashSet();
        while(head !=null){
            if(!seen.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
class ListNode{
    int value;
    ListNode next;

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}
