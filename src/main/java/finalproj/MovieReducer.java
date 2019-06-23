package finalproj;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import finalproj.model.MovieGenre;
import finalproj.model.Movie;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroKey;

public class MovieReducer extends 
Reducer<AvroKey<MovieGenre>, AvroValue<Movie>, AvroKey<MovieGenre>, AvroValue<Integer>>{

	@Override
	public void reduce(AvroKey<MovieGenre> key, Iterable<AvroValue<Movie>> value,
			Context context) throws IOException, InterruptedException {
		int sum = 0;
	
		for (AvroValue<Movie> movie : value) {
			int movieCount = Integer.parseInt(movie.datum().getCount().toString());
			sum +=  movieCount;
		}
		System.out.println(key + ": value: " + sum);
		context.write(key,  new AvroValue<Integer>(sum));
		
	}
	}
	


