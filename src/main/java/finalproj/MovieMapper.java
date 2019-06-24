package finalproj;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
// before this was .mapreduce.Mapper...
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroKey;
import org.apache.hadoop.io.NullWritable;
import java.io.IOException;
import finalproj.model.Movie;
import finalproj.model.MovieGenre;


//import org.apache.log4j.Logger;


public class MovieMapper extends
Mapper<LongWritable, Text, AvroKey<Movie>, AvroValue<NullWritable>>
{	
	private static final Movie.Builder movieBuilder = Movie.newBuilder();
	private static final Logger logger = Logger.getLogger(MovieMapper.class);
	
	@Override
	public void map(LongWritable key, Text values, Context context) throws
	IOException, InterruptedException {
		
		if (key.get() == 0) {
			return;
		} else {
		/* line example(s) 
		 * 131256,"Feuer, Eis & Dosenbier (2002)",Comedy
		 * 131248,Brother Bear 2 (2006),Adventure|Animation|Children|Comedy|Fantasy
		*/ 
		String row = values.toString();
		/* 
		 * (?:...) Non-capturing group! so we don't want anything that begins/end
		 * with quotations! :D b/c these commmas are part of the title.
		 * the -1 indicates.. that the array can be of any length, and that the pattern will be applied
		 * as many times as possible 
		 */
		String[] columns = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1);
		
		// There should only be three groups columns[0],...columns[2]
		// so long anything that has a columns.length != 3 
		if (columns.length != 3) {
			logger.info("Mapping error: "+row);
			
		} else if ((columns.length == 3) && columns[2] == "genres") {
			logger.info("Column Titles: " + row);
			
		}
		else if ((columns.length == 3) && columns[2] != "genres") {
			String id = columns[0];
			String title = columns[1];
			
			String genrePreSplit = columns[2];
			String[] genres = genrePreSplit.split("\\|");
			
			for (String genre: genres) {
				genre = genre.replace("-",  "").replace(" ", "").
						replace("(", "").replace(")", "");
				Movie movie = movieBuilder.build();
				movie.setGenre(MovieGenre.valueOf(genre));
				movie.setId(id);
				movie.setTitle(title);
				context.write(new AvroKey<Movie>(movie), new AvroValue<NullWritable>(NullWritable.get()));
			}
		}
		
		}
		
		
	}
	}

