package stubs;
// i NEED TO KEEP WORKING ON THIS, OR FIND INFO ON IT.
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import stubs.model.SimpleCard;
import stubs.model.CardSuit;
import stubs.model.GameType;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroKey;


public class MapperTest {
	MapDriver<LongWritable, Text, AvroKey<CardSuit>, AvroValue<SimpleCard>> mapDriver;
	private static SimpleCard.Builder cardBuilder = SimpleCard.newBuilder();
	
	@Before
	public void setUp() {
		RegexCardMapper cardMapper = new RegexCardMapper();
		
		mapDriver = MapDriver.newMapDriver(cardMapper);
		}

	@Test
	public void mapTestTrue() throws IOException {
		SimpleCard card = cardBuilder.build();
		// what's the difference between addInput and withInput?
		mapDriver.withInput(new LongWritable(), new Text("2015-01-14 23:59:17	e4c07e72-57de-45ef-b428-9c51617665ac	TexasHoldem	Diamond	4"));
		mapDriver.withOutput(new AvroKey<CardSuit>(CardSuit.DIAMOND), new AvroValue<SimpleCard>(card));
		mapDriver.runTest();
	}
	
	@Test(expected=IOException.class)
	public void mapTestFalse() throws IOException {
		SimpleCard card = cardBuilder.build();
		// what's the difference between addInput and withInput?
		mapDriver.withInput(new LongWritable(), new Text("2015-01-14 23:59:17	e4c07e72-57de-45ef-b428-9c51617665ac	TexasHoldem	Diamond	4"));
		mapDriver.withOutput(new AvroKey<CardSuit>(CardSuit.CLUB), new AvroValue<SimpleCard>(card));
		mapDriver.runTest();
	}

}
