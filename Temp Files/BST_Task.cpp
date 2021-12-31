#include<iostream>
using namespace std;
struct TreeNode
{
     int value; //data 
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
      void destroySubTree(TreeNode *); //Recursively delete all tree nodes
      void deleteNode(int, TreeNode *&);
      void makeDeletion(TreeNode *&);
   
public:
      IntBinaryTree() //constructor	          
	  { 
	  root = NULL; 
	  }
	  
      ~IntBinaryTree() //destrctor            
	  { 
	   destroySubTree(root); 
	  }
	  	  
      void insertNode(int); //insert value
      
      void showNodesInOrder()
      {
      	displayInOrder(root); //print InOrder
	  }
	  
	  void showNodesPreOrder()     
	  { 
	  displayPreOrder(root);  //print PreOrder
	  }
	  
      void showNodesPostOrder()    
	  { 
	  displayPostOrder(root); //print PostOrder
	  }
	  bool find(int); // search node
	  void remove(int); // delete node
      void destroySubTree(int);
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

bool IntBinaryTree::find(int num)
{
   // The function starts from the root
   TreeNode *nodePtr = root;

   while (nodePtr) 
   {
      if (nodePtr->value == num)
         return true;  // value is found
      else if (num < nodePtr->value)
         nodePtr = nodePtr->left;
      else
         nodePtr = nodePtr->right;
   }
   return false; // value not found
}

void IntBinaryTree::remove(int num)
{
deleteNode(num, root); 
}  

void IntBinaryTree::deleteNode(int num, TreeNode *&nodePtr) 
{
    if (nodePtr == NULL) // node does not exist in the tree
        cout << num <<"not found.\n";     
    else if (num < nodePtr->value)
        deleteNode(num, nodePtr->left);  // find in left subtree    
    else if (num > nodePtr->value)
        deleteNode(num, nodePtr->right); // find in right subtree    
    else // num == nodePtr->value i.e. node is found
        makeDeletion(nodePtr); // actually deletes node from BST
}


void IntBinaryTree::makeDeletion(TreeNode *&nodePtr) 
{
   TreeNode *tempNodePtr; // Temperary pointer
   if (nodePtr->right == NULL) 
   { // case for leaf and one (left) child
      tempNodePtr = nodePtr;
      nodePtr = nodePtr->left; // Reattach the left child
      delete tempNodePtr;
   }
   else if (nodePtr->left == NULL) 
   { // case for one (right) child
      tempNodePtr = nodePtr;
      nodePtr = nodePtr->right; // Reattach the right child
      delete tempNodePtr;
   }
   else 
   {  // case for two children.
      tempNodePtr = nodePtr->right; // Move one node to the right
      while (tempNodePtr->left) 
	  { // Go to the extreme left node
         tempNodePtr = tempNodePtr->left;
      }
      tempNodePtr->left = nodePtr->left; // Reattach the left subtree
      tempNodePtr = nodePtr;
      nodePtr = nodePtr->right; // Reattach the right subtree
      delete tempNodePtr;
   }
}
void IntBinaryTree::destroySubTree(TreeNode *nodePtr)
{
	
if (nodePtr->left)
{
destroySubTree(nodePtr->left);
}

if (nodePtr->right)
{
destroySubTree(nodePtr->right);
}

delete nodePtr;
}

int main()
{
   IntBinaryTree tree;
   
   cout << "***********************\n";
   cout << "***(INSERTING NODES)***\n";
   cout << "***********************\n"<<endl;
   
   cout << "Here are the values in the tree:\n\n";

   
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
   
   cout << "\n***********************\n";
   cout << "***(DELETING NODES)***\n";
   cout << "***********************\n"<<endl;
   
   cout << "Deleting 8...\n";
   tree.remove(8);
   cout << "Deleting 12...\n";
   tree.remove(12);

   cout << "Displaying Nodes after Deletion:\n";
   cout << "Inorder traversal:\n";
   tree.showNodesInOrder();
   
   cout << "\n\n*********************\n";
   cout << "***(FINDING NODES)***\n";
   cout << "*********************";
   
   if (tree.find(3))
         cout << " \n3 is found in the tree.\n";
   else
         cout << " \n3 was not found in the tree.\n";
   
return 0;
}

