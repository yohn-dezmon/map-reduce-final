package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import stubs.model.CardSuit;
import stubs.model.GameType;
import stubs.model.SimpleCard;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;


public class CardMapper extends 
	Mapper<AvroKey<SimpleCard>, AvroValue<NullWritable>, AvroKey<CardSuit>, AvroValue<SimpleCard>> {
	
		
		@Override
		public void map(AvroKey<SimpleCard> key, AvroValue<NullWritable> value, Context context) throws
		IOException, InterruptedException {
			
				context.write(new AvroKey<CardSuit>(key.datum().getSuit()), 
								new AvroValue<SimpleCard>(key.datum()));
				
				
			}
		}

