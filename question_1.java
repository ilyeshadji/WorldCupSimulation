	//-----------------------------------------------------------------------------
	// Assignment 3 
	// Written by: Ilyes Hadji
	// For COMP 248 Section K-X - Fall 2018
	//-----------------------------------------------------------------------------

/*
 * This program is doing a simulation of a World Cup Tournament. 
 * First, it asks the user to choose his favorite team. If the team is not on the ROUND OF 16 list, he has the choice to retry or to
 * exit the program.
 * If the user inputs a team that is on the ROUND OF 16 list, the simulation starts. 
 * Second, the simulation has a limited possible number of time it can get executed (20). The program is looking to see if the chosen team wins
 * or not the tournament in the amount of possible tries.
 * Third, all the scores of all tournaments are showed to the user.
 * Forth, the program does an average of the total goals scored in the games.
 * Finally, it compares the scores to the average to know how many games were above it.
 * 
 */

import java.util.Scanner;
public class question_1 
{
	public static void main (String[] args)
	{
		// Welcome message
		System.out.println("	Welcome to our World Cup Tournament Simulation Program!");
		System.out.println("-------------------------------------------------------------------");
		Scanner myK = new Scanner(System.in);
		
		//Initializing the teams that take part to the competition
		String[] teams = {"Uruguay", "Portugal", "France", "Argentina", "Brazil", "Mexico", "Belgium",
						"Japan", "Spain","Russia","Croatia","Denmark", "Sweden", "Switzerland", "Colombia", "England"};
		
		//Initializing all the useful variables and array for the program:
		
		String favTeam, answer, w_final;
		//Used to know if the user wants to retry
		boolean again = true;
		
		//Used to know the winners of all rounds
		Winner winnersRound16 = new Winner();
		Winner winnersQuarters = new Winner();
		Winner winnersSemiFinals = new Winner();
		Winner winnerFinal = new Winner();
		//Score of the games
		Winner scoreGame = new Winner();
		
		//Asking for the user input
		System.out.print("Enter your favorite team: ");
		favTeam = myK.next();
		
		//To ignore any additional spaces added to the favorite team names
		favTeam.replaceAll(" ","");
		favTeam.replaceAll("\n","");	
		
		//To keep track of the winners
		String[] w_round16 = new String[8];
		String[] w_quarters= new String[4];
		String[] w_semiFinals = new String[2];
		
		//To count the number of tournaments
		int count = 0;
		
		//To keep track of the number of teams that scored above the average
		int count2 = 0;
		
		//To keep track of the number of games played in a tournament
		int numberOfGame = 0;
		//To keep track of the number of tournaments 
		int numberOfTournaments =0;
	
		double average, overallAverage;
		//To keep track of the averages
		double[] arr_averages = new double[21];
		//To keep track of the game scores
		int[] gamesScore = new int[4];
		//To keep track of the number of goals in each game in each tournament
		int[][][] totalGoals = new int [21][15][1];
		//To know if the users favorite team wins the tournament
		boolean favTeamWins = false;

		//This loop is useful to know if the user wants to retry to input his favorite team
		outer : do
		{
			//This loop is useful to compare to favorite team to the list of teams that participate in the ROUND OF 16
			for (int nameComparison=0; nameComparison<=15; nameComparison++)
				{
				//Start of the tournament if the user inputs a team that is on the ROUND OF 16 list
				if (favTeam.equalsIgnoreCase(teams[nameComparison]))
					{
						//To keep track if the favorite team won the last tournament
						while (favTeamWins == false)
							{
								//Round of 16
								System.out.print("\nROUND OF 16 ");
								//Choosing the opponents
								for (int firstRound=0; firstRound<teams.length ; firstRound+=2)
									{
										//Giving scores to the games (randomly)
										gamesScore = scoreGame.theScore();
										
										//Output the scores of the games
										System.out.print("[" + teams[firstRound] + " "+ (gamesScore[0] + gamesScore[2]) +":" + (gamesScore[1] + gamesScore[3])+
												" "+teams[firstRound +1]+ "] ");
										//Keep track of the winners of the ROUND OF 16
										w_round16[firstRound/2] = winnersRound16.makeGame(gamesScore[0], gamesScore[1], gamesScore[2], gamesScore[3], 
																	teams[firstRound], teams[firstRound+1]);
										//Keep track of the total goals depending of the tournament
										totalGoals[numberOfTournaments][numberOfGame][0] = gamesScore[0]+gamesScore[1]+gamesScore[2]+gamesScore[3];
										//To store the goals depending the game
										numberOfGame+=1;
									}
					System.out.println();
					//Quarter-finals
					System.out.print("QUARTER-FINALS ");
			
					//Choosing the opponents from the winners of the ROUND OF 16 
					for (int secondRound=0; secondRound<w_round16.length ; secondRound+=2)
					{
						//Giving scores to the games (randomly)
						gamesScore = scoreGame.theScore();
						
						//Output the score of the games
						System.out.print("[" + w_round16[secondRound] + " "+ (gamesScore[0] + gamesScore[2]) +":" + (gamesScore[1] + gamesScore[3]) +
											" "+w_round16[secondRound +1]+ "] ");
						
						//Keep track of the winners of the QUARTER-FINALS
						 w_quarters[secondRound/2] = winnersQuarters.makeGame(gamesScore[0], gamesScore[1], gamesScore[2], gamesScore[3], w_round16[secondRound], 
								 					 w_round16[secondRound+1]);
						 
						 //Keep track of the goals depending of the tournament
						 totalGoals[numberOfTournaments][numberOfGame][0] = gamesScore[0]+gamesScore[1]+gamesScore[2]+gamesScore[3];
						 
						 //To store the goals depending the game
						 numberOfGame+=1;
					}
					System.out.println();
					
					//SEMI-FINALS
					System.out.print("SEMI-FINALS ");
					
					//Choosing the opponents from the winners of the QUARTER-FINALS
					for (int semiFinals=0; semiFinals<w_quarters.length ; semiFinals+=2)
					{
						//Giving score to the games (randomly)
						gamesScore = scoreGame.theScore();
						
						//Output the score of the games
						System.out.print("[" + w_quarters[semiFinals] + " "+ (gamesScore[0] + gamesScore[2]) +":" + (gamesScore[1] + gamesScore[3]) +
											" "+w_quarters[semiFinals +1]+ "] ");
						
						//Keep track of the winners of the games
						 w_semiFinals[semiFinals/2] = winnersSemiFinals.makeGame(gamesScore[0], gamesScore[1], gamesScore[2], gamesScore[3], w_quarters[semiFinals], 
								 					  w_quarters[semiFinals+1]);
						 
						 //Keep track of the goals depending of the tournament
						 totalGoals[numberOfTournaments][numberOfGame][0] = gamesScore[0]+gamesScore[1]+gamesScore[2]+gamesScore[3];
						 
						 //To store the goals depending the games
						 numberOfGame+=1;
					}
					
					//Final
					System.out.print("\nFINAL ");
				
					//Giving score to the games (randomly)
					gamesScore = scoreGame.theScore();
					
					//Output the score of the games
					System.out.println("[" + w_semiFinals[0] + " "+ (gamesScore[0] + gamesScore[2]) +":" + (gamesScore[1] + gamesScore[3]) +
										" "+w_semiFinals[1]+ "] ");
					
					//Winner of the tournament
					 w_final = winnerFinal.makeGame(gamesScore[0], gamesScore[1], gamesScore[2], gamesScore[3], w_semiFinals[0], 
							   w_semiFinals[1]);
					 
					 //To store goals depending the games
					 totalGoals[numberOfTournaments][numberOfGame][0] = gamesScore[0]+gamesScore[1]+gamesScore[2]+gamesScore[3];
					 
					 //To show the user the winner of the tournament
					 System.out.println("Tournament " + count + ": "+ "The WINNER is: " + w_final);
					 System.out.println();
					 
					 //To set a maximum numbers of tournaments
					 if (numberOfTournaments == 20)
					 	{
						 //If the team didn't win a single tournament in 20 tries
						 System.out.println("Sorry, "+ favTeam + " didn't win in 20 tournaments!\n");
						 //To break out of the loop
						 break;
					 	}
					 
					 //To know if the users favorite team won the tournament
					 if (w_final.equalsIgnoreCase(favTeam))
							favTeamWins = true;
					 //If the users favorite team didn't win the tournament
					 else
						{
						 //One more tournament (useful for the loop)
				 		 count++;
				 		 //One more tournament (useful for the array that stores the total goals)
				 		 numberOfTournaments++;
				 		 //Reset the number of games played
						 numberOfGame*=0;
						}		
				}
					//To tell the user how many tournaments it took to win the tournament
					if (numberOfTournaments < 20)
					System.out.println("\n\nIt took " + (count+1) + " tournament(s) of the game for " + favTeam + " to win!!!");
					
					//GOAL STATS
					System.out.println("GOAL STATS\n");
					
					//To go through the number of tournaments
					for(int i=0; i<(count+1); i++)
						{
						System.out.print("[Tournament " +  i + "]"+ " Total goals: [");
						
						//To go through the match number depending of the number of tournament
						for(int j=0; j<14; j++)
							{System.out.print(totalGoals[i][j][0] +",");}
						
						//To output the last game score (needed to do it out of the loop to get rid of the coma
						System.out.print(totalGoals[i][14][0]);
						
						//Compute and store the average of each tournament
						average = (double)((totalGoals[i][0][0] + totalGoals[i][1][0]+totalGoals[i][2][0]+totalGoals[i][3][0]+totalGoals[i][4][0]+
								totalGoals[i][5][0] + totalGoals[i][6][0] + totalGoals[i][7][0]+totalGoals[i][8][0]+totalGoals[i][9][0]
										+totalGoals[i][10][0]+totalGoals[i][11][0]+totalGoals[i][12][0]+totalGoals[i][13][0]
												+totalGoals[i][14][0])/15.0);
						
						//To keep a single digit after the decimal point
						average = average*10;
						average = (int)average;
						average = (double)(average);
						average = average/10;
						arr_averages[i] = average;
						
						//Output the average
						System.out.print("] [Average: " + average + "]");
						System.out.println();
						}
					
					//Compute and store the overall average
					overallAverage = (arr_averages[0] + arr_averages[1] +arr_averages[2]+arr_averages[3]+arr_averages[4]+arr_averages[5]+
							arr_averages[6]+arr_averages[7]+arr_averages[8]+arr_averages[9]+arr_averages[10]+arr_averages[11]+
							arr_averages[12]+arr_averages[13]+arr_averages[14]+arr_averages[15]+arr_averages[16]+arr_averages[17]+
							arr_averages[18]+arr_averages[19]+arr_averages[20])/numberOfTournaments;
					
					//To keep a single digit after the decimal point
					overallAverage = overallAverage*10;
					overallAverage = (int)overallAverage;
					overallAverage = (double)(overallAverage);
					overallAverage = overallAverage/10;
					
					//To know how many games had a total score over the overall average
					for(int i=0; i<(count+1); i++)
					{
						for(int j=0; j<14; j++)
							if (totalGoals[i][j][0] > overallAverage)
								
								//Increase the number of games by 1
								count2++;
					}
					//Output for the user the overall average and the number of games that had a total goal over it
					System.out.println("\nAverage goals for "+ numberOfTournaments + " tournament(s): "+ overallAverage);
					System.out.println("Total matches in all tournaments over the average goal value: " + count2);
				
				break outer; // END OF TOURNAMENT
					}
				}
			
			//If the user doesn't input a team in the ROUND OF 16
			System.out.println("Your team is not in the Round of 16");
			System.out.print("Do you want to retry? "+'('+ "yes or no" + ')' + " ");
			
			//Asking the user if he wants to retry
			answer = myK.next();
			answer.replaceAll(" ", "");
			if (answer.equalsIgnoreCase("yes"))
				{
				
				//Asking the user to input his favorite team again
				System.out.println("\nThere you go..");
				System.out.print("\nEnter your favorite team: ");
				favTeam = myK.next();
				}
			else
				//To end the loop and exit the program
				again = !again;
		}
		while (again);
		//Thank you message
		System.out.println("\nThank you for the usage of the wonderful Wolrd Cup Simulator!");
		//Keeping Java happy (:
		myK.close();
	}
}
