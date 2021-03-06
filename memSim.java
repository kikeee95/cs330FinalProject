import java.util.ArrayList;
import java.util.Scanner;
/*
  This is the main file for the memSim project.

  It starts up the CPU, RAM, HDD, and any other accessories specified by the user

  Authors: Pat Baumgardner, Adam Dachenhausen, Shah Syed
 */

class memSim extends Thread{
    //A flag to tell our components if the system is done running
    public static boolean done;
    
    //An array to hold all the hard drives in the system
    HDD[] hardDrives;

    //An array to hold all the ram sticks in the system
    RAM[] rams;
    
    ArrayList processes = new ArrayList<process>();
    CPU cpu;
    
    /**
       Starts the simulation of our memory management.
       First prompts the user for data
       
     */
    public static void main(String args[]){
	//Here we will prompt the user for data
	memSim sim = initialUserPrompt();
	
	while(!done){
	   sim.promptTree();
	}
    }
    
    /**
       A constructor for the memSim object
     **/
    public memSim(int numHDD, int hDDSize, int numHDDPlatters, int numRam, int rAMSize, int cacheSize){
	//First, we need to allow everything to know that we are starting up, aka not done
	done = false;
	
	//Let's initialize the number of harddrives, and their size
	hardDrives = new HDD[numHDD];

	for(int i = 0; i < numHDD; i++){
	    hardDrives[i] = new HDD(hDDSize, numHDDPlatters);
	    hardDrives[i].start();
	}

	//Now let's initialize our RAM
	rams = new RAM[numRam];

	for(int i = 0; i < numRam; i++){
	    rams[i] = new RAM(rAMSize);
	    rams[i].start();
	}

	//Now let's initialize our cacheSize
	cpu = new CPU(1, cacheSize);
	cpu.start();
    }
    
    /**
       Prompts the user for specifics about the system.
       Then initializes the system and returns it

       @return a memSim object specified by the user's requests
     **/
    private static memSim initialUserPrompt(){
	Scanner scnr = new Scanner(System.in);

	//Let's ask the user for system parameters
	System.out.println("How many Hard Drives would you like?");
	String input = scnr.nextLine();
	int numHDD = Integer.parseInt(input);

	System.out.println("How many platters should each hard drive have?");
	input = scnr.nextLine();
	int numPlatters = Integer.parseInt(input);

	System.out.println("And how big (in bytes) should each one be?");
	input = scnr.nextLine();
	int hDDSize = Integer.parseInt(input);

	System.out.println("How many RAM sticks would you like?");
	input = scnr.nextLine();
	int numRAM = Integer.parseInt(input);

	System.out.println("And how big (in bytes) should each one be?");
	input = scnr.nextLine();
	int rAMSize = Integer.parseInt(input);

	System.out.println("Finally, how big (in bytes) would you like your CPU cache to be?");
	input = scnr.nextLine();
	int cacheSize = Integer.parseInt(input);

	//And finally initialize the system
	return new memSim(numHDD,hDDSize,numPlatters,numRAM,rAMSize,cacheSize);
    }

    /**
       Gives the user a list of options to choose their next move with the simulator,
       and acts accordingly.

       
     **/
    private void promptTree(){
	//First print the user's options
	System.out.println("What would you like to do next?");
	System.out.println("1.\tMove Data");
	System.out.println("2.\tRead Data");
	System.out.println("3.\tWrite Data");
	System.out.println("4.\tHelp");
	System.out.println("5.\tExit");
	System.out.print("Please enter the number of your selection: ");
	
	//Then wait for their response and tokenize it
	Scanner scnr = new Scanner(System.in);
	String input = scnr.nextLine();
	String[] tokens = input.split(" ");
	String keystone = tokens[0];
	
	//Process the response
	if(keystone.equalsIgnoreCase("1")|keystone.equalsIgnoreCase("move")){

	}
	else if(keystone.equalsIgnoreCase("2")|keystone.equalsIgnoreCase("read")){

	}
	else if(keystone.equalsIgnoreCase("3")|keystone.equalsIgnoreCase("write")){

	}
	else if(keystone.equalsIgnoreCase("4")|keystone.equalsIgnoreCase("help")){
	    displayHelp(tokens[1]);
	}
	else if(keystone.equalsIgnoreCase("5")|keystone.equalsIgnoreCase("exit")){
	    done = true;
	}
	else{
	    //We didn't understand the instruction, so let's just tell the user, and we will return
	    //Then our parent will just recall us.
	    System.out.println("Your choice was not understood. Please only enter a number");
	    displayHelp("");
	}
	return;
    }
    /**
       A helper method to display a user help menu
     **/
    private static void displayHelp(String input){
	System.out.println();
	System.out.println();
	System.out.println("Java Based Memory Latency Simulator");
	System.out.println("Developed by Pat Baumgardner, Adam Dachenhausen, Shah Syed");
	System.out.println("Supported commands: ");
	System.out.println("move");
	System.out.println("read");
	System.out.println("write");
	System.out.println("help");
	System.out.println("exit");
	System.out.println("For help with a specific command type 'help [command]'");
	if(input == null){
	    return;
	    }
	else if(input.equalsIgnoreCase("move")){
	    System.out.println("Moves data from one specified location to another");
	}
	else if(input.equalsIgnoreCase("read")){
	    System.out.println("Reads data from specifed location");
	}
	else if(input.equalsIgnoreCase("write")){
	    System.out.println("Writes data to specified location");
	}
	else if(input.equalsIgnoreCase("help")){
	    System.out.println("Displays the help message");
	}
	else if(input.equalsIgnoreCase("exit")){
	    System.out.println("Exits the simulator");
	}
	else{
	    System.out.println("Command not found");
	}
	System.out.println();
	System.out.println();
	return;
    }
}
