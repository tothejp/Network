package cloud.client;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.*;
 
public class Tree extends JPanel{
    TreeArea TreeArea;
    JTextArea textArea;
    final static String newline = "\n";
    
    public Tree() {
        super(new GridLayout(1,2,1,2));
        TreeArea = new TreeArea();
        add(TreeArea);
        textArea = new JTextArea(30,35);
        textArea.setEditable(false);
        textArea.append("Printout Contents");
        add(new JScrollPane(textArea)); 
    }
 

    class TreeArea extends JScrollPane {
        JTree tree;     
        public TreeArea() {
            TreeNode rootNode = createNodes();
            tree = new JTree(rootNode);
            setViewportView(tree);
        }
        private DefaultMutableTreeNode newNodes(String[] sp){
            DefaultMutableTreeNode Node;        	
            Node = new DefaultMutableTreeNode(sp[0]);
            return Node;
        }
        
        private TreeNode createNodes() {
            DefaultMutableTreeNode root;
            DefaultMutableTreeNode grandparent;
            DefaultMutableTreeNode parent;
            DefaultMutableTreeNode child;

            root = new DefaultMutableTreeNode("Root Server");

            String filename = "test";
            String path = "root/first/second/third";
//            String[] sp = path.split("/");
            String[] sp = { "First", "Second", "Third"};
            /*
            Find first node on root
            	if not
            		make new node
            	if exist
            		find second node on first node           
            */
           
            
            DefaultMutableTreeNode newNode1, newNode2, newNode3;
              	newNode1 = new DefaultMutableTreeNode(sp[0]);
            	newNode2 = new DefaultMutableTreeNode(sp[1]);
            	newNode3 = new DefaultMutableTreeNode(sp[2]);
            	newNode2.add(newNode3);
               	newNode1.add(newNode2);
            	root.add(newNode1);
            	root.add(newNodes(sp));
            /* 
            grandparent = new DefaultMutableTreeNode("Potrero Hill");
            root.add(grandparent);
            //
            parent = new DefaultMutableTreeNode("Restaurants");
            grandparent.add(parent);
            child = new DefaultMutableTreeNode("Thai Barbeque");
            parent.add(child);
            child = new DefaultMutableTreeNode("Goat Hill Pizza");
            parent.add(child);
            //
            parent = new DefaultMutableTreeNode("Grocery Stores");
            grandparent.add(parent);
            child = new DefaultMutableTreeNode("Good Life Grocery");
            parent.add(child);
            child = new DefaultMutableTreeNode("Safeway");
            parent.add(child);
             
            grandparent = new DefaultMutableTreeNode("Noe Valley");
            root.add(grandparent);
            //
            parent = new DefaultMutableTreeNode("Restaurants");
            grandparent.add(parent);
            child = new DefaultMutableTreeNode("Hamano Sushi");
            parent.add(child);
            child = new DefaultMutableTreeNode("Hahn's Hibachi");
            parent.add(child);
            //
            parent = new DefaultMutableTreeNode("Grocery Stores");
            grandparent.add(parent);
            child = new DefaultMutableTreeNode("Real Foods");
            parent.add(child);
            child = new DefaultMutableTreeNode("Bell Market");
            parent.add(child);
  */           
            return root;
        }
/*    
        public Dimension getMinimumSize() {
            return minSize;
        }
 
        public Dimension getPreferredSize() {
            return minSize;
        }*/
    }
}
