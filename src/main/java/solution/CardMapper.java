package solution;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;

import solution.model.Card;
import solution.model.Suit;

public class CardMapper
    extends
    Mapper<AvroKey<Card>, AvroValue<NullWritable>, AvroKey<Suit>, AvroValue<Card>> {

    @Override
    public void map(AvroKey<Card> key, AvroValue<NullWritable> value,
                    Context context) throws IOException, InterruptedException {
        // Emit and group based on suit
        context.write(new AvroKey<Suit>(key.datum().getSuit()),
                      new AvroValue<Card>(key.datum()));
    }
}