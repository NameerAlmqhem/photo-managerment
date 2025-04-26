/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructrue;

/**
 *
 * @author nmyralmqhm
 */
public class LinkedList<T> {
     class Node<T> {
	public T data;
	public Node<T> next;
	public Node (T val) {
		data = val;
		next = null;
	}
        }

        private Node<T> head;
        private Node<T> current;
        int size; 
        
        public LinkedList () {
                head = current = null;
                size = 0;
        }
        public boolean empty () {
                return head == null;
        }
        public boolean last () {
                return current.next == null;
        }
        public boolean full () {
                return false;
        }
        public void findFirst () {
                current = head;
        }
        public void findNext () {
                current = current.next;
        }
        public T retrieve () {
                return current.data;
        }
        public void update (T val) {
                current.data = val;
        }
        public void insert (T val) {
                Node<T> tmp;
                if (empty()) {
                        current = head = new Node<T> (val);
                }
                else {
                        tmp = current.next;
                        current.next = new Node<T> (val);
                        current = current.next;
                        current.next = tmp;
                }
                size ++;
        }
        public void remove () {
                if (current == head) {
                        head = head.next;
                }
                else {
                        Node<T> tmp = head;
                        while (tmp.next != current)
                                tmp = tmp.next;
                        tmp.next = current.next;
                }
                if (current.next == null)
                        current = head;
                else
                        current = current.next;
                size--;
        }
        
        public int getSize()
        {
            return size;
        }
        
}
