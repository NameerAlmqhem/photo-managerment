/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructrue;

import java.util.Scanner;

/**
 *
 * @author nmyralmqhm
 */
public class Album {
    private String name;
        private String condition;
        private PhotoManager manager;
        private InvIndexPhotoManager invmanager;
        private int NbComps;

        // Constructor
        public Album(String name, String condition, PhotoManager manager, InvIndexPhotoManager invmanager )
        {
            this.name = name;
            this.condition = condition;
            this.manager = manager;
            this.invmanager = invmanager;
            NbComps =0;
        }
        
        // Return the name of the album
        public String getName()
        {
            return name;
        }
        
        // Return the condition associated with the album
        public String getCondition()
        {
            return condition;
        }

        // Return the manager
        public PhotoManager getManager()
        {
            return manager;
        }

        // Return the manager
        public InvIndexPhotoManager getInvManager()
        {
            return invmanager;
        }
        
        // Return the number of tag comparisons used to find all photos of the album
        public int getNbComps()
        {
            return NbComps;
        }
        
        // Return all photos that satisfy the album condition
        public LinkedList<Photo> getPhotos()
        {
            int choice = menu();
            
            LinkedList<Photo> result = new LinkedList<Photo>();
            switch (choice )
            {
                case 1:
                    result = getPhotosLL();
                    break;
                default:
                    result = getPhotosBST();
        }
        return result;
        }
            
       //=================================================================
       private LinkedList<Photo> getPhotosLL()
       {
                LinkedList<Photo> Rphotos = new LinkedList<Photo>();
                {
                    LinkedList<Photo> photos1 = manager.getPhotos();
                    if (! photos1.empty())
                    {
                        photos1.findFirst();
                        while (! photos1.last())
                        {
                            Rphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                            photos1.findNext();
                        }
                        Rphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                    }
                }
                NbComps =0 ;
                
                if (this.condition.compareTo("") != 0)
                {
                    String [] Array = condition.split(" AND ");
                    
               Rphotos.findFirst();
                    while ( ! Rphotos.last())
                    {
                        Photo photo = Rphotos.retrieve();
               if ( ! allAvilable (photo.allTags , Array ))
                            Rphotos.remove();
                        else
                            Rphotos.findNext();
                    }
                    Photo photo11 = Rphotos.retrieve();
                    if ( ! allAvilable (photo11.allTags , Array ))
                        Rphotos.remove();
                    else
                        Rphotos.findNext();
                }
                return Rphotos;
        }
       

        private boolean allAvilable ( LinkedList<String> AllTags , String [] Array )
        {
            boolean allTagsFound = true;  //استمرار التحقق من كل tags
            if (AllTags.empty())
                allTagsFound = false;
            else
            {
                for ( int i = 0 ; i < Array.length && allTagsFound ; i++)
                {
                    boolean found_in_tags = false;

                    AllTags.findFirst();

                    while (!AllTags.last())
                    {
                        this.NbComps ++ ;    
                        if (AllTags.retrieve().compareToIgnoreCase(Array[i]) == 0)
                        {
                            found_in_tags = true;
                            break;
                        }
                        AllTags.findNext();
                    }
                    if (! found_in_tags )
                    {
                        this.NbComps ++ ;
                        if (AllTags.retrieve().compareToIgnoreCase(Array[i]) == 0)
                            found_in_tags = true;
                    }
                    if ( ! found_in_tags )
                       allTagsFound= false;
                }
            }
            return allTagsFound;
        }

       //=================================================================
        // Return all photos that satisfy the album condition
        private LinkedList<Photo> getPhotosBST()
        {
            BST<LinkedList<Photo>> photosBST = invmanager.getPhotos();
            LinkedList<Photo> Rphotos = new LinkedList<Photo>();
            NbComps =0 ;
            String [] tags;
            

            if (this.condition.compareTo("") == 0)
            {
                if ( photosBST.findkey(" ") == true)
                    Rphotos = photosBST.retrieve();
            }
            else
            {
                tags = condition.split(" AND ");
                for ( int i = 0 ; i < tags.length ; i++)
                {
                    if ( photosBST.findkey(tags[i]) == true)
                    {
                        if (i == 0)
                        {
                            LinkedList<Photo > miniTag = photosBST.retrieve();
                            miniTag.findFirst();
                            while ( ! miniTag.last())
                            {
                                Rphotos.insert(miniTag.retrieve());
                                miniTag.findNext();
                            }
                            Rphotos.insert(miniTag.retrieve());
                        }
                        else
// كتبتها عشان أتحقق من الصور المشتركة بين قائمتين بناءً على المسار
                            Rphotos  = intersectPhotos( Rphotos , photosBST.retrieve());
                    } 
                    else
                    {
                        Rphotos = new LinkedList<Photo>();
                        break;
                    }
                }
            }
            return Rphotos;
        }

        // used when there is a condition 
        private LinkedList<Photo> intersectPhotos ( LinkedList<Photo> list1 ,LinkedList<Photo> list2)
        {
            LinkedList<Photo> result = new LinkedList<Photo>();
            
            // empty list with no photos
            if ( list1.empty())
                return result;
            
            if (list2.empty())
                return list1;
            
            list2.findFirst();
            while (! list2.last())
            {
                boolean found = false;
                list1.findFirst();
                while (! list1.last() && ! found)
                {
                    NbComps++;
                    if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                        found = true;
                    list1.findNext();
                }
                if (! found )
                {
                    NbComps++;
                    if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                        found = true;
                }
                if (found )
                    result.insert(list2.retrieve());

                list2.findNext();
            }
            boolean found = false;
            list1.findFirst();
            while (! list1.last() && ! found)
            {
                NbComps++;
                if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                    found = true;
                list1.findNext();
            }
            if (! found )
            {
                NbComps++;
                if ( list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                    found = true;
            }
            if (found )
                result.insert(list2.retrieve());
                                  
            return result;
        }
        
        //============================================================
        private int menu()
        {
            Scanner input = new Scanner ( System.in);
        
            int choice;
            System.out.println("1. Linked List");
            System.out.println("2. BST");
            System.out.println("Enter your choice ");
            choice = input.nextInt();
            
            return choice;
            
        }
}
