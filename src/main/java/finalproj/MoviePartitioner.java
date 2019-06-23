package finalproj;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Partitioner;
import finalproj.model.Movie;
import finalproj.model.MovieGenre;



public class MoviePartitioner extends
	Partitioner<AvroKey<MovieGenre>, AvroValue<Movie>> 
{
	@Override
	public int getPartition(AvroKey<MovieGenre> key, AvroValue<Movie> value,
			int numPartitions) {
		
		if (numPartitions == 4) {
			int movieId = Integer.parseInt(value.datum().getId().toString());
			if (movieId <= 32816) {
				return 0;
			} else if (movieId > 32816 && movieId <= 65631) {
				return 1 % numPartitions;
			} else if (movieId > 65631 && movieId <= 98447) {
				return 2 % numPartitions;
			} else if (movieId > 98447 && movieId <= 131262) {
				return 3 % numPartitions;
			}
		}
		return 0;
		
	}
	
}
		
	 
	

