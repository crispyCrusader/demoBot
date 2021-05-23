package events.util;

import java.time.format.DateTimeFormatter;

import demoBot.Bot;
import events.FakeTyping;
import events.JoinVC;
import events.LeaveVC;
import events.MessageBlock;
import events.MessageRespond;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

// Abstract class that all event classes will extend onto
public abstract class EventObject extends ListenerAdapter{
	
	// Abstract method to retrieve an event's name
	public abstract String getName();
	
	// Abstract method to retrieve an event's purpose / action
	public abstract String getAction();
	
	// Adds all the events to the event listener
	public static void init() {
		Bot.jda.addEventListener(new MessageBlock());
		Bot.jda.addEventListener(new MessageRespond());
		Bot.jda.addEventListener(new FakeTyping());
		Bot.jda.addEventListener(new JoinVC());
		Bot.jda.addEventListener(new LeaveVC());
	}
	// Retrieves the time to use in the dev message
	public String getTimestamp() {
		return (Bot.getCurrentDate().format(DateTimeFormatter.ofPattern("E MM-dd-yyyy")) 
				+ " <" 
				+ Bot.getCurrentTime().format(DateTimeFormatter.ofPattern("hh-mm-ss a")) 
				+ ">");
	}
	
	// Generates a detailed dev message to appear on console, confirming event has executed
	public void devMessage(String name, String action) {
		System.out.println("EVENT: " + getTimestamp() + " executed " + name + ": " + action + ";");
	}
}
