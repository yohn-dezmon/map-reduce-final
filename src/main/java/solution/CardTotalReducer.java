package solution;

import java.io.IOException;

import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.mapreduce.Reducer;

import solution.model.Card;
import solution.model.Suit;

public class CardTotalReducer
    extends
    Reducer<AvroKey<Suit>, AvroValue<Card>, AvroKey<Suit>, AvroValue<Integer>> {

    @Override
    public void reduce(AvroKey<Suit> key,
                       Iterable<AvroValue<Card>> values, Context context)
    throws IOException, InterruptedException {
        int sum = 0;

        // Go through all values to sum up card values for a card suit
        for (AvroValue<Card> value : values) {
            sum += Integer.parseInt(value.datum().getCard()
                                    .toString());
        }

        // Emit the count based on suit
        context.write(key, new AvroValue<Integer>(sum));
    }
}
