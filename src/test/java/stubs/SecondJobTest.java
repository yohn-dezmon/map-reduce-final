package stubs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.hadoop.io.AvroSerialization;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import finalproj.model.Movie;
import finalproj.model.MovieGenre;
import finalproj.AvroMovieMapper;
import finalproj.MovieReducer;

public class SecondJobTest {
    MapDriver<AvroKey<Movie>, AvroValue<NullWritable>, AvroKey<MovieGenre>, AvroValue<Movie>> mapDriver;
    ReduceDriver<AvroKey<MovieGenre>, AvroValue<Movie>, AvroKey<MovieGenre>, AvroValue<Integer>> reduceDriver;
    MapReduceDriver<AvroKey<Movie>, AvroValue<NullWritable>, AvroKey<MovieGenre>, 
    AvroValue<Movie>, AvroKey<MovieGenre>, AvroValue<Integer>>mapReduceDriver;
    

    @Before
    public void setUp() {
        AvroMovieMapper mapper = new AvroMovieMapper();
        mapDriver = MapDriver.newMapDriver(mapper);

        MovieReducer reducer = new MovieReducer();
        reduceDriver = ReduceDriver.newReduceDriver(reducer);

        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper,
                          reducer);
        
        String[] strings = mapDriver.getConfiguration().getStrings("io.serializations");
	    String[] newStrings = new String[strings.length +1];
	    System.arraycopy( strings, 0, newStrings, 0, strings.length );
	    newStrings[newStrings.length-1] = AvroSerialization.class.getName();
	    
	    mapDriver.getConfiguration().setStrings("io.serializations", newStrings);
//	    mapDriver.getConfiguration().setStrings("avro.schema.input.key", Movie.getClassSchema().toString(true));
//	    mapDriver.getConfiguration().setStrings("avro.schema.input.value", Schema.create(Schema.Type.NULL).toString(true));
	    mapDriver.getConfiguration().setStrings("avro.serialization.key.writer.schema", MovieGenre.getClassSchema().toString(true));
	    mapDriver.getConfiguration().setStrings("avro.serialization.value.writer.schema", Movie.getClassSchema().toString(true));
	    mapDriver.getConfiguration().setStrings("avro.serialization.key.reader.schema", Movie.getClassSchema().toString(true));
	    mapDriver.getConfiguration().setStrings("avro.serialization.value.reader.schema", Schema.create(Schema.Type.NULL).toString(true));
	    
	    
	    String[] strings1 = mapReduceDriver.getConfiguration().getStrings("io.serializations");
	    String[] newStrings1 = new String[strings.length +1];
	    System.arraycopy( strings1, 0, newStrings1, 0, strings1.length );
	    newStrings[newStrings1.length-1] = AvroSerialization.class.getName();
	    
	    mapReduceDriver.getConfiguration().setStrings("io.serializations", newStrings);
	    mapReduceDriver.getConfiguration().setStrings("avro.serialization.key.writer.schema", MovieGenre.getClassSchema().toString(true));
	    mapReduceDriver.getConfiguration().setStrings("avro.serialization.value.writer.schema", Schema.create(Schema.Type.INT).toString(true));
	    
	    String[] strings2 = reduceDriver.getConfiguration().getStrings("io.serializations");
	    String[] newStrings2 = new String[strings2.length +1];
	    System.arraycopy( strings2, 0, newStrings2, 0, strings2.length );
	    newStrings[newStrings2.length-1] = AvroSerialization.class.getName();
	    
	    reduceDriver.getConfiguration().setStrings("io.serializations", newStrings);
	    reduceDriver.getConfiguration().setStrings("avro.serialization.key.writer.schema", MovieGenre.getClassSchema().toString(true));
	    reduceDriver.getConfiguration().setStrings("avro.serialization.value.writer.schema", Schema.create(Schema.Type.INT).toString(true));
	    

    }

    @Test
    public void testMapper() throws IOException {
    	Movie.Builder movieBuilder = Movie.newBuilder();
        Movie movie = movieBuilder.build();
        String genre = "Adventure";
        String id = "131248";
        String title = "Brother Bear 2 (2006)";
        movie.setGenre(MovieGenre.valueOf(genre));
		movie.setId(id);
		movie.setTitle(title);
		System.out.println(movie.getGenre());
		// the error occurs at withInput... AvroTypeException, not an enum... when obviously its not SUPPOSED to be an enum...
        mapDriver
        .withInput(new AvroKey<Movie>(movie), new AvroValue<NullWritable>(NullWritable.get()));
        mapDriver.withOutput(new AvroKey<MovieGenre>(MovieGenre.Adventure), new AvroValue<Movie>(movie));
        mapDriver.runTest();
    }
    
//    @Test
//    public void testMapReduce() thorws IOException {
//    	
//    	
//    }
    }


