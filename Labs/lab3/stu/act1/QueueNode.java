package rit.stu.act1;

import rit.cs.Node;
import rit.cs.Queue;

/**
 * A queue implementation that uses a Node to represent the structure.
 * @param <T> The type of data the queue will hold
 * @author Sean Strout @ RIT CS
 * @author Nicholas Graca
 */
public class QueueNode<T> implements Queue<T> {
    /**
     * Create an empty queue.
     */
    public Node<T> head;
    public Node<T> back;
    public int size;

    //Constucts an empty queue
    public QueueNode() {
        head = new Node(null, null);
        size = 0;
        back = new Node(null, null);
    }

    @Override
    //returns the back of the queue
    public T back() {
        assert !empty();
       return back.getData();
    }

    @Override
    //removes and returns the head of the queue
    public T dequeue() {
        assert !empty();
        T hdata = head.getData();
        assert !empty();
        head = head.getNext();
        assert !empty();
        size--;
        return hdata;
    }

    @Override
    //Checks to see if the queue is empty
    public boolean empty() {
        return size == 0;
    }

    @Override
    //adds a new element to the queue
    public void enqueue(T element) {
        Node<T> newNode = new Node(element,null);
        if(empty()== true){
            head = newNode;
            head.setData(element);
        }
        else{
            back.setNext(newNode);
        }
        back = newNode;
        size++;
    }

    @Override
    //returns the head of the queue
    public T front() {
        assert !empty();
        return head.getData();

    }
}
