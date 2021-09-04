package com.kmmi.kmmiquiz.ui.quiz;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kmmi.kmmiquiz.R;
import com.kmmi.kmmiquiz.model.QuestionQuiz;
import com.kmmi.kmmiquiz.model.Score;
import com.kmmi.kmmiquiz.object.Constant;
import com.kmmi.kmmiquiz.preferences.UserPreferences;

import java.util.ArrayList;

public class QuizFragment extends Fragment implements View.OnClickListener {
    private ProgressBar progressBar;
    private Button btnOptionOne, btnOptionTwo, btnOptionThree, btnOptionFour, btnGo;
    private TextView tvQuestion;

    private int mQuestionPosition = 1;
    private ArrayList<QuestionQuiz> mQuizList = null;
    private int mSelectOptionPosition = 0;
    private int mCorrectAnswer = 0;
    private int mWrongAnswer = 0;
    private int mScore = 0;

    private UserPreferences preferences;
    private Score score = null;

    MediaPlayer mediaPlayer;

    private final static String TAG = QuizFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar);
        btnOptionOne = view.findViewById(R.id.option_1);
        btnOptionTwo = view.findViewById(R.id.option_2);
        btnOptionThree = view.findViewById(R.id.option_3);
        btnOptionFour = view.findViewById(R.id.option_4);
        btnGo = view.findViewById(R.id.btn_go);
        tvQuestion = view.findViewById(R.id.tv_question);

        mQuizList = new Constant().getQustion();
        preferences = new UserPreferences(requireActivity());
        score = new Score();

        btnOptionOne.setOnClickListener(this);
        btnOptionTwo.setOnClickListener(this);
        btnOptionThree.setOnClickListener(this);
        btnOptionFour.setOnClickListener(this);
        btnGo.setOnClickListener(this);

        setQuestion();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.option_1) {
            selectedViewOptions(btnOptionOne,1);
        }else if (view.getId() == R.id.option_2) {
            selectedViewOptions(btnOptionTwo,2);
        }else if (view.getId() == R.id.option_3) {
            selectedViewOptions(btnOptionThree,3);
        }else if (view.getId() == R.id.option_4) {
            selectedViewOptions(btnOptionFour,4);
        }else if (view.getId() == R.id.btn_go) {
            if (mSelectOptionPosition == 0) {
                mQuestionPosition++;
                if (mQuestionPosition <= mQuizList.size()) {
                    setQuestion();
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_scoreFragment);
                    int scorer = mScore * 2;
                    score.setIsScore(scorer);
                    score.setCorrect(mCorrectAnswer);
                    score.setWrong(mWrongAnswer);
                    preferences.setScore(score);

                    mediaPlayer = MediaPlayer.create(requireActivity(),R.raw.notif_finish);
                    mediaPlayer.start();
                }
            }else {
                QuestionQuiz question = mQuizList.get(mQuestionPosition - 1);
                Log.d(TAG, "onClick: " + mQuestionPosition);
                if (question.getQuestionAnswer() !=  mSelectOptionPosition) {
                    answerView(mSelectOptionPosition,R.drawable.bg_btn_wrong);
                    mediaPlayer = MediaPlayer.create(requireActivity(),R.raw.notif_wrong);
                    mWrongAnswer++;
                }else {
                    mediaPlayer = MediaPlayer.create(requireActivity(),R.raw.notif_correct );
                    mCorrectAnswer++;
                    mScore += 10;
                }
                mediaPlayer.start();
                answerView(question.getQuestionAnswer(),R.drawable.bg_btn_correct);
                if (mQuestionPosition == mQuizList.size()) {
                    btnGo.setText(getResources().getString(R.string.finish));
                } else {
                    btnGo.setText(getResources().getString(R.string.next));
                }
                mSelectOptionPosition = 0;
            }

        }
    }

    private void setQuestion() {
        QuestionQuiz questionQuiz = mQuizList.get(mQuestionPosition - 1);
        defaultOptionView();
        btnGo.setText(getResources().getString(R.string.go));
        progressBar.setProgress(mQuestionPosition);
        tvQuestion.setText(questionQuiz.getQuestion());
        btnOptionOne.setText(questionQuiz.getOptionOne());
        btnOptionTwo.setText(questionQuiz.getOptionTwo());
        btnOptionThree.setText(questionQuiz.getOptionThree());
        btnOptionFour.setText(questionQuiz.getOptionOFour());
    }

    private void selectedViewOptions(Button btnSelect, int selectedOption) {
        defaultOptionView();
        mSelectOptionPosition = selectedOption;
        btnSelect.setBackground(ContextCompat.getDrawable(requireActivity(), R.drawable.bg_login));
        btnSelect.setTextColor(requireActivity().getResources().getColor(R.color.white));
    }

    private void answerView(int answer, int drawableView) {
        switch (answer) {
            case 1: {
                btnOptionOne.setBackground(ContextCompat.getDrawable(requireActivity(), drawableView));
                btnOptionOne.setTextColor(requireActivity().getResources().getColor(R.color.white));
                break;
            }
            case 2: {
                btnOptionTwo.setBackground(ContextCompat.getDrawable(requireActivity(), drawableView));
                btnOptionTwo.setTextColor(requireActivity().getResources().getColor(R.color.white));
                break;
            }
            case 3: {
                btnOptionThree.setBackground(ContextCompat.getDrawable(requireActivity(), drawableView));
                btnOptionThree.setTextColor(requireActivity().getResources().getColor(R.color.white));
                break;
            }
            case 4: {
                btnOptionFour.setBackground(ContextCompat.getDrawable(requireActivity(), drawableView));
                btnOptionFour.setTextColor(requireActivity().getResources().getColor(R.color.white));
                break;
            }
        }
    }

    private void defaultOptionView() {
        ArrayList<Button> options = new ArrayList<>();
        options.add(0,btnOptionOne);
        options.add(1,btnOptionTwo);
        options.add(2,btnOptionThree);
        options.add(3,btnOptionFour);
        for (int i = 0; i < options.size(); i++) {
            options.get(i).setBackground(ContextCompat.getDrawable(requireActivity(),R.drawable.bg_cancel));
            options.get(i).setTextColor(textPrimaryColor());
        }
    }

    private int textPrimaryColor() {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getActivity().getTheme();
        theme.resolveAttribute(android.R.attr.textColorPrimary, typedValue, true);
        TypedArray arr =
                getActivity().obtainStyledAttributes(typedValue.data, new int[]{
                        android.R.attr.textColorPrimary});
        return arr.getColor(0, -1);
    }
}