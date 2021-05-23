package commands.util;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import commands.Help;
import commands.Join;
import commands.Leave;
import commands.Lines;
import commands.ShutDown;
import commands.Activity;
import demoBot.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

// Abstract class that all command classes will extend onto
public abstract class CommandObject {
	
	public static ArrayList<String> types;
	public static ArrayList<CommandObject> commands;
	
	// Abstract method to retrieve the command name
	public abstract String getName();
	
	// Extra details used in the dev message
	public abstract String extraDetails();
	
	// Abstract method to retrieve the command arguments
	public abstract String getArgs();
	
	// Abstract method to retrieve the type of command
	public abstract String getType();
	
	// Abstract method to retrieve the command's description
	public abstract String getDesc();
	
	// Abstract method to check whether the command is for admin use only
	public abstract boolean getAdmin();
	
	// Abstract method that executes each command
	public abstract void execute(Guild guild, TextChannel textChannel, User user, String [] arg);
	
	// Adds all the commands to the static "commands" array
	public static void init() {
		
		// Required arrays
		commands = new ArrayList<>();
		types = new ArrayList<>();
		
		// Basic commands
		commands.add(new Help());
		commands.add(new Join());
		commands.add(new Leave());
		commands.add(new Activity());
		
		// Dev commands
		commands.add(new Lines());
		commands.add(new ShutDown());
		
		getTypes();
	}
	
	// Cycles through all command objects to find the command types
	public static void getTypes() {
		for (CommandObject command : CommandObject.commands)
		{
			// If the types do not match, create a new entry in the array
			if (!(types.contains(command.getType())))
				types.add(command.getType());
		}
	}
	
	// Retrieves the time to be used in the dev message on a command's execution
	public String getTimestamp() {
		return (Bot.getCurrentDate().format(DateTimeFormatter.ofPattern("E MM-dd-yyyy")) 
				+ " <" 
				+ Bot.getCurrentTime().format(DateTimeFormatter.ofPattern("hh-mm-ss a")) 
				+ ">");
	}
	
	// Prints the dev message on a command's execution
	public void devMessage(String name, String details) {
		System.out.println("COMMAND: " + getTimestamp() + " executed " + name + "; " + details);
	}
	
	// Used in the handler to detmine if commands exist in the array
	public boolean compare(String cmd) {
		return cmd.equalsIgnoreCase(getName());
	}
	
	// Used to determine if user can run admin commands
	public boolean hasAdminRole(Member member) {
		// Determines if the command requires admin privileges to execute
		if (getAdmin())
			return member.getRoles().contains(Bot.adminRole);
		else
			return true;
	}
}
