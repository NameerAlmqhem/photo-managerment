/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructrue;

/**
 *
 * @author nmyralmqhm
 */
public class Photo {
    
          private String path;
          LinkedList<String> allTags = new LinkedList<>();
         
        // Constructor
         public Photo(String path, LinkedList<String> tags)
         {
             this.path = path;
                
             if (! tags.empty())
             {   
                tags.findFirst();
                while ( !tags.last())//all except the last 
                {
                    allTags.insert(tags.retrieve());
                    tags.findNext();
                }
                allTags.insert(tags.retrieve());//the last one
             }
        }
         
        // Return the path (full file name) of the photo. A photo is uniquely identified by its path.
        public String getPath()
        {
                return path;
        }
        
        // Return all tags associated with the photo
        public LinkedList<String> getTags()
        {
            LinkedList<String> tagsNew = new LinkedList<String>();
            
            if (! allTags.empty())
            {
                allTags.findFirst();
                while ( ! allTags.last())
                {
                    tagsNew.insert(allTags.retrieve());
                    allTags.findNext();
                }
                tagsNew.insert(allTags.retrieve());
            }
            return tagsNew;
        }

    @Override
    public String toString() {
        
        String str = "Photo{" + "path=" + path + ", allTags=" ;
        
        allTags.findFirst();
        while ( ! allTags.last())
        {
            str += allTags.retrieve().toString() + "; ";
            allTags.findNext();
        }
        
        str += allTags.retrieve().toString()  + "}";
        return str ;
    }
}
