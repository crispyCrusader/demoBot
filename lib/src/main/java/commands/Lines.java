package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import commands.util.CommandObject;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

// Returns the number of lines and files of code that makes up the bot
public class Lines extends CommandObject {

	// Number of lines and files
	public static int linesOfCode = 0;
	public static int numberOfFiles = 0;
	
	public String getName() {
		return "lines";
	}

	public String extraDetails() {
		return "";
	}

	public String getArgs() {
		return "";
	}

	public String getType() {
		return "Dev";
	}

	public String getDesc() {
		return "prints number of files and lines of code";
	}

	public boolean getAdmin() {
		return false;
	}

	public void execute(Guild guild, TextChannel textChannel, User user, String[] arg) {
		// If the static variable is 0, start traversing the files to count number of files and LoC
		if (linesOfCode == 0)
		{
			File file = new File("src/main/java");
			File [] dir = file.listFiles();
			
			traverseDirectory(dir, 0);
		}
		
		textChannel.sendMessage("I am made of " + linesOfCode + " lines of code within " + numberOfFiles + " files.").queue();
	}

	public void traverseDirectory(File [] file, int i) {
		// If there are no more files, stop the current recursive incident
		if (i == file.length)
			return;
		
		// If file is a folder, recursively look at the files inside
		if (file[i].isDirectory())
		{
			traverseDirectory(file[i].listFiles(), 0);
		}
		// If file is not a folder, increment number of files and count the lines of code
		else 
		{
			try {
				linesOfCode += Files.lines(Path.of(file[i].getPath())).count();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			numberOfFiles += 1;
		}
		
		// Go to the next file in the directory
		traverseDirectory(file, ++i);
	}
}
