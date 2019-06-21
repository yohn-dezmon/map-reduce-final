package stubs;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import stubs.model.SimpleCard;
import stubs.model.CardSuit;
import stubs.model.GameType;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroKey;


public class RegexCardMapper extends
	Mapper<LongWritable, Text, AvroKey<SimpleCard>, AvroValue<NullWritable>>{
	private static final SimpleCard.Builder cardBuilder = SimpleCard.newBuilder();
	
	
	@Override 
	public void map(LongWritable key, Text values, Context context) 
	throws IOException, InterruptedException {
		
		String inputLine = values.toString();
		
		
		Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})\\s*(\\S*)\\s*(\\S*)\\s*(\\w*)\\s*(\\w*)\\s*(\\d*)");
		Matcher m = pattern.matcher(inputLine);
		
		
		
		if (m.matches()) {
			SimpleCard card = cardBuilder.build();
			card.setDate(m.group(1));
			card.setTime(m.group(2));
			card.setGameId(m.group(3));
			card.setGame(GameType.valueOf(m.group(4)));
			card.setSuit(CardSuit.valueOf(m.group(5).toUpperCase()));
			card.setCardValue(m.group(6));
			
			context.write(new AvroKey<SimpleCard>(card),
					new AvroValue<NullWritable>(NullWritable.get()));
			

			
		}
		
	}

	
}