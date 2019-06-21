package solution;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import solution.model.Card;
import solution.model.GameTypes;
import solution.model.Suit;

public class RegexCardMapper extends
    Mapper<LongWritable, Text, AvroKey<Card>, AvroValue<NullWritable>> {
    private static final Card.Builder cardBuilder = Card.newBuilder();

    /**
     * Example line:<br>
     * 2015-01-10 00:00:00  1ea7fc17-7cf0-486d-8b8b-ad905e0d7a7a    PaiGow  Club    7
     */
    private static Pattern inputPattern =
        Pattern.compile("(.{19})\\s+([0-9a-f\\-]+)\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)");

    @Override
    public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {
        // Convert the incoming line to a string and break on tabs
        String inputLine = value.toString();

        Matcher inputMatch = inputPattern.matcher(inputLine);

        // Only emit those lines passing the regex
        if (inputMatch.matches()) {
            Card card = cardBuilder.build();
            card.setStartTime(inputMatch.group(1));
            card.setGameId(inputMatch.group(2));

            card.setGameType(GameTypes.valueOf(inputMatch.group(3)));
            card.setSuit(Suit.valueOf(inputMatch.group(4).toUpperCase()));

            card.setCard(inputMatch.group(5));

            // Emit and group based on game type
            context.write(new AvroKey<Card>(card),
                          new AvroValue<NullWritable>(NullWritable.get()));
        }
    }
}