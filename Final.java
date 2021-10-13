//written by Patrick Kimumwe

import java.io.*;
import java.text.*;
import java.math.*;
import java.lang.*;

public class Final
{

	//Global Variables

	static String Player[] = new String[10000];
	static int x = 0;
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static String pick [] = new String[2];
	static int choice[] = new int [3];
	static int tourney [] = new int [2];	
	static String line;
	static int points,dollars = 0;
	static String play;

   public static void main (String[] args) throws IOException
{
	
	String mode;

	//Introduction
	System.out.println("Welcome to the interactive basketball 1v1 tournament");
	System.out.println("In this tournament you'll try to win prizes by facing off against the computer attempting to guess the winner of the tournament.");
	System.out.println("Points awarded go as follows: 5 points if you guess the winner and the program is wrong, 2 points if you both guess the winner, -3 points if the program guess the winner and you're wrong, and no points if you both answer incorrectly.");	
do
{
//entering menu
do
{
System.out.println("GAME MENU\n");
System.out.println("1. Enter/Add Players");
System.out.println("2. Modify Players");
System.out.println("3. Sort Players");
System.out.println("4. Show Players");
System.out.println("5. Save Players");
System.out.println("6. Load in previous game");
System.out.println("7. Exit Game Menu\n");

//Allow user to choose a mode
System.out.println("What is your choice?");
choice [1] = Integer.valueOf(in.readLine()).intValue();

if (choice [1] == 1)
{
enterPlayer();
}//close choice 1

if (choice [1] == 2)
{
modPlayer();
}//close choice 2

if (choice [1] == 3)
{
sortPlayer();
}//close choice 3

if (choice [1] == 4)
{
showPlayer();
}//close choice 4

if (choice [1] == 5)
{
saveFile();
}//close choice 5

if (choice [1] == 6)
{
load();
}//close choice 6

}
while(choice[1] != 7);//loop until exit is chosen

System.out.println("Here is a list of the players you have selected for the tournament.");
showPlayers();
System.out.println("Out of this list who do you think will win?");

pick [0] = in.readLine();

//check to see if found or not
boolean found = false;

do
{

//flag to hold position in array
int flag = 0;
//counter
int i;

//loop through the players
for(i = 0; i<= (x - 1); i++)
{
	if(pick[0].equalsIgnoreCase(Player[i]))
	{
		found = true;
	}
}

if (x == 0)
{
System.out.println("Sorry no players have been entered.");
found = true;
}

if (found == false)
{
System.out.println("Player not found. Please choose a player to win the tournament");
pick [0] = in.readLine();
}

}
while (found == false);

System.out.println("Now the program will select who it thinks will win");
random();

System.out.println("The program has chosen " + pick [1] + ".");

System.out.println("The tournament shall now commence!");

faceoff();

points();

prizes();

System.out.println("Play again? Type 'yes' to play again!");
play = in.readLine();
}
while (play.equalsIgnoreCase("yes"));
}//close main

public static void faceoff() throws IOException
{
//intialize counter
int j = 1;

do
{

//counter
int k;

//holds original value of x
int hold = x;

//holds random number to determine winner
int decide;

System.out.println("Round " + j + "\n");

//conduct the tournament
for(k = 0; k < hold/2; k++)
{
System.out.println(Player[k] + " will face off against " + Player[k+1] + ".\n");

decide = (int)(Math.random()*2)+1;

if (decide == 1)
{
	for(int i = k; i<(hold - 1);i++)
	{
	//shift the array
	Player[i] = Player[i+1];
	}//close for
System.out.println(Player[k] + " is the winner!\n");
//update our total players' variable "x"
x = x - 1;
}//close if

if (decide == 2)
{
	for(int i = k + 1; i<(hold - 1);i++)
	{
	//shift the array
	Player[i] = Player[i+1];
	}//close for

System.out.println(Player[k] + " is the winner!\n");

//update our total players' variable "x"
x = x - 1;
}//close if

}//close for

//update round counter
j++;

//display the tournament winner
if(x == 1)
{
System.out.println("The winner of the tournament is " + Player[0] + "!");
}//close if

System.out.println("Press enter to move onto round " + j + " (unless a winner has been chosen then move onto prizes).");
in.readLine();

if(x == 0)
{
x = 1;
}

}//close do while
while(x != 1);
}//close method faceoff

public static void random()
{

pick [1] = Player [(int)(Math.random()*x)];

}//close method random


public static void enterPlayer() throws IOException
{
	
do
{
	//Update Array
	System.out.print("Enter/Add a player for the tournament: "); 
	Player[x] = in.readLine();
	
	//Increase counter
	x++;

	//Ask if quit is wanted
	System.out.print("\nTo Quit entering players type 'quit': ");

	//Avoid array error
	line = in.readLine();
	if(x == 16)
	{
	System.out.print("Max number of players entered!");
	line = "quit";
	}//close if	
}
while(line.compareToIgnoreCase("quit")!=0);

}// close method (Enter show)

public static void showPlayers() throws IOException
{
//counter
int i;

//output
for(i = 0; i <=(x - 1); i++)
{
System.out.println("LIST OF PLAYER(S)\n");
System.out.println("Player: "+Player[i]);
System.out.println(" ");
}//close for
}//close method (show players)

public static void points()
{

if (pick[0].equalsIgnoreCase(Player[0]))
{
points = points + 5;
System.out.println("You guessed the winner of the tournament! You have been awarded 5 points.");
} 
if ((pick[0].compareToIgnoreCase(Player[0]) != 0) && (pick[1].equalsIgnoreCase(Player[0])))
{
System.out.println("Sorry you guessed incorrectly. No points awarded.");
}//close else

if (pick[1].equalsIgnoreCase(Player[0]))
{
points = points - 3;
System.out.println("The program guessed the winner. You have been deducted 3 points.");
}//close if

if ((pick[0].compareToIgnoreCase(Player[0]) != 0) && (pick[1].compareToIgnoreCase(Player[0]) !=0))
{
System.out.println("Both program and user guessed incorrectly. No points lost or given.");
}//close if

}//close method points

public static void prizes() throws IOException
{
do
{
System.out.println("Current point total: " + points + "\n");
System.out.println("Here are the list of prizes.");
System.out.println("1. 10 Dollars - 5 Points");
System.out.println("2. 20 Dollars - 10 Points");
System.out.println("3. 30 Dollars - 15 Points");
System.out.println("4. 40 Dollars - 20 Points");
System.out.println("5. Exit points menu\n");
//Allow user to choose a prize
System.out.println("What is your choice?");
choice [0] = Integer.valueOf(in.readLine()).intValue();

if (choice[0] == 1)
{
if (points >= 5)
{
dollars = dollars + 10;

System.out.println("You've collected 10 dollars!");

points = points - 5;

}//close if

if (points < 5)
{
System.out.println("Sorry, you are not eligible for this prize.");
}//close if

}//close choice 1


if (choice[0] == 2)
{
if (points >= 10)
{
dollars = dollars + 20;

System.out.println("You've collected 20 dollars!");

points = points - 10;

}//close if

if (points < 10)
{
System.out.println("Sorry, you are not eligible for this prize.");
}//close if

}//close choice 2

if (choice[0] == 3)
{
if (points >= 15)
{
dollars = dollars + 30;

System.out.println("You've collected 30 dollars!");

points = points - 15;

}//close if

if (points < 15)
{
System.out.println("Sorry, you are not eligible for this prize.");
}//close if

}//close choice 3


if (choice[0] == 4)
{
if (points >= 20)
{
dollars = dollars + 40;

System.out.println("You've collected 40 dollars!");

points = points - 20;

}//close if

if (points < 20)
{
System.out.println("Sorry, you are not eligible for this prize.");
}//close if

}//close choice 4

}//close do while
while (choice [0] != 5);
}//close method prizes

public static void modPlayer() throws IOException
{
//string to hold user input
String modplayer;
//flag to hold position in array
int flag = 0;
//counter
int i;
//check to see if found or not
boolean found = false;
//ask for input
System.out.print("Who is the player you wish to modify?"); 
modplayer = in.readLine();
//loop through the shows
for(i = 0; i<= (x - 1); i++)
{
	if(modplayer.equalsIgnoreCase(Player[i]))
	{
		flag = i; 
		found = true;
	}
}
if (found == false)
{
System.out.println("Player not found");
}
else
{
//Enter modified information
System.out.print("Enter new player information: "); 
Player[flag] = in.readLine();
System.out.println("New player name is: " + Player[flag]+ "\n");
}//close else

}//close method (Modify Player)

public static void sortPlayer() throws IOException
{

//i and j are counters, smallest is the current smallest element
int i, j, smallest;

//we use temp to make an element swap 
String temp;

//Sorting time!
for(i = 0; i <=(x - 1); i++)
{
	smallest = i;

	for(j = i; j <=(x - 1); j++)
	{
		//Compare our current smallest to the current position in the array
		if(Player[j].compareToIgnoreCase(Player[smallest]) < 0)
		{
			smallest = j;
		}//close if
	}//close for
//swap smallest element with position in the array
temp = Player[i];

Player[i] = Player[smallest];

Player[smallest] = temp;

}//close for

//output
System.out.println("All the players have been sorted!");
System.out.println(" ");

}//close method (sort players)

public static void showPlayer() throws IOException
{
//counter
int i;

System.out.println("LIST OF PLAYER(S)\n");

//output
for(i = 0; i <=(x - 1); i++)
{

System.out.println("Player: "+Player[i]);
System.out.println(" ");

}//close for
}//close method (show players)

public static void saveFile() throws IOException
{
//counter
int i;
//Create File output
PrintWriter output;
output = new PrintWriter(new FileWriter("Tourney.txt"));
//Output the number of players to the file
output.println(x);

//loop through our array outputing to a file 
for(i = 0;i<=(x-1);i++)
{
System.out.println("Saving player: " + Player[i] + " to file"); 
output.println(Player[i]);
}
//close file
output.close();
}//close method (Saving)

public static void load() throws IOException
{

//Counter
int i;

//Create Fileinput
BufferedReader input;
input = new BufferedReader(new FileReader("Tourney.txt"));

//x is the total number of players
x = Integer.valueOf(input.readLine()).intValue(); 
System.out.println("Loading "+x+" player(s).");

//Loop through all the shows in the file
for(i = 0;i<=(x-1);i++)
{
//Read in the data
Player[i] = input.readLine();
}//close for

}//close method (load)

}//close class
