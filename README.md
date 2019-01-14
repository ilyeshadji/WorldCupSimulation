/*
 * This class countains the methods useful for the question_1 program.
 * The first thing it does is to generate randomly the scores for each team
 * Then, it compares the score of each team to determine the winner.
 */


public class Winner 
{
	//Useful variables
	private String winner;
	
	//Score of opponent1
	private int score1;
	
	//Score of opponent2
	private int score2;
	
	//Score of opponent1 in Overtime
	private int score3;
	
	//Score of opponent2 in Overtime
	private int score4;
	private int[] goals;
	
	//Computing the scores
	public int[] theScore()
	{
		//Needed to store the scores
		int[] goals = new int[4];
		
		//Initialized to 0 because they might not be needed (if no overtime)
		score3=0;
		score4=0;
		
		//If need be to go in overtime
		boolean overtime = true;
		
		//Setting the scores values
		score1= (int)(Math.random()*5);
		score2= (int)(Math.random()*5);
		
		//If the game ends before overtime
		if (score1 > score2 || score1 < score2)
			{goals[0]=score1;
			 goals[1]=score2;
			 goals[2]=score3;
			 goals[3]=score4;}
		else 
		{
			//If the scores are still equal after the initialization of the scores during overtime
			while (overtime)
			{
				//Need to initialize the overtime scores
				score3 = (int)(Math.random()*2);
				score4 = (int)(Math.random()*2);
				
				//Checking if there is a winner in overtime
				if (score3 > score4 || score3 < score4 )
					{overtime = !overtime;
					 goals[0]=score1;
					 goals[1]=score2;
					 goals[2]=score3;
					 goals[3]=score4;
					}
			}			
		}
		this.goals=goals;
		return goals;
	}
	
	//Comparing the scores to find the winner
	public String makeGame ( int goal1, int goal2, int goal3, int goal4, String country1, String country2) 
	{
		
		String winner = null;
		boolean overtime =true;
		
		//Defining the winner after regular time
		if (goal1 > goal2)
			{winner = country1;}
		else if (goal1 < goal2)
			{winner = country2;}
		
		//If there is no winner after regular time
		else 
			{
			while (overtime)
				{
				//Defining the winner after overtime
				if (goal3 > goal4)
					{	winner = country1;
					overtime = !overtime;}		
				if (goal3 < goal4)
					{	winner = country2;
					overtime = !overtime;}		
				}			
			}
			//Returning the winner
			this.winner=winner;
			return winner;
	}
}
