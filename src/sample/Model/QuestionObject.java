package sample.Model;

public class QuestionObject {

    public enum OPTION {A, B, C, D, NOT_FILLED}

    ;

    private OPTION selectedOption;
    private OPTION correctOption;

    public QuestionObject(OPTION correctAns) {
        this.correctOption = correctAns;
    }

    public void setSelectedOption(OPTION selectedOption) {
        this.selectedOption = selectedOption;
    }

    public boolean isCorrect() {
        return (selectedOption == correctOption);
    }
}
