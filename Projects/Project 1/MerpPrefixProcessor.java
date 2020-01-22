package Processors;
import Nodes.BinaryOperatorNode;
import Nodes.MerpNode;
import Nodes.UnaryOperatorNode;

import java.util.ArrayList;

/**
 * Created by njg77 on 3/7/2017.
 */
public class MerpPrefixProcessor extends MerpProcessor {

    public MerpPrefixProcessor(){

    }

    /**Constructs and assigns a Merp tree from the provided list of MerpNode tokens using prefix notation
     *
     * @param tokens- list of MerpNodes used to create the pares tree
     */
    @Override
    public void constructTree(ArrayList<String> tokens) {
        if(tokens.size()==1){
            MerpNode node = createMerpNode(tokens.remove(0));
            tree = node;
        }else{
            MerpNode node = createMerpNode(tokens.get(0));
            tree = node;
            constructTreeHelper(tokens, tree);
        }

    }

    /**Helper to recursively construct the parse tree
     *
     * @param tokens- list of MerpNodes used to create the pares tree
     * @return current root of the parse tree
     */
    private void constructTreeHelper(ArrayList<String> tokens, MerpNode tree){
        if(tokens.size()>0) {
            MerpNode node = createMerpNode(tokens.remove(0));
            if (node.getNodeType()== MerpNode.NodeType.BinaryOperation) {
                ((BinaryOperatorNode)tree).setLeftChild(createMerpNode(tokens.get(0)));
                constructTreeHelper(tokens,node);
                node = createMerpNode(tokens.get(0));
                ((BinaryOperatorNode) tree).setRightChild(node);
                constructTreeHelper(tokens,node);
            } else if (node.getNodeType()== MerpNode.NodeType.UnaryOperation) {
                ((UnaryOperatorNode) tree).setChild(createMerpNode(tokens.get(0)));
                constructTreeHelper(tokens,node);
            }
        }
    }
}
