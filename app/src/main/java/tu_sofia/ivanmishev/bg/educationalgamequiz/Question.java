package tu_sofia.ivanmishev.bg.educationalgamequiz;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.Switch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Admin on 5.4.2015 г..
 */
public class Question implements Serializable {
    private String question= "";
    private String correctAnswer="";
    private String wrongAnswer1="";
    private String wrongAnswer2="";
    private String wrongAnswer3="";
    private String positionA="";
    private String positionB="";
    private String positionC="";
    private String positionD="";


    private int difficulty = 1;
    private static boolean askAlienUsed= false;
    private static boolean askConsortiumUsed= false;
    private static boolean blastQuestionUsed= false;


    public Question(String question,String correctAnswer,String wrongAnswer1,String wrongAnswer2,String wrongAnswer3, int difficulty){
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.difficulty = difficulty * RandomPointGeneratorForCurrentAnswer();
        shuffleAnswer();
    }


    public int RandomPointGeneratorForCurrentAnswer(){

        int min = 2;
        int max = 7;
        Random rand = new Random();
        int randomNum;
        switch(this.getDifficulty()){
            case 1: randomNum = (rand.nextInt((max - min) + 1) + min) * bonus();
                break;

            case 2: randomNum = (rand.nextInt((max - min) + 3) + min) * bonus();
                break;

            case 3: randomNum = (rand.nextInt((max - min) + 5) + min) * bonus();
                break;

            case 4: randomNum = (rand.nextInt((max - min) + 7) + min) * bonus();
                break;

            default: randomNum = (rand.nextInt((max - min) + 1) + min) * bonus();
                break;

        }
        return randomNum;
    }

    private int bonus() {

        Random rand = new Random();
        int randInt = rand.nextInt(10);
        if (randInt <=2 ){
            return 2;
        }else{
            return 1;
        }

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return correctAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.correctAnswer = rightAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public static boolean isBlastQuestionUsed() {
        return blastQuestionUsed;
    }

    public static void setBlastQuestionUsed(boolean blastQuestionUsed) {
        Question.blastQuestionUsed = blastQuestionUsed;
    }

    public static boolean isAskConsortiumUsed() {
        return askConsortiumUsed;
    }

    public static void setAskConsortiumUsed(boolean askConsortiumUsed) {
        Question.askConsortiumUsed = askConsortiumUsed;
    }

    public static boolean isAskAlienUsed() {
        return askAlienUsed;
    }

    public static void setAskAlienUsed(boolean askAlienUsed) {
        Question.askAlienUsed = askAlienUsed;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getPositionA() {
        return positionA;
    }

    public void setPositionA(String possitionA) {
        this.positionA = possitionA;
    }

    public String getPositionB() {
        return positionB;
    }

    public void setPositionB(String possitionB) {
        this.positionB = possitionB;
    }

    public String getPositionC() {
        return positionC;
    }

    public void setPositionC(String possitionC) {
        this.positionC = possitionC;
    }

    public String getPositionD() {
        return positionD;
    }

    public void setPositionD(String possitionD) {
        this.positionD = possitionD;
    }

    public void shuffleAnswer(){

        String[] allAnswers = {getRightAnswer(), getWrongAnswer1(),getWrongAnswer2(),getWrongAnswer3()};
        ArrayList<String> allAnswersList = new ArrayList<String>(Arrays.asList(allAnswers));
        Collections.shuffle(allAnswersList);
        Iterator iterator = allAnswersList.iterator();

        while(iterator.hasNext()){
            if(getPositionA().equals("")){
                setPositionA(iterator.next().toString());
            }else if(getPositionB().equals("")){
                setPositionB(iterator.next().toString());
            }else if(getPositionC().equals("")){
                setPositionC(iterator.next().toString());
            }else{
                setPositionD(iterator.next().toString());
            }
        }

    }
//    //check for one wrong answer that will stay in object field
//    public void useFiftyFifty(){
//        if (isFiftyFiftyIsUsed() == false){
//            Random rand = new Random();
//            int wAnswer1 = rand.nextInt(100);
//            int wAnswer2 = rand.nextInt(100);
//            int wAnswer3 = rand.nextInt(100);
//            if((wAnswer1 <= wAnswer2) && (wAnswer1 <= wAnswer3)){
//                setWrongAnswer2("");
//                setWrongAnswer3("");
//            }else if((wAnswer2 <= wAnswer1) && (wAnswer2 <= wAnswer3)){
//                setWrongAnswer3("");
//                setWrongAnswer1("");
//            }else{
//                setWrongAnswer1("");
//                setWrongAnswer2("");
//            }
//            setFiftyFiftyIsUsed(true);
//        }
//   }

    public String blastQuestion(){
        setBlastQuestionUsed(true);
        return "Военоначалникът на извънземната раса Грох'ул използва мощен бластер върху въпросът и облъчи с радианция верният отговор!";
    }


    public String askAlien(){
        setAskAlienUsed(true);
        String cAnswer = getRightAnswer();
        return "Мъдрецът на извънземните мисли, че верният отговор е: " + cAnswer;
    }

    public static void setHelpers(){
        setAskAlienUsed(false);
        setAskConsortiumUsed(false);
        setBlastQuestionUsed(false);
    }

    public String askConsortium(){
        setAskConsortiumUsed(true);
        int cAnswer = 40;
        int wAnswer1 = 0;
        int wAnswer2 = 0;
        int wAnswer3 = 0;
        Random rand = new Random();
        cAnswer = cAnswer + rand.nextInt(30)+1;
        wAnswer1 = rand.nextInt(100-cAnswer)+1;
        wAnswer2 = rand.nextInt(100-(wAnswer1+cAnswer))+1;
        wAnswer3 = (100-(wAnswer2+wAnswer1+cAnswer));

        return "Консорциумът на извънземните отсъди:  \n" +
                getRightAnswer() + " " + cAnswer + "%" +"\n" +
                getWrongAnswer1() + " " + wAnswer1 + "%" +"\n" +
                getWrongAnswer2() + " " + wAnswer2 + "%" +"\n" +
                getWrongAnswer3() + " " + wAnswer3 + "%";

    }
/*    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(correctAnswer);
        dest.writeString(wrongAnswer1);
        dest.writeString(wrongAnswer2);
        dest.writeString(wrongAnswer3);
        dest.writeString(positionA);
        dest.writeString(positionB);
        dest.writeString(positionC);
        dest.writeString(positionD);
        dest.writeInt(difficulty);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {

            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };*/
}
