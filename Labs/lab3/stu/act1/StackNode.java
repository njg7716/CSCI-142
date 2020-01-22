package rit.stu.act1;
import rit.cs.Node;
import rit.cs.Stack;

/**
 * A stack implementation that uses a Node to represent the structure.
 * @param <T> The type of data the stack will hold
 * @author Sean Strout @ RIT CS
 * @author Nicholas Graca
 */
public class StackNode<T> implements Stack<T> {

    /**
     * Create an empty stack.
     */
    private Node<T> head;

    //Constructs an empty stack
    public StackNode() {
        head = new Node(null, null);
    }

    @Override
    //Checks to see if the stack is empty
    public boolean empty() {
        return top() == null;
    }

    @Override
    //removes the head of the stack
    public T pop() {
        T hdata = head.getData();
        assert !empty();
        if(head.getNext() == null){
            head.setData(null);
        }
        else {
            head = head.getNext();
        }
        return hdata;
    }

    @Override
    //adds a new element to the stack
    public void push(T element) {
        head = new Node<T>(element,head);
    }

    @Override
    //returns the head of the stack
    public T top() {
        return head.getData();
    }
}
