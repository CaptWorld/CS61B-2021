package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] pianoKeys = new GuitarString[keyboard.length()];
        for (int i = 0; i < pianoKeys.length; i++) {
            pianoKeys[i] = new GuitarString(440 * Math.pow(2, (i - 24) / 12.0));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int indexOfPianoKey = keyboard.indexOf(key);
                if (indexOfPianoKey != -1) {
                    pianoKeys[indexOfPianoKey].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString pianoKey : pianoKeys) {
                sample += pianoKey.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString pianoKey : pianoKeys) {
                pianoKey.tic();
            }
        }
    }
}
