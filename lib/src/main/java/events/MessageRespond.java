package events;

import events.util.EventObject;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

// Responds to certain keywords
public class MessageRespond extends EventObject{

	String keyword;
	String user;
	
	public String getName() {
		return "MessageRespond";
	}

	public String getAction() {
		return "Demo Bot responded to \"" + keyword + "\" sent by \"" + user + "\"";
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		// Message from user
		String message = event.getMessage().getContentRaw().toLowerCase();
		this.user = event.getAuthor().getName();
		
		// Variable to check if Demo Bot responded to a keyword
		boolean messageResponded = false;
		
		// Verifies the message was not sent by a bot
		if (!event.getMember().getUser().isBot())
		{
			// Keywords:
			// Hello
			if (message.contains("hello") || (message.matches(".*\\bhi\\b.*")))
			{
				event.getChannel().sendMessage("Hello there!").queue();
				messageResponded = true;
				this.keyword = "hello\" or \"hi";
			}
			// Bye
			if (message.contains("bye"))
			{
				event.getChannel().sendMessage("Lol, I'm here to stay!").queue();
				messageResponded = true;
				this.keyword = "bye";
			}
			
			// Checks if Demo Bot responded to a message
			if (messageResponded)
			{
				// Prints dev message
				super.devMessage(getName(), getAction());
			}
		}
	}
}
