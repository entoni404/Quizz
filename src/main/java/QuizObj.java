public class QuizObj {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;


    public QuizObj() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    public String[] allQuestions(){

        String[] allQuestions = new String[incorrect_answers.length + 1];

        for (int i = 0; i < incorrect_answers.length + 1 ; i++) {
            if(i < incorrect_answers.length){
            allQuestions[i] = incorrect_answers[i];
            }else{
                allQuestions[i] = correct_answer;
            }
        }


        return allQuestions;

    }

    @Override
    public String toString(){
    // type difficulty question correct_answer incorrect_answers
        return getClass().getSimpleName() + "[Category: "+ category + ", Type: " + type + ", Difficulty: "+ difficulty +
                ", Question: " + question + ", Correct_Answer: " + correct_answer + ", Incorret_Answers: " + incorrect_answers;
    }
}
