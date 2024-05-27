package Core.Actions;

import Utilities.NetworkUtils;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonActions {

    public static void clearTextArea(JTextArea textArea) {
        textArea.setText("");
    }

    public static void readText(JTextArea textArea) {
        String text = textArea.getText(); // Get the text from the textArea
        if (text.isBlank()) {
            JOptionPane.showMessageDialog(null, "There is no text to read.");
            return;
        }

        if (!NetworkUtils.isInternetAvailable()) {
            JOptionPane.showMessageDialog(null, "Unable to Read. Please check your connection.");
            return;
        }

        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.LINEAR16)
                    .build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            ByteString audioContents = response.getAudioContent();

            String outputPath = "output.wav";
            try (OutputStream out = new FileOutputStream(outputPath)) {
                out.write(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.wav\"");
            }

            // Play the audio
            try {
                File audioFile = new File(outputPath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip audioClip = AudioSystem.getClip();

                // Add a line listener to the clip that waits until the clip is fully loaded before starting playback
                audioClip.addLineListener(new LineListener() {
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            synchronized (audioClip) {
                                audioClip.notify();
                            }
                        }
                    }
                });

                audioClip.open(audioStream);
                audioClip.start();

                // Wait for the audio to finish
                synchronized (audioClip) {
                    audioClip.wait();
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void speak(String text, JToggleButton textToSpeechToggleButton) {
        // Your existing speak method code goes here...
        if (!textToSpeechToggleButton.isSelected()) {
            return;
        }

        if (!NetworkUtils.isInternetAvailable()) {
            JOptionPane.showMessageDialog(null, "No internet connection. Please check your connection.");
            return;
        }
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.LINEAR16)
                    .build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            ByteString audioContents = response.getAudioContent();

            String outputPath = "output.wav";
            try (OutputStream out = new FileOutputStream(outputPath)) {
                out.write(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.wav\"");
            }

            // Play the audio
            try {
                File audioFile = new File(outputPath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip audioClip = AudioSystem.getClip();

                // Add a line listener to the clip that waits until the clip is fully loaded before starting playback
                audioClip.addLineListener(new LineListener() {
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            synchronized (audioClip) {
                                audioClip.notify();
                            }
                        }
                    }
                });

                audioClip.open(audioStream);
                audioClip.start();

                // Wait for the audio to finish
                synchronized (audioClip) {
                    audioClip.wait();
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkAnswerAndNext(String[] correctWords, String userAnswer, JTabbedPane tabbedPane, JTextArea textArea, boolean useDelimiter) {
        String delimiter = useDelimiter ? " " : "";
        String correctAnswer = String.join(delimiter, correctWords);

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(null, "Correct!");
            int selectedIndex = tabbedPane.getSelectedIndex();

            if (selectedIndex + 1 < tabbedPane.getTabCount()) {
                tabbedPane.setSelectedIndex(selectedIndex + 1);
                textArea.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect!");
            textArea.setText("");
        }
    }

    public static void checkAnswerAndNextMultipleChoice(JTabbedPane jTabbedPane1, JRadioButton[] radioButtons, String[] correctAnswers) {
        String[] userAnswers = new String[radioButtons.length];
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isSelected()) {
                userAnswers[i] = radioButtons[i].getText();
            }
        }

        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i].equals(userAnswers[i])) {
                score++;
            }
        }

        JOptionPane.showMessageDialog(null, "You got " + score + "/" + correctAnswers.length);

        // Proceed to the next tab regardless of the score
        int selectedIndex = jTabbedPane1.getSelectedIndex();
        if (selectedIndex + 1 < jTabbedPane1.getTabCount()) {
            jTabbedPane1.setSelectedIndex(selectedIndex + 1);
        }
    }

    public static void toggleTextToSpeech(JToggleButton textToSpeechToggleButton) {
        if (textToSpeechToggleButton.isSelected()) {
//            textToSpeechToggleButton.setBackground(Color.RED);
            textToSpeechToggleButton.setForeground(Color.RED);
        } else {
            textToSpeechToggleButton.setBackground(null);
            textToSpeechToggleButton.setForeground(null);
        }
    }

    public static void playVideoLesson(int durationInSeconds, String lessonFileName, boolean isVowel) {
        JFrame frame = new JFrame("Video Player");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        frame.add(mediaPlayerComponent, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String currentDirectory = System.getProperty("user.dir");
        String videoFilePath = currentDirectory + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Video_Lessons" + File.separator + (isVowel ? "Vowels" : "Consonants") + File.separator + lessonFileName + ".mp4";
        mediaPlayerComponent.mediaPlayer().media().play(videoFilePath);

        // Schedule a task to stop the video after the durationInSeconds of the video
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mediaPlayerComponent.mediaPlayer().controls().stop();  // Stop the video
                frame.dispose();  // Close the window
            }
        }, durationInSeconds * 1000);

        // Add a window listener to release the media player when the JFrame is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.mediaPlayer().release();
            }
        });
    }
}