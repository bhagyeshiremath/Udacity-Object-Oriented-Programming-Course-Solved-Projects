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
        String str = (movieList.get(randomIndex)).toUpperCase();
        scn.close();
        //System.out.println(str);
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
    public static int repeating_char(String s , char character , StringBuilder temp) throws FileNotFoundException
    {
        for(int k = 0 ; k < s.length(); k++)
        {
            if(s.charAt(k) == character && temp.toString().charAt(k) == '_')
            {
               return k;
            }
        }
        return -1;
    }

   public static void main(String[] args) throws FileNotFoundException
   { 
       String[] temp = converting();
       //I'm using string builder because we are modi0fying the string
       StringBuilder s = new StringBuilder(temp[0]);
       System.out.println("Try Guessing by this "+ s.toString()+"\nLength of the movie name is "+ temp[1].length());
       //Its just for testing purpose
       //System.out.println(temp[1]);
       Scanner scn = new Scanner(System.in);  
       int i = 10;
       while(i >= 0)
       {
            System.out.print("Guess the movie by entering the character :- ");
           
            String temp_String = scn.next().toUpperCase();
            char character ; 
            //I'm checking if a string is cointaing same char more than one time
            if(temp_String.length() == 1)
            {
                character = temp_String.toUpperCase().charAt(0);
                //Here I'm checking if the user is entering the char only using ascii values
                if(character >= 65 && character <= 90 || character >= 97 && character <= 122)
                {
                    //this is the find the position the that char
                    int index = temp[1].indexOf(Character.toString(character));
                    if(index != -1)
                    {
                        //if char is repeating than do this
                        if(s.toString().charAt(index) != '_')
                        {
                            int flag = repeating_char(temp[1], character , s);
                            s.setCharAt(flag, character);
                        }
                        else
                        {
                            //else this
                            //setCharAt is a StringBuilder function to set the char at specific position
                            s.setCharAt(index,character);
                        }
                        String t = s.toString();
                        System.out.println("Modified guess is:- "+t);
                    }
                    else
                    {
                        System.out.println("You have more "+ --i +" guess(es) left");
                        System.out.println("Guess the right char !!!!!");
                    }
                
                    //here I'm checking if the out random string is equals o user input or not
                    if(temp[1].equals(s.toString()))
                    { 
                        System.out.println("Congratulations ! You guessed it right...");
                        break;
                    }    
                }
                else
                {
                    System.out.println("Please enter a character only");
                }
            }
            else
            {
                System.out.println("Enter single character at a time ...");
            }            
        }
        System.out.println("The correct random movie is :- " + temp[1]);       
       scn.close();
    }
}           
