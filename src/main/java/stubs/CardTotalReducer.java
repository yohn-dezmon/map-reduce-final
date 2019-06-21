package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import stubs.model.CardSuit;

import stubs.model.SimpleCard;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroKey;

public class CardTotalReducer extends
	Reducer<AvroKey<CardSuit>, AvroValue<SimpleCard>, AvroKey<CardSuit>, AvroValue<Integer>>{
	
	@Override
	public void reduce(AvroKey<CardSuit> key, Iterable<AvroValue<SimpleCard>> values,
			Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		
		for (AvroValue<SimpleCard> value : values) {
			sum += Integer.parseInt(value.datum().getCardValue().toString());
		}
		
		// interesting that you don't need the .datum() here but I guess this
		// makes sense since we already extracted the value in the previous step.
		context.write(key,  new AvroValue<Integer>(sum));
		
	}

}
