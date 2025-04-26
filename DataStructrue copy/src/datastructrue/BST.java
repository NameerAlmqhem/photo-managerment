/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructrue;

/**
 *
 * @author nmyralmqhm
 */
public class BST<T> {
    public class BSTNode <T> {
            public String key;
            public T data;
            public BSTNode<T> left, right;

            /** Creates a new instance of BSTNode */
            public BSTNode(String k, T val) {
                    key = k;
                    data = val;
                    left = right = null;
            }

            public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {
                    key = k;
                    data = val;
                    left = l;
                    right = r;
            }
        }

        BSTNode<T> root, current;
        String AllKeys;
        
        /** Creates a new instance of BST */
        public BST() {
                root = current = null;
        }

        public boolean empty() {
                return root == null;
        }

        public boolean full() {
                return false;
        }

        public T retrieve () {
                return current.data;
        }

        public boolean findkey(String  tkey) {
                BSTNode<T> p = root;
                BSTNode<T> q = root;

                if(empty())
                        return false;

                while(p != null) {
                        q = p;
                        if(p.key.compareToIgnoreCase(tkey) == 0) {
                                current = p;
                                return true;
                        }
                        else if(tkey.compareToIgnoreCase(p.key) < 0 )
                                p = p.left;
                        else
                                p = p.right;
                }
                current = q;
                return false;
        }

        public boolean insert(String k, T val) {
                BSTNode<T> p;
                BSTNode<T> q = current;

                if(findkey(k)) {
                        current = q;  // findkey() modified current
                        return false; // key already in the BST
                }

                p = new BSTNode<T>(k, val);
                if (empty()) {
                        root = current = p;
                        return true;
                }
                else {
                        // current is pointing to parent of the new key
                        if (k.compareToIgnoreCase(current.key) < 0)
                                current.left = p;
                        else
                                current.right = p;
                        current = p;
                        return true;
                }
        }

        public boolean remove_key (String tkey){
                Boolean removed = new Boolean(false);
                BSTNode<T> p;
                p = remove_aux(tkey, root, removed);
                current = root = p;
                return removed;
        }

        private BSTNode<T> remove_aux(String  key, BSTNode<T> p, boolean flag) {
                BSTNode<T> q, child = null;
                if(p == null)
                        return null;
                if(key.compareToIgnoreCase(p.key) <0)
                        p.left = remove_aux(key, p.left, flag); //go left
                else if(key.compareToIgnoreCase(p.key) > 0)
                        p.right = remove_aux(key, p.right, flag); //go right
                else {
                        flag = true;
                        if (p.left != null && p.right != null){ //two children
                                q = find_min(p.right);
                                p.key = q.key;
                                p.data = q.data;
                                p.right = remove_aux(q.key, p.right, flag);
                        }
                else {
                                if (p.right == null) //one child
                                        child = p.left;
                                else if (p.left == null) //one child
                                        child = p.right;
                                return child;
                        }
                }
                return p;
        }

        private BSTNode<T> find_min(BSTNode<T> p)
        {
                if(p == null)
                        return null;

                while(p.left != null){
                        p = p.left;
                }

                return p;
        }

        public boolean update(String key, T data)
        {
                    remove_key(current.key);
                    return insert(key, data);
        }

        //Method removeKey: iterative  
        public boolean removeKey(String k) {
            // Search 
            String  k1 = k;      
            BSTNode<T> p = root;      
            BSTNode<T> q = null;    // Parent of p

            while (p != null) 
            {
                if (k1.compareToIgnoreCase(p.key) < 0) 
                {
                    q =p;
                    p = p.left;
                }
                else if (k1.compareToIgnoreCase(p.key) >0) 
                {
                    q = p;
                    p = p.right;
                } 
                else { 
                    // Found the key            
                    // Check the three cases
                    if ((p.left != null) && (p.right != null)) 
                    { 
                // Case 3: two children                
                            // Search for the min in the right subtree
                            BSTNode<T> min = p.right;
                            q = p;
                            while (min.left != null) 
                            {
                                q = min;
                                min = min.left;
                            }
                            p.key = min.key;               
                            p.data = min.data;
                            k1 = min.key;
                            p = min;
                            // Now fall back to either case 1 or 2
                    }
                    // The subtree rooted at p will change here             
                    if (p.left != null) 
                    { 
                        // One child
                        p = p.left;
                    } 
                    else 
                    { 
                        // One or no children
                        p = p.right;
                    }

                    if (q == null) 
                    { 
                        // No parent for p, root must change
                        root = p;
                    } 
                    else 
                    {
                        if (k1.compareToIgnoreCase(q.key) < 0) 
                        {
                            q.left = p;
                        } 
                        else 
                        {
                            q.right = p;
                        }
                    }
                    current = root;
                    return true;
                }
            }
            return false; // Not found
        }

        public String inOrder ()
         {
             AllKeys = "" ;
              if ( root != null)
                  inorder( root );
              return AllKeys;

         }

         private void inorder(BSTNode<T> p )
         {
             if ( p.left !=null)
                 inorder ( p.left);
             if (AllKeys == "")
                 AllKeys = p.key;
             else
                  AllKeys += " AND " + p.key ;
             if ( p.right !=null)
                 inorder ( p.right);
         }
}
