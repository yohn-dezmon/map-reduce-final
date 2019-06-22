package finalproj;

import java.io.IOException;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.mapreduce.lib.map.WrappedMapper.Context;
import org.apache.hadoop.mapreduce.Mapper;
import finalproj.model.Movie;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.NullWritable;
import finalproj.model.MovieGenre;

public class AvroMovieMapper extends
Mapper<AvroKey<Movie>, AvroValue<NullWritable>, AvroKey<MovieGenre>, AvroValue<Movie>> {
	
	@Override
	public void map(AvroKey<Movie> key, AvroValue<NullWritable> values, Context context) throws
	IOException, InterruptedException {
		
		context.write(new AvroKey<MovieGenre>(key.datum().getGenre()), new AvroValue<Movie>(key.datum()));
	}

}
