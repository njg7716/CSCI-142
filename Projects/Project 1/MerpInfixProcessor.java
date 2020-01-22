package Processors;
import Nodes.BinaryOperatorNode;
import Nodes.MerpNode;
import Nodes.UnaryOperatorNode;
import java.util.*;

import static Nodes.MerpNode.NodeType.BinaryOperation;

/**
 * Created by njg77 on 3/7/2017.
 */
public class MerpInfixProcessor extends MerpProcessor {

    public MerpInfixProcessor(){

    }

    /**Constructs and assigns a Merp tree from the provided list of MerpNode tokens using infix notation
     *
     * @param tokens list of MerpNodes used to create the pares tree
     */
    @Override
    public void constructTree(ArrayList<String> tokens) {
        if(tokens.size()==1){
            MerpNode root = createMerpNode(tokens.remove(0));
            tree = root;
        }else if(tokens.size()<4) {
            int size = tokens.size();
            int rootnum = (size - 1) / 2;
            MerpNode root = createMerpNode(tokens.get(rootnum));
            tree = root;
            processStack(tokens);
        }else{
            int size = tokens.size();
            int rootnum = (size - 1) / 2;
            MerpNode root = createMerpNode(tokens.get(rootnum-1));
            tree = root;
            processStack(tokens);
        }
    }

    /**Processes an stack of MerpNodes to create a Merp Parse Tree
     *
     */
    private void processStack(ArrayList<String> tokens){
        int x=0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("-",2);map.put("+",2);map.put("*",1);map.put("/",1);map.put("^",0);map.put(">",4);
        map.put(">=",4);map.put("<",4);map.put("<=",4);map.put("==",4);map.put("!=",4);
        map.put("@",0);map.put("_",1);map.put("|",1);
        Stack<String> stack = new Stack();
        Queue<String> queue = new LinkedList<String>();
        while( x != tokens.size()){
            String node = tokens.remove(x);
            if (node.equals("+")||node.equals("-")||node.equals("*")||node.equals("/")||node.equals("^")||node.equals("@")||node.equals(">")||node.equals(">=")||
                    node.equals("<")||node.equals("<=")||node.equals("_") ||node.equals("==")||node.equals("|")||node.equals("!=")) {
                if (stack.empty()) {
                    stack.add(node);
                }else if(map.get(node)<=map.get(stack.peek())){
                    stack.add(node);
                }else{
                    queue.add(node);
                }
            }else{
                queue.add(node);
            }
        }
        while(stack.empty()!=true) {
            queue.add(stack.pop());
        }
        int y=0;
        while(y!= queue.size()){
            tokens.add(queue.remove());
        }
        MPostProc(tokens);
    }

    /**Uses the same process as Postfix to construct the tree and sets the tree root.
     *
     * @param tokens
     */
    public void MPostProc(ArrayList<String> tokens){
        if(tokens.size()==1){
            MerpNode node = createMerpNode(tokens.remove(0));
        }
        else{
            Stack<MerpNode> stack = new Stack<>();
            this.MPostProcHelper(tokens,stack);
        }
    }

    /**Helps contruct the tree
     *
     * @param tokens list of MerpNodes used to create the pares tree
     * @param stack used to hold operators when constructing the tree
     */
    public void MPostProcHelper(ArrayList<String> tokens, Stack<MerpNode> stack){
        if(tokens.size()>0){
            MerpNode node = createMerpNode(tokens.remove(0));
            if (node.getNodeType() == MerpNode.NodeType.Variable || node.getNodeType() == MerpNode.NodeType.Constant) {
                stack.push(node);
            } else if (node.getNodeType() == MerpNode.NodeType.BinaryOperation) {
                if(stack.size()>0) {
                    ((BinaryOperatorNode) node).setRightChild(stack.pop());
                    if(stack.size()>0) {
                        ((BinaryOperatorNode) node).setLeftChild(stack.pop());
                    }
                    stack.push(node);
                    tree = node;
                    this.MPostProcHelper(tokens,stack);
                }
            } else if (node.getNodeType() == MerpNode.NodeType.UnaryOperation) {
                if(stack.size()>0) {
                    ((UnaryOperatorNode) tree).setChild(stack.pop());
                    this.MPostProcHelper(tokens,stack);
                }
                this.MPostProcHelper(tokens,stack);
            }
            this.MPostProcHelper(tokens,stack);
        }
    }
}
