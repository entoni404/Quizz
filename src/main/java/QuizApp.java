import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.swing.*;

public class QuizApp implements ActionListener {

    QuizObj[] quizObjArr = (QuizObj[]) quizApiArray();


    char guess;
    char correctAnswer;
    int index;
    int correctGuesses = 0;
    int totalQuestions = quizObjArr.length;
    int result;
    int seconds = 10;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField(); // question
    JTextArea textArea = new JTextArea(); // current question
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answerALabel = new JLabel();
    JLabel answerBLabel = new JLabel();
    JLabel answerCLabel = new JLabel();
    JLabel answerDLabel = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel secondsLeft = new JLabel();
    JTextField numberRight = new JTextField();
    JTextField percentage = new JTextField();

    Timer deadline = new Timer(1500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            secondsLeft.setText(String.valueOf(seconds));
            if(seconds <=0 ){
                showAnswers();
            }
        }
    });


    // constructor
    public QuizApp() throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.getContentPane().setBackground(new Color(50, 68, 168));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(20, 10, 650, 50); // decides where the textfield(question) position in the frame is
        textField.setBackground(new Color(12, 2, 87));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Dialog", Font.PLAIN, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setText("First Question");


        textArea.setBounds(20, 70, 650, 80); // decides where the textArea(question) position in the frame is
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.darkGray);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 20));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        textArea.setText("Velma Kelly and Roxie Hart are the protagonists of which Oscar winning movie?");

        buttonA.setBounds(30, 200, 60, 60);
        buttonA.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(30, 300, 60, 60);
        buttonB.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(30, 400, 60, 60);
        buttonC.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(30, 500, 60, 60);
        buttonD.setFont(new Font("Helvetica", Font.PLAIN, 20));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answerALabel.setBounds(120, 200, 450, 60);
        answerALabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        answerALabel.setBackground(Color.WHITE);
        answerALabel.setForeground(Color.WHITE);
        answerALabel.setText("Dreamgirls");

        answerBLabel.setBounds(120, 300, 450, 60);
        answerBLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        answerBLabel.setBackground(Color.WHITE);
        answerBLabel.setForeground(Color.WHITE);
        answerBLabel.setText("Cabaret");

        answerCLabel.setBounds(120, 400, 450, 60);
        answerCLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        answerCLabel.setBackground(Color.WHITE);
        answerCLabel.setForeground(Color.WHITE);
        answerCLabel.setText("Chicago");

        answerDLabel.setBounds(120, 500, 450, 60);
        answerDLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        answerDLabel.setBackground(Color.WHITE);
        answerDLabel.setForeground(Color.WHITE);
        answerDLabel.setText("All That Jazz");

        secondsLeft.setBounds(635, 611, 50, 50);
        secondsLeft.setBackground(Color.BLACK);
        secondsLeft.setForeground(Color.WHITE);
        secondsLeft.setFont(new Font("Helvetica", Font.PLAIN, 20));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(seconds));

        timeLabel.setBounds(535, 610, 100, 50);
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("Time Left");

        numberRight.setBounds(250, 280, 200, 70);
        numberRight.setBackground(Color.darkGray);
        numberRight.setForeground(Color.WHITE);
        numberRight.setFont(new Font("Helvetica", Font.PLAIN, 20));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setEditable(false);

        percentage.setBounds(250, 350, 200, 70);
        percentage.setBackground(Color.darkGray);
        percentage.setForeground(Color.WHITE);
        percentage.setFont(new Font("Helvetica", Font.PLAIN, 20));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setEditable(false);



//        frame.add(numberRight);
//        frame.add(percentage);
        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(answerALabel);
        frame.add(answerBLabel);
        frame.add(answerCLabel);
        frame.add(answerDLabel);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        nextQuestion();

    }

    // fetch API method
    public Object[] quizApiArray() {

        QuizObj[] objectArray = new QuizObj[0];


        try {
            URL url = new URL("https://opentdb.com/api.php?amount=5&type=multiple");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestMethod("GET");
            connection.connect();


            // checking if connection is successful
            int responseCode = connection.getResponseCode();


            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                String jsonString = informationString.delete(0, 29).toString();

//                System.out.println(jsonString);


                ObjectMapper objectMapper = new ObjectMapper();
                objectArray = objectMapper.readValue(jsonString, QuizObj[].class);


//                 outputs the object keys
//                for (QuizObj obj : objectArray) {
//                    System.out.println(obj.getQuestion());
//                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectArray;
    }

    //method - next question

    public void nextQuestion() {


        if (index >= totalQuestions) {
            results();
        }
        else {
            textField.setText("Question: " + (index + 1));
            textArea.setText(quizObjArr[index].getQuestion());

            String[] combinedAnswers = quizObjArr[index].allQuestions();
            ArrayList<String> combinedAnswersList = new ArrayList<>(Arrays.asList(combinedAnswers));

            answerALabel.setText(combinedAnswersList.get(new Random().nextInt(combinedAnswersList.size())));
            int targetAIndex = combinedAnswersList.indexOf(answerALabel.getText());
            combinedAnswersList.remove(targetAIndex);


            answerBLabel.setText(combinedAnswersList.get(new Random().nextInt(combinedAnswersList.size())));
            int targetBIndex = combinedAnswersList.indexOf(answerBLabel.getText());
            combinedAnswersList.remove(targetBIndex);

            answerCLabel.setText(combinedAnswersList.get(new Random().nextInt(combinedAnswersList.size())));
            int targetCIndex = combinedAnswersList.indexOf(answerCLabel.getText());
            combinedAnswersList.remove(targetCIndex);

            answerDLabel.setText(combinedAnswersList.get(0));

            deadline.start();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource() ==buttonA){
                if(answerALabel.getText() == quizObjArr[index].getCorrect_answer()){
                    correctGuesses++;
                }
        }
        if(e.getSource() ==buttonB){
                if(answerBLabel.getText() == quizObjArr[index].getCorrect_answer()){
                    correctGuesses++;
                }
        }
        if(e.getSource() ==buttonC){
            if(answerCLabel.getText() == quizObjArr[index].getCorrect_answer()){
                correctGuesses++;
            }
        }
        if(e.getSource() ==buttonD){
            if(answerDLabel.getText() == quizObjArr[index].getCorrect_answer()){
                correctGuesses++;
            }
        }

        showAnswers();

    }

    // method - correct answer

    public void showAnswers() {

        deadline.stop();

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(quizObjArr[index].getCorrect_answer() != answerALabel.getText()){
            answerALabel.setForeground(new Color(204, 4, 4));
        }else{
            answerALabel.setForeground(new Color(5, 176, 48));

        }
        if(quizObjArr[index].getCorrect_answer() != answerBLabel.getText()){
            answerBLabel.setForeground(new Color(204, 4, 4));
        }else{
            answerBLabel.setForeground(new Color(5, 176, 48));

        }
        if(quizObjArr[index].getCorrect_answer() != answerCLabel.getText()){
            answerCLabel.setForeground(new Color(204, 4, 4));
        }else{
            answerCLabel.setForeground(new Color(5, 176, 48));

        }
        if(quizObjArr[index].getCorrect_answer() != answerDLabel.getText()){
            answerDLabel.setForeground(new Color(204, 4, 4));
        }else{
            answerDLabel.setForeground(new Color(5, 176, 48));

        }

        Timer pause = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerALabel.setForeground(Color.WHITE);
                answerBLabel.setForeground(Color.WHITE);
                answerCLabel.setForeground(Color.WHITE);
                answerDLabel.setForeground(Color.WHITE);

                 seconds = 10;
                 secondsLeft.setText(String.valueOf(seconds));
                 buttonA.setEnabled(true);
                 buttonB.setEnabled(true);
                 buttonC.setEnabled(true);
                 buttonD.setEnabled(true);
                 index++;
                 nextQuestion();

            }
        });
            pause.setRepeats(false);
            pause.start();
    }



    public void results() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int) (correctGuesses / (double) totalQuestions * 100);
        textField.setText("Results!");
        textArea.setText("");
        answerALabel.setText("");
        answerBLabel.setText("");
        answerCLabel.setText("");
        answerDLabel.setText("");

        numberRight.setText(correctGuesses + " / " + totalQuestions );
        percentage.setText(result + "%");

        frame.add(percentage);
        frame.add(numberRight);

    }


}
