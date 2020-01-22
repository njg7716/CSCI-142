package Processors;

import Nodes.MerpNode;
import Nodes.UnaryOperatorNode;
import Nodes.VariableNode;
import Nodes.BinaryOperatorNode;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by njg77 on 3/7/2017.
 */
public class MerpPostfixProcessor extends MerpProcessor {

    public MerpPostfixProcessor(){

    }

    /**Constructs and assigns a Merp tree from the provided list of MerpNode tokens using postfix notation
     *
     * @param tokens- list of MerpNodes used to create the pares tree
     */
    @Override
    public void constructTree(ArrayList<String> tokens) {
        if(tokens.size()==1){
            MerpNode node = createMerpNode(tokens.remove(0));
            tree = node;
        }
        else{
            MerpNode node = createMerpNode(tokens.get(tokens.size()-1));
            tree = node;
            Stack<MerpNode> stack = new Stack<>();
            this.constructTreeHepler(tokens,stack);
        }

    }

    /**Helper to recursively construct the parse tree
     *
     * @param tokens- list of MerpNodes to process
     *
     */
    private void constructTreeHepler(ArrayList<String> tokens,Stack<MerpNode> stack) {
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
                    this.constructTreeHepler(tokens,stack);
                }
            } else if (node.getNodeType() == MerpNode.NodeType.UnaryOperation) {
                if(stack.size()>0) {
                    ((UnaryOperatorNode) tree).setChild(stack.pop());
                    this.constructTreeHepler(tokens,stack);
                }
                this.constructTreeHepler(tokens,stack);
            }
            this.constructTreeHepler(tokens,stack);
        }
    }
}
