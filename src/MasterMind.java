/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import sun.audio.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author mrusy
 */
public class MasterMind {

    public static void main(String[] args) throws IOException {
        
        

        Scanner a = new Scanner(System.in);
        int colour;//number of colour
        int codeGenerated[] = new int[4];//codemaker's code
        //String codeGeneratedInChar[] = new String[4];
        int guess[] = new int[4];
        String guessInChar[] = new String[4];
        int numGuess;
        char choice;
     
        
        
        String name;

        int tempCode[] = new int[4];
        int tempGuess[] = new int[4];

        int counter = 0;
        int score = -1;
        
        int index=0;
        
        int feedbackArrayCounter=0;
       
       
        
        
       //Music("C:\\Users\\mrusy\\Documents\\NetBeansProjects\\MasterMind\\Music\\doto.wav");
        
        
        
        System.out.printf("\t\t\tWelcome to MasterMind!!!\n\n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("The available colours are: ");
        System.out.println("\u001B[37m" + "White " + "\u001B[0m" + "\u001B[31m" + "Red " + "\u001B[0m" + "\u001B[32m" + "Green " + "\u001B[0m" + "\u001B[33m" + "Yellow " + "\u001B[0m" + "\u001B[34m" + "Blue " + "\u001B[0m" + "\u001B[35m" + "Purple " + "\u001B[m" + "\u001B[36m" + "Cyan " +  "\u001B[30m" + "\u001B[30m" + "Black " + "\u001B[0m" + "\n");
        System.out.println("For the feedback:");
        System.out.println("Black - Correct colour and position");
        System.out.println("White - Correct colour but wrong position\n");
        System.out.println("You can enter the followwing commands during guessing to perform special actions: ");
        System.out.println("To restart the game            : 0 0 0 0");
        System.out.println("To give up and reveal the code : 9 9 9 9");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        
        
        //looping for continue
        
        do {
        //play or enter score board
        System.out.println("\nPlay or View ScoreBoard(p/v): ");
        choice = a.nextLine().charAt(0);
        
        //checking to play
        if (choice=='p'){
        
        System.out.print("\n\nPlease enter your name: ");
        name = a.nextLine();
        
        
        System.out.print("Enter the number of guesses allowed: ");
        numGuess = a.nextInt();
        
        //for printing history purpose
        int printGuess[] = new int[numGuess*4];
        int reverse[] = new int [4];
        
        int blackArray[] = new int[numGuess];
        int whiteArray[] = new int[numGuess];
        
        System.out.print("Enter the number of colour (2-8): ");
        colour = a.nextInt();

        //check if no of colour is out of bounds
        while (colour < 2 || colour > 8) {
            System.out.print("Please enter the correct number of colour: ");
            colour = a.nextInt();

        }
        
        int numColour[] = new int[colour];
        
        
        //generate the code in int
        generateCode(codeGenerated,numColour,colour);
        //convert the code into strings
        //generateCodeInChar(codeGenerated,codeGeneratedInChar);
        
        System.out.println("\nThe colours available are: ");
        for(int i=0;i<colour;i++){
            System.out.print(printColour(numColour[i]) + " ");
            
        }

        
        //start timer
        double startTime = System.nanoTime();
        
        System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
        System.out.println("\nEnter your guess: ");
        

        //user input in char
        for (int i = 0; i < 4; i++) {
            guessInChar[i] = a.next();
        }
        
        //change user input to int
        for (int i = 0; i < 4; i++) {
            guess[i] = guessToInt(guessInChar[i]) ;
            
        }
        
        for (int i = 0, j = 3; i < 4; i++, j--) {
            reverse[i]=guess[j];
        }
        
        for (int i = 0,j=index ; i < 4; i++) {
            printGuess[index]=reverse[i];
            index++;
        }
        
        
        
        System.out.print("\t\t\t\t\t\t\t\t    History");
        
       

        for (int i = 0; i < 4; i++) {
            tempCode[i] = codeGenerated[i];
            tempGuess[i] = guess[i];
        }

        
        
        //where shit starts
        while (true) {
            
                     //restart function
                    if(guess[0]==0&&guess[1]==0&&guess[2]==0&&guess[3]==0){

                        //reset
                            counter = 0;
                            score = -1;
                            index=0;
                            feedbackArrayCounter=0;

                            System.out.println("\nYou have chose to restart.\n");

                            System.out.print("Enter the number of guesses allowed: ");
                            
                            numGuess = a.nextInt();

                            //for printing history purpose
                            printGuess = new int[numGuess*4];
                            

                            blackArray = new int[numGuess];
                            whiteArray = new int[numGuess];


                            System.out.print("Enter the number of colour (2-8): ");
                            colour = a.nextInt();

                            //check if no of colour is out of bounds
                            while (colour < 2 || colour > 8) {
                                System.out.print("Please enter the correct number of colour: ");
                                colour = a.nextInt();

                            }

                            numColour = new int[colour];


                            //generate the code in int
                            generateCode(codeGenerated,numColour,colour);
                            //convert the code into strings
                            //generateCodeInChar(codeGenerated,codeGeneratedInChar);

                            System.out.println("\nThe colours available are: ");
                            for(int i=0;i<colour;i++){
                                System.out.print(printColour(numColour[i]) + " ");

                            }


                            //start timer
                            startTime = System.nanoTime();

                            System.out.println("\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
                            System.out.println("\nEnter your guess: ");


                            //user input in char
                            for (int i = 0; i < 4; i++) {
                                guessInChar[i] = a.next();
                            }

                            //change user input to int
                            for (int i = 0; i < 4; i++) {
                                guess[i] = guessToInt(guessInChar[i]) ;

                            }

                            for (int i = 0, j = 3; i < 4; i++, j--) {
                                reverse[i]=guess[j];
                            }

                            for (int i = 0,j=index ; i < 4; i++) {
                                printGuess[index]=reverse[i];
                                index++;
                            }
                            
                             for (int i = 0; i < 4; i++) {
                                tempCode[i] = codeGenerated[i];
                                tempGuess[i] = guess[i];
                              }

                            continue;

                    }

                    //to giveup and reveal the code
                    if(guess[0]==9&&guess[1]==9&&guess[2]==9&&guess[3]==9){
                        Music("C:\\Users\\mrusy\\Documents\\NetBeansProjects\\MasterMind\\Music\\Sad Trombone.wav");
                        System.out.println("\nYou have chose to give up and reveal the code.\n");
                        score = 0;
                        break;
                    }


                int black = 0;
                int white = 0;

                //if correct colout and position, no need to check the position again
                    for (int i = 0; i < 4; i++) {

                        if (tempCode[i] == tempGuess[i]) {

                            black++;
                            tempCode[i] = -1;
                            tempGuess[i] = -1;

                        }

                    }    

                    
                for (int i = 0; i < 4; i++) {
                    //check klau black dah affect
                    if (tempCode[i] == -1) {
                        continue;
                    }
                    
                    for (int j = 0; j < 4; j++) {
                        //check klau black dah affect
                        if (tempGuess[j] == -1) {
                            continue;
                        }
                        if (tempCode[i] == tempGuess[j]) {
                            white++;
                            tempCode[i] = -1;
                            tempGuess[j] = -1;
                        }
                    }
                }
                
                
                blackArray[feedbackArrayCounter] = black;
                whiteArray[feedbackArrayCounter] = white;
                feedbackArrayCounter++;
                

               //prints feedback and guess
                System.out.println("");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
                System.out.println("                                                |                                               |  \n");
                
                    
                    for (int i = printGuess.length-1,j=numGuess-1 ; i>-1; i--){
                        
                        if(i==3||(i+1)%4==0)
                            System.out.print(blackArray[j] + " black, " + whiteArray[j] + " white\t\t\t\t|\t");
                            
                        System.out.print(printInColour(printGuess[i]) + " ");
                        
                        if(i%4==0){
                            System.out.println("\t|\n");
                        j--;
                        }
                        
                    }
                
//                System.out.print(black + " correct colour and position");
//                System.out.println("\n" + white + " correct colour but wrong position\t\t|\t\t\t\t\t\t|");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  \n");
                
                counter++;  
                
               
                //checks for exit condititon, either win or lose
                if (black == 4) {
                    Music("C:\\Users\\mrusy\\Documents\\NetBeansProjects\\MasterMind\\Music\\Crowd_1.wav");
                    System.out.println("\nYou have guess the code correctly!\n");
                    break;
                } else if (counter == numGuess) {
                      Music("C:\\Users\\mrusy\\Documents\\NetBeansProjects\\MasterMind\\Music\\Sad Trombone.wav");
                    System.out.println("\nYou're out of guesses!\n");
                    score = 0;
                    break;
                }   

               
                
                Music("C:\\Users\\mrusy\\Documents\\NetBeansProjects\\MasterMind\\Music\\oof.wav");
                
                //asks for user input again
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");
                System.out.println("\nEnter your guess: ");
                for (int i = 0; i < 4; i++) {
                    guessInChar[i] = a.next();
                }
                
                for (int i = 0; i < 4; i++) {
                    guess[i] = guessToInt(guessInChar[i]) ;
                }
                
                for (int i = 0, j = 3; i < 4; i++, j--) {
                   reverse[i]=guess[j];
                }
        
                for (int i = 0,j=index ; i < 4; i++) {
                   printGuess[index]=reverse[i];
                   index++;
                }
                
                

                for (int i = 0; i < 4; i++) {
                    tempCode[i] = codeGenerated[i];
                    tempGuess[i] = guess[i];
                }

         //end bracket for big while loop
         }       

            //prints out the code
            System.out.print("The code is: ");
            for (int i = 0; i < 4; i++) 
               System.out.print(printInColour(codeGenerated[i]) + " "); 
            
           
            //ends the timer
            double endTime = System.nanoTime();
            double elapsedTime = endTime - startTime;
            double seconds = elapsedTime / 1_000_000_000.0;    

            //if the player does not give up or lose the match    
            if(score!=0)
            score = (100000*colour)/(1+(counter*(int)seconds));

            //prints out the result
            System.out.println("\nNumber of attempts: " + counter);
            System.out.println("Time taken: " + (int)seconds + " seconds");
            System.out.println("Score: " + score);
        
        
        //record and store the player name and score into a text file
        try {

            File file = new File("MasterMind Score.txt");
            PrintWriter record = new PrintWriter(new FileWriter(file, true));

            record.printf("%-20s\t\t%-5d", name, score);
            record.println("");
            

            record.close();

        } catch (FileNotFoundException e) {
            System.out.println("The file is not found.");
        }
        
        
        //record and store the player's guess
        try {

            File file = new File("Player's Guess History.txt");
            PrintWriter record = new PrintWriter(new FileWriter(file, true));

            record.printf("%S\'s guess:", name);
            record.println("");
            record.println("");
            
            record.println("- - - - - - - - - - - - - - - - - - - ");
            
            for (int i = printGuess.length-1,j=numGuess-1 ; i>-1; i--){
                        
                       
                        
                        record.printf("%-7S ",printGuessHistory(printGuess[i]));    
                       
                        if(i%4==0){
                            record.println("");
                          j--;
                        }   
                       
                        
                    }
            
            record.println("- - - - - - - - - - - - - - - - - - - ");
            record.println("");
            record.println("");
            record.println("");
            record.close();

        } catch (FileNotFoundException e) {
            System.out.println("The file is not found.");
        }
        
        //checking to see scoreboard
        } else if (choice=='v') {
            //read score and sort it
            System.out.println("");
            List <String> playerName = new ArrayList<>();
            List <Integer> playerScore = new ArrayList<>();
            try {
                Scanner s = new Scanner(new File("MasterMind Score.txt"));
                while(s.hasNext()){
                    playerName.add(s.next());
                    playerScore.add(s.nextInt());
                }
                s.close();
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                System.out.println("|\t\t   SCOREBOARD   \t\t|");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                System.out.println("");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                System.out.println("|\tName\t\t|\tScore\t\t|");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
                //sorting
                for(int x = 0; x < playerScore.size()-1; x++){
                    for(int y = x+1; y < playerScore.size(); y++){
                        String holdName;
                        int holdScore;
                        if(playerScore.get(x) < playerScore.get(y)){
                            holdName = playerName.get(x);
                            holdScore = playerScore.get(x);
                            playerName.set(x,playerName.get(y));
                            playerScore.set(x,playerScore.get(y));
                            playerName.set(y,holdName);
                            playerScore.set(y,holdScore);
                        }
                    }
                }
                //display
                for(int x = 0; x < 10; x++){
                    System.out.println("|\t" + playerName.get(x) + "\t\t|\t" + playerScore.get(x) + "\t\t|");
                }
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
            } catch (FileNotFoundException e) {
                System.out.println("The file is not found.");
            }
        }
        System.out.println("\nContinue?(y/n)");
        if(choice=='p')
            a.nextLine();
        
        //reset
        counter = 0;
        score = -1;
        index=0;
        feedbackArrayCounter=0;
                            
        } while(a.nextLine().charAt(0)!='n');
        
     //end of main method
     }   
       
        
    public static void generateCode(int a[],int b[], int x) {

        Random random = new Random();
        boolean state = true;
        
        //determine what colours are inside the code
        for(int i=0;i<x;i++){
            state = true;
            
            while(state){
                int temp = 1 + random.nextInt(8);
                
                for(int j=0;j<x;j++){
                    if(b[j]==temp)
                        break;
                    if(j==x-1){
                        b[i]=temp;
                        state=false;
                    }
                    
                }
            }
        }

        
        
        //generates the code with the colours generated
        for (int i = 0; i < 4; i++) {
            a[i] = b[random.nextInt(x)];
        }        
        
     }     
        

    
    
    public static void generateCodeInChar(int a[],String b[]){
        
        for (int i = 0; i < 4; i++) {
            
            if(a[i]==1)
                b[i]="White";
            else if(a[i]==2)
                b[i]="Red";
            else if(a[i]==3)
                b[i]="Green";
            else if(a[i]==4)
                b[i]="Yellow";
            else if(a[i]==5)
                b[i]="Blue";
            else if(a[i]==6)
                b[i]="Purple";
            else if(a[i]==7)
                b[i]="Cyan";
            else
                b[i]="Black";
            
        }
        
    }
    
    public static int guessToInt(String a){
        
            if(a.equalsIgnoreCase("White"))
                return 1;
            else if(a.equalsIgnoreCase("Red"))
                return 2;
            else if(a.equalsIgnoreCase("Green"))
                return 3;
            else if(a.equalsIgnoreCase("Yellow"))
                return 4;
            else if(a.equalsIgnoreCase("Blue"))
                return 5;
            else if(a.equalsIgnoreCase("Purple"))
                return 6;
            else if(a.equalsIgnoreCase("Cyan"))
                return 7;
            else if(a.equalsIgnoreCase("Black"))
                return 8;
            else if(a.equalsIgnoreCase("0"))
                return 0;
            else if(a.equalsIgnoreCase("9"))
                return 9;
            else 
                return -1;
    }
    
    public static String printInColour(int a){
        
            if(a==1)
                return "\u001B[47m" + "       " + "\u001B[0m";
            else if(a==2)
                return "\u001B[41m" + "       " + "\u001B[0m";
            else if(a==3)
                return "\u001B[42m" + "       "  + "\u001B[0m";
            else if(a==4)
                return "\u001B[43m" + "       "  + "\u001B[0m";
           else if(a==5)
                return "\u001B[44m" + "       " + "\u001B[0m";
            else if(a==6)
                return "\u001B[45m" + "       "  + "\u001B[0m";
            else if(a==7)
                return "\u001B[46m" + "       "  + "\u001B[0m";
            else if(a==8)
                return "\u001B[40m" + "       "  + "\u001B[0m";
            else if(a==0)
                return "   O   ";
            else
                return " Error ";
            
        
    }
    
    public static String printColour(int a){
        
        
            if(a==1)
                return "\u001B[37m" + "White" + "\u001B[0m";
            else if(a==2)
                return "\u001B[31m" + "Red" + "\u001B[0m";
            else if(a==3)
                return "\u001B[32m" + "Green"  + "\u001B[0m";
            else if(a==4)
                return "\u001B[33m" + "Yellow"  + "\u001B[0m";
           else if(a==5)
                return "\u001B[34m" + "Blue" + "\u001B[0m";
            else if(a==6)
                return "\u001B[35m" + "Purple"  + "\u001B[0m";
            else if(a==7)
                return "\u001B[36m" + "Cyan"  + "\u001B[0m";
            else if(a==8)
                return "\u001B[30m" + "Black"  + "\u001B[0m";
            else
                return " Error ";
            
        
    }
    
     public static String printGuessHistory(int a){
            if(a==1)
                return "White";
            else if(a==2)
                return "Red";
            else if(a==3)
                return "Green";
            else if(a==4)
                return "Yellow";
            else if(a==5)
                return "Blue";
            else if(a==6)
                return "Purple";
            else if(a==7)
                return "Cyan";
            else if(a==8)
                return "Black";
            else if(a==0)
                return "O";
            else if(a==9)
                return "9";
            else
                return "Error";
     }
        
        
    public static void Music(String filepath){
        InputStream music;
        try{
            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
            
        }catch(IOException e){
            System.out.println("Error");
        }
        
    }
        
}
   

    
    
    
    
  
    
    
  
    
     
     


