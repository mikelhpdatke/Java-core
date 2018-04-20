package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.bson.Document;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


public class BotTelegram extends TelegramLongPollingBot {
	public static final String IDCHANNEL = "@racracrac";
	static HelloBot sender = new HelloBot();
	static List<Keo> tracker = new ArrayList<Keo>();
	//db
	static MongoClient mongo = new MongoClient("localhost", 27017);
	static MongoDatabase database = mongo.getDatabase("telegramDb");
	// database.createCollection("sampleCollection");
	//System.out.println("Collection created successfully");
	static MongoCollection<Document> collection = database.getCollection("pairCollection");
	
	//
	public BotTelegram() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getBotUsername() {
		return "tomcryptovn_bot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		
		if (update.hasMessage() && update.getMessage().hasText()) {
			String data = update.getMessage().getText();
			while (!('0' <= data.charAt(data.length() - 1) && data.charAt(data.length() - 1) <= '9'))
			{
				data = new StringBuilder(data).deleteCharAt(data.length() - 1).toString();
			}
			
			String list[] = data.split("\\s+");
			Keo keo = new Keo(list[0], list[1], list[2], list[3], list[4]);

			// insert to db
			try {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(keo);
				collection.insertOne(Document.parse(json));
				System.out.println("Added to db Successfully!\n" + json);
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(IDCHANNEL)
	                .setText("Added pair" + keo.name);
	        
	        int done = 0;
	        while (done == 0) {
		        try {
		            execute(message); // Call method to send the message
		        } catch (TelegramApiException e) {
		            //e.printStackTrace();
		            System.out.println("Error While send mess to Telegram Server!!");
		            continue;
		        }
		        done = 1;
	        }
	        */
	    }
	}

	@Override
	public String getBotToken() {
		
		return "578380163:AAHzzWz-O5-7cEP1JYLq3dDsTh65usZVqhY";
	}
	
	
	public static void sendMessage(String mess) {
		SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(IDCHANNEL)
                .setText(mess);
    	try {
    		sender.execute(message);
		}
    	catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		///Send hello
		int done = 0;
		/*
        while (done == 0) {
        	SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(IDCHANNEL)
                    .setText("Hello I'm Tom's bot = )) Let's go to the moon with me !");
	        try {
	            sender.execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	            System.out.println("Error While send mess to Telegram Server!!");
	            continue;
	        }
	        done = 1;
        }
		*/
		
		
		
		///
		ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        
        BotTelegram mybot = new BotTelegram();
        try {
            botsApi.registerBot(mybot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        // db
        //collection.updateOne(Filters.eq("id", 1), Updates.set("likes", 150));       
        
		//System.out.println("Collection pairCollection selected successfully");
		//collection.insertOne(new Document("description","one"));
        //
        int toSend = 0;
        List<Integer> needDel = new ArrayList<Integer>();
        while (true) {
        	toSend = 0;
        	StringBuilder ret = new StringBuilder("Update from Tom:\n");
        	needDel = new ArrayList<Integer>();
        	
        	FindIterable<Document> iterDoc = collection.find();
        	Iterator it = iterDoc.iterator();
        	while (it.hasNext()) {
    			//System.out.println(it.next());
    			Document tmp = (Document) it.next();
    			
    			
    			ArrayList<Double> mang = (ArrayList<Double>) tmp.get("target");
    			double[] mtarget = new double[mang.size()];
    			for(int i = 0; i < mang.size(); i++)
    				mtarget[i] = mang.get(i);
    			//String[] mang = st.split(",");
    			//double[] mtarget = {0,0,0,0,0};
    			/*
    			for(int i = 0; i < mang.length; i++) {
    				System.out.println("Check target:!! " + mang[i]);
    				mtarget[i] = Double.parseDouble(mang[i]);
    			}
    			*/
    			Keo o = new Keo(tmp.getInteger("id")
    					, tmp.getInteger("status")
    					, tmp.getString("link")
    					, tmp.getString("name")
    					, tmp.getString("exchange")
    					, tmp.getDouble("stoploss")
    					, tmp.getDouble("buyzone")
    					, tmp.getDouble("cur")
    					, mtarget
    					, tmp.getInteger("num")
    					, tmp.getInteger("num_target")
    					, tmp.getInteger("selected"));
    			System.out.println("Watching pair" + o.name);
        		String getsttPair = o.getStatus();
        		System.out.println("Status::" + o.status);
        		// cur && status has been changed
        		// need to be update
        	    collection.updateOne(Filters.eq("id", o.id), Updates.set("status", o.status));       
        	    collection.updateOne(Filters.eq("id", o.id), Updates.set("cur", o.cur));   
        	    collection.updateOne(Filters.eq("id", o.id), Updates.set("selected", o.selected));   
        		if (getsttPair != "") {
        			ret.append(getsttPair).append("\n");
        			toSend++;
        		}
        		
        		if ((o.updateStatus() != -1) && (o.updateStatus() == (o.num_target + 1)) ) {
        			needDel.add(o.id);
        			System.out.println("pair "+ o.name + " will be deleted soon");
        		}
    		}
        	
        	// send to bot
        	if (toSend > 0) {
        		SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(IDCHANNEL)
                        .setText(ret.toString());
        		done = 0;
    	        while (done == 0) {
    		        try {
    		            sender.execute(message); // Call method to send the message
    		        } catch (TelegramApiException e) {
    		            //e.printStackTrace();
    		            System.out.println("Error While send mess to Telegram Server!!");
    		            continue;
    		        }
    		        done = 1;
    	        }
        		
        	}
        	//System.out.println(needDel.size() + );
        	//del
        	for(Integer i : needDel) {
        	    collection.deleteOne(Filters.eq("id", i.intValue())); 
        	}
        	
        	// Sleeping
        	try {
        		System.out.println("Sleeping..");
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }
        
	}
}
