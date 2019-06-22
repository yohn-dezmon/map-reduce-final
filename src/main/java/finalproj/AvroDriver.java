package finalproj;

import stubs.model.SimpleCard;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.avro.Schema;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.avro.mapreduce.AvroKeyValueOutputFormat;
import org.apache.avro.mapreduce.AvroKeyValueInputFormat;


import finalproj.model.Movie;
import finalproj.model.MovieGenre;





public class AvroDriver extends
Configured implements Tool{	
	
	
	@Override
    public int run(String[] args) throws Exception {

		String input, output;
		if (args.length == 2) {
			input = args[0];
			output = args[1];
		} else {
			System.err.println("Error: input output");
			return -1;
		}
		
		Job job = Job.getInstance();
		job.setJarByClass(AvroDriver.class);
        job.setJobName("Movie Genre Mapper");
		
		FileInputFormat.setInputPaths(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output + "-etl"));
		
		job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(AvroKeyValueOutputFormat.class);
		
        job.setMapperClass(MovieMapper.class);
        job.setNumReduceTasks(0);
        
		AvroJob.setOutputKeySchema(job, Movie.getClassSchema());
		AvroJob.setOutputValueSchema(job, Schema.create(Schema.Type.NULL));

		boolean success = job.waitForCompletion(true);
		
		if (success == false) {
            System.err.println("First phase failed");
            return 1;
        }
		
		Job countJob = Job.getInstance();
		countJob.setJarByClass(AvroDriver.class);
		countJob.setJobName("Counter");
        
        FileInputFormat.setInputPaths(countJob, new Path(output + "-etl"));
		FileOutputFormat.setOutputPath(countJob, new Path(output));
		
		countJob.setInputFormatClass(AvroKeyValueInputFormat.class);
		countJob.setOutputFormatClass(AvroKeyValueOutputFormat.class);
		
		// set mapper
		countJob.setMapperClass(AvroMovieMapper.class);
		// set partitioner
		countJob.setPartitionerClass(MoviePartitioner.class);
		// set number of reduce tasks 
		countJob.setNumReduceTasks(4);
		// set the combiner  
		countJob.setCombinerClass(MovieCombiner.class);
		// set reducer 
		countJob.setReducerClass(MovieReducer.class);
		
		
		countJob.setMapOutputKeyClass(MovieGenre.class);
		countJob.setMapOutputValueClass(SimpleCard.class);
		
		AvroJob.setInputKeySchema(countJob, Movie.getClassSchema());
		AvroJob.setInputValueSchema(countJob, Schema.create(Schema.Type.NULL));
		
		AvroJob.setMapOutputKeySchema(countJob, MovieGenre.getClassSchema());
		AvroJob.setMapOutputValueSchema(countJob, Movie.getClassSchema());
		
		AvroJob.setOutputKeySchema(countJob, MovieGenre.getClassSchema());
		AvroJob.setOutputValueSchema(countJob, Schema.create(Schema.Type.INT));
		
		success = countJob.waitForCompletion(true);
		
		return success ? 0 : 1;
		
	}
	
	public static void main(String[] args) throws Exception { 
		AvroDriver driver = new AvroDriver();	
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
		}
	
	
	}
