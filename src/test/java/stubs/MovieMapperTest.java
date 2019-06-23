package stubs;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import finalproj.AvroDriver;
import finalproj.MovieMapper;
import finalproj.model.Movie;
import finalproj.model.MovieGenre;
import org.apache.hadoop.mapreduce.Job;
import java.io.IOException;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.avro.Schema;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.hadoop.io.AvroSerialization;
import org.apache.hadoop.fs.CommonConfigurationKeysPublic;
import org.apache.hadoop.conf.Configuration;


public class MovieMapperTest {
	MapDriver<LongWritable, Text, AvroKey<Movie>, AvroValue<NullWritable>>mapDriver;
	private static final Movie.Builder movieBuilder = Movie.newBuilder();
	
	@Before
	public void setUp() throws IOException {
		MovieMapper movieMap = new MovieMapper();
		mapDriver = MapDriver.newMapDriver(movieMap);
		
		// Copy over the default io.serializations. This allows you to 
	    // deserialize the inputs to the mapper
	    String[] strings = mapDriver.getConfiguration().getStrings("io.serializations");
	    String[] newStrings = new String[strings.length +1];
	    System.arraycopy( strings, 0, newStrings, 0, strings.length );
	    newStrings[newStrings.length-1] = AvroSerialization.class.getName();
	    
	    // Now you have to configure AvroSerialization by specifying the key
	    // writer Schema and the value writer schema.
	    mapDriver.getConfiguration().setStrings("io.serializations", newStrings);
	    mapDriver.getConfiguration().setStrings("avro.serialization.key.writer.schema", Movie.SCHEMA$.toString(true));
	    mapDriver.getConfiguration().setStrings("avro.serialization.value.writer.schema", Schema.create(Schema.Type.NULL).toString(true));
	}

	@Test
	public void testGoodData() throws IOException {
		Movie movie = movieBuilder.build();
		movie.setGenre(MovieGenre.valueOf("Adventure"));
		movie.setId("131248");
		movie.setTitle("Brother Bear 2 (2006)");
		
		Movie movie1 = movieBuilder.build();
		movie1.setGenre(MovieGenre.valueOf("Animation"));
		movie1.setId("131248");
		movie1.setTitle("Brother Bear 2 (2006)");
		
		Movie movie2 = movieBuilder.build();
		movie2.setGenre(MovieGenre.valueOf("Children"));
		movie2.setId("131248");
		movie2.setTitle("Brother Bear 2 (2006)");
		
		Movie movie3 = movieBuilder.build();
		movie3.setGenre(MovieGenre.valueOf("Comedy"));
		movie3.setId("131248");
		movie3.setTitle("Brother Bear 2 (2006)");
		
		Movie movie4 = movieBuilder.build();
		movie4.setGenre(MovieGenre.valueOf("Fantasy"));
		movie4.setId("131248");
		movie4.setTitle("Brother Bear 2 (2006)");
		
		mapDriver.withInput(new LongWritable(), new Text("131248,Brother Bear 2 (2006),Adventure|Animation|Children|Comedy|Fantasy"));
		mapDriver.withOutput(new AvroKey<Movie>(movie), new AvroValue<NullWritable>(NullWritable.get()));
		mapDriver.withOutput(new AvroKey<Movie>(movie1), new AvroValue<NullWritable>(NullWritable.get()));
		mapDriver.withOutput(new AvroKey<Movie>(movie2), new AvroValue<NullWritable>(NullWritable.get()));
		mapDriver.withOutput(new AvroKey<Movie>(movie3), new AvroValue<NullWritable>(NullWritable.get()));
		mapDriver.withOutput(new AvroKey<Movie>(movie4), new AvroValue<NullWritable>(NullWritable.get()));
		
	}
	
	@Test
	public void testBadData() throws IOException {
		Movie movie123 = movieBuilder.build();
		movie123.setGenre(MovieGenre.valueOf("SciFi"));
//		movie.setId("131248");
//		movie.setTitle("Brother Bear 2 (2006)");
		
		
		mapDriver.withInput(new LongWritable(), new Text("131248,Brother Bear 2 (2006),Adventure|Animation|Children|Comedy|Fantasy"));
		mapDriver.withOutput(new AvroKey<Movie>(movie123), new AvroValue<NullWritable>(NullWritable.get()));
		
	}

}
