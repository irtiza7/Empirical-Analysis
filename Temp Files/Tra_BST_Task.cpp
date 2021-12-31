#include<iostream>
using namespace std;
struct TreeNode
{
     int value;
     TreeNode *left;
     TreeNode *right;
};

class IntBinaryTree 
{
   private:
      TreeNode *root;  // Pointer to the root of BST
      void displayInOrder(TreeNode *);
      void displayPreOrder(TreeNode *);
      void displayPostOrder(TreeNode *);
   
public:
      IntBinaryTree()	          
	  { 
	  root = NULL; 
	  }
	  
      ~IntBinaryTree()             
	  { 
	  //destroySubTree(root); 
	  }	  
      void insertNode(int);
      
      void showNodesInOrder()
      {
      	displayInOrder(root);
	  }
	  
	  void showNodesPreOrder()     
	  { 
	  displayPreOrder(root);  
	  }
	  
      void showNodesPostOrder()    
	  { 
	  displayPostOrder(root); 
	  }
      
};

void IntBinaryTree::insertNode(int num) 
{
   TreeNode *newNode, *nodePtr;  // Pointer to create new node & traverse tree
   
   newNode = new TreeNode; // Create a new node
   newNode->value = num;
   newNode->left = newNode->right = NULL;
   
   if (!root)  root = newNode; // If tree is empty.
   else // Tree is not empty
   { 
      nodePtr = root; 
      while (nodePtr != NULL) 
	  {      
         if (num < nodePtr->value) 
		 { // Left subtree
            if (nodePtr->left)  
			{ 
			nodePtr = nodePtr->left; 
			}
            else 
			{ 
			nodePtr->left = newNode; 
			break;  
			}
         }
         else if (num > nodePtr->value) 
		 {  // Right subtree
            if (nodePtr->right)    
			nodePtr = nodePtr->right;
            else  
			{  
			nodePtr->right = newNode;   
			break; 
			}
         }
         else 
		 {  
		 cout << "Duplicate value found in tree.\n"; 
		 break; 
		 }
      }
   }
}

void IntBinaryTree::displayInOrder(TreeNode *root)  
{
	if(root != NULL)
	{
		displayInOrder(root->left);
		cout<<root->value<<"  ";
		displayInOrder(root->right);
	}
}

void IntBinaryTree::displayPreOrder(TreeNode *root)  
{
	if(root != NULL)
	{
		cout<<root->value<<"  ";
		displayPreOrder(root->left);
		displayPreOrder(root->right);
	}
}

void IntBinaryTree::displayPostOrder(TreeNode *root)  
{
	if(root != NULL)
	{
		displayPostOrder(root->left);
		displayPostOrder(root->right);
		cout<<root->value<<"  ";
	}
}



int main()
{
   IntBinaryTree tree;
   
   cout << "***********************\n";
   cout << "***(INSERTING NODES)***\n";
   cout << "***********************\n"<<endl;
   
   tree.insertNode(5);
   tree.insertNode(8);
   tree.insertNode(3);
   tree.insertNode(12);
   tree.insertNode(9);
   
   cout << "Inorder traversal:\n";
   tree.showNodesInOrder();
   cout << " \nPreorder traversal:\n";
   tree.showNodesPreOrder();
   cout << " \nPostorder traversal:\n";
   tree.showNodesPostOrder();
   
return 0;
}

