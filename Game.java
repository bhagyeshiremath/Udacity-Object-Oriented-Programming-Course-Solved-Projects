import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Game
{
    //This function is for generting random movie
    public static String displayMovie() throws FileNotFoundException
    {
        File file = new File("MovieList.txt");
        Scanner scn = new Scanner(file);
        ArrayList<String> movieList = new ArrayList<String>();
        while (scn.hasNext())
        {
            movieList.add(scn.nextLine());
        }
        //System.out.println(movieList);
        int randomIndex = (int)(Math.random() * movieList.size());
        //System.out.println("Random movie: " + movieList.get(randomIndex));
        //System.out.println(randomIndex);
        String str = movieList.get(randomIndex);
        scn.close();
        return str;     
    }

    //This function is for converting random generated movie into _ (Underscore) for each char
   public static String[] converting() throws FileNotFoundException
   {
        String word = displayMovie();
        String hidingName = word.replaceAll("[A-Za-z]","_");
        String[] arr = {hidingName , word};
       //System.out.println(arr);
        return arr;
   }

    //I have created this function check wheather a string contains a repeated char
    public static ArrayList<Integer> repeating_char(String s , char character) throws FileNotFoundException
    {
        ArrayList<Integer> ar = new ArrayList<>();
        int count = 0;
        for(int k = 1 ; k < s.length()-1 ; k++)
        {
            if(s.charAt(k) == character)
            {
                count ++;
                ar.add(k);
            }
        }
        ar.add(count);
        return ar;
    }

   public static void main(String[] args) throws FileNotFoundException
   { 
       String[] temp = converting();
       //I'm using string builder because we are modi0fying the string
       StringBuilder s = new StringBuilder(temp[0]);
       System.out.println("Try Guessing by this "+ s.toString()+"\nLength of the movie name is "+ temp[1].length());
       Scanner scn = new Scanner(System.in);  
       int i = 10;
       while(i >= 0)
       {
            System.out.print("Guess the movie by entering the character :- ");
            char character = scn.next().charAt(0);
            //Here 65 to 90 (Capital char) & 97 to 122 (Small char)are the ASCII values
            if(character >= 65 && character <= 90 || character >= 97 && character <= 122)
            {
                int index = temp[1].indexOf(Character.toString(character));
                if(index != -1)
                {
                    ArrayList <Integer> indices = repeating_char(temp[1], character);
                    if(indices.get(indices.size()-1) > 1)
                    {
                       indices.remove(indices.size()-1);
                       for(int j : indices)
                       {
                           s.setCharAt(j, character);
                       }
                    }
                    else
                    {
                        s.setCharAt(index,character);//setCharAt is a StringBuilder function to set the char at specific position
                    }
                }
                String t = s.toString();
                System.out.println("Right Guess!!\nModified guess is:- "+t);

                        if(temp[1].equals(s.toString()))
                        { 
                            System.out.println("Congratulations!You guessed it right...");
                            break;
                        }    
                        else
                        {
                            System.out.println("You have more "+ --i +" guess(es) left");
                            System.out.println("Guess the right char !!!!!");
                        }
            }
           else
           {
               System.out.println("Please enter a character only");
           }
        }
        System.out.println("The correct random movie is :- " + temp[1]);       
       scn.close();
    }
}
       
       
           
        
           
           