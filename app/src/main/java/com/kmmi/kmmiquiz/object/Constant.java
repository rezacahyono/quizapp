package com.kmmi.kmmiquiz.object;

import com.kmmi.kmmiquiz.model.QuestionQuiz;
import java.util.ArrayList;

public class Constant {
    public Constant() {
    }

    public ArrayList<QuestionQuiz> getQustion() {
        ArrayList<QuestionQuiz> questionList = new ArrayList<>();
        QuestionQuiz questionA = new QuestionQuiz(
                1,
                "Nama ibu kota indonesia adalah...",
                "Jakarta",
                "Bandung",
                "Banten",
                "Surabaya",
                1
        );
        questionList.add(questionA);
        QuestionQuiz questionB = new QuestionQuiz(
                2,
                "Berapa banyak provinsi di negara indonesia...",
                "34",
                "33",
                "32",
                "31",
                1
        );
        questionList.add(questionB);
        QuestionQuiz questionC = new QuestionQuiz(
                2,
                "35 + 15",
                "50",
                "40",
                "45",
                "55",
                1
        );
        questionList.add(questionC);
        QuestionQuiz questionD = new QuestionQuiz(
                2,
                "Sipakah Presiden indonesi pertama",
                "Ir. Joko widodo",
                "Ir. Soekarno",
                "Moh. yamin",
                "Ismail Marzuki",
                2
        );
        questionList.add(questionD);
        QuestionQuiz questionE = new QuestionQuiz(
                2,
                "Pada tanggal berapakah indonesia merdeka?",
                "14 Agustus 1945",
                "17 Agustus 1955",
                "18 september 1945",
                "17 Agustus 1945",
                4
        );
        questionList.add(questionE);

        return questionList;
    }
}
