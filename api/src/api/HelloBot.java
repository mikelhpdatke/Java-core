package api;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;



public class HelloBot extends AbilityBot {

	public static String BOT_TOKEN = "585071147:AAHiAK4s0jft8bB-diLJPaUaYYgcHfgboA8";
	public static String BOT_USERNAME = "tomsendervn_bot";
	
	protected HelloBot() {
		super(BOT_TOKEN, BOT_USERNAME);
		// TODO Auto-generated constructor stub
	}


	@Override
	public int creatorId() {
		// TODO Auto-generated method stub
		return 470339598;
	}

	
}
