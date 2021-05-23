package demoBot;

import javax.security.auth.login.LoginException;

// Initializes the bot
public class Main {
	
	public static void main(String [] args) throws LoginException, InterruptedException {
		
		// Launches the bot
		boolean status = Bot.launch();
		
		if (status == true)
			Bot.botControlChannel.sendMessage("I have successfully launched. Hello!").queue();
	}
}
