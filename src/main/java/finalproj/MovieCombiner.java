package finalproj;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import finalproj.model.MovieGenre;
import stubs.model.SimpleCard;
import finalproj.model.Movie;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroKey;

public class MovieCombiner extends 
Reducer<AvroKey<MovieGenre>, AvroValue<Movie>, AvroKey<MovieGenre>, AvroValue<Movie>>{

	@Override
	public void reduce(AvroKey<MovieGenre> key, Iterable<AvroValue<Movie>> value,
			Context context) throws IOException, InterruptedException {
		

		int sum = 0;
		
		for (AvroValue<Movie> movie: value) {
			sum = 1;
			movie.datum().setCount(sum);
		}
		
		for (AvroValue<Movie> movie: value) {
			context.write(key, movie);
		}
		
		
		
	}
	}
	

