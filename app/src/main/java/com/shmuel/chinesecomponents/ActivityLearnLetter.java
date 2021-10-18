package com.shmuel.chinesecomponents;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.shmuel.chinesecomponents.beans.Letter;
import com.shmuel.chinesecomponents.data.ManagerData;
import com.shmuel.chinesecomponents.logic.SaveLesson;
import com.shmuel.chinesecomponents.logic.TextToSpeech;

public class ActivityLearnLetter extends AppCompatActivity {

    public static final String EXTRA_ID_LETTER = "letter";
    private Letter letter;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_letter);

        ManagerData.instance.restoreInstanceState(savedInstanceState);

        ViewGroup layoutParts = (ViewGroup) findViewById(R.id.parts);
        TextView chinese_charachter = (TextView) findViewById(R.id.chinese_charachter);
        ImageView chinese_charachter_image = (ImageView) findViewById(R.id.chinese_charachter_image);
        TextView pinyin = (TextView) findViewById(R.id.pinyin);
        TextView meaning = (TextView) findViewById(R.id.meaning);
        ImageView shape = (ImageView) findViewById(R.id.shape);
        TextView storyMeaning = (TextView) findViewById(R.id.storyMeaning);
        TextView storyPronunciation = (TextView) findViewById(R.id.storyPronunciation);
        Button speak = (Button) findViewById(R.id.speak);
        Button goToNext = (Button) findViewById(R.id.goToNext);
        Button goToPrevious = (Button) findViewById(R.id.goToPrevious);

        Intent sourceIntent = getIntent();
        String id = sourceIntent.getStringExtra(EXTRA_ID_LETTER);
        letter = ManagerData.instance.get(id);

        String[] boldConstant = getResources().getStringArray((R.array.bold_canstants));
        List<Letter> parts = new ArrayList<>();
        for (String part : letter.getParts())
            parts.add(ManagerData.instance.get(part));
        AdapterPart adapter = new AdapterPart(this, parts);
        adapter.addToLayout(layoutParts);

        chinese_charachter.setText(letter.getChineseLetter());
        chinese_charachter.setTextSize(getResources().getDimension(letter.isWord() ? R.dimen.text_size_word_chinese : R.dimen.text_size_letter_chinese));
        pinyin.setText(letter.getPinyin());
        meaning.setText(letter.getMeaning());
        int idImage = getResources().getIdentifier(letter.getShape(), "drawable", getPackageName());
        if (idImage != 0)
            shape.setImageResource(idImage);
        storyMeaning.setText(makeSectionOfTextBold(letter.getStoryMeaning(), boldConstant, letter.getStoryMeaningBold()));
        storyPronunciation.setText(makeSectionOfTextBold(letter.getStoryPronunciation(), boldConstant, letter.getStoryPronunciationBold()));
        if ("".equals(letter.getPinyin()))
            speak.setVisibility(View.INVISIBLE);
        if ("".equals(letter.getChineseLetter())) {
            chinese_charachter.setVisibility(View.GONE);
            chinese_charachter_image.setImageResource(getResources().getIdentifier(letter.getChinesePic(), "drawable", getPackageName()));
        }
        if (letter.getNextId() == null)
            goToNext.setEnabled(false);
        if (letter.getPreviousId() == null)
            goToPrevious.setEnabled(false);
        textToSpeech = new TextToSpeech(this);
        new SaveLesson(this).setLetter(letter.getId());
    }

    private TextView createTextViewPart(final Letter part, boolean first) {
        TextView textView = new TextView(this);
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(getResources().getDimension(R.dimen.part_text_view_text_size));
        String text = first ? "" : "+ ";
        text += part.getChineseLetter() + " " + part.getSummary();
        textView.setText(text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(ActivityLearnLetter.this, part);
            }
        });
        return textView;
    }

    public static void navigate(Context context, Letter letter, boolean animate) {
        navigate(context, letter.getId(), animate);
    }

    public static void navigate(Context context, Letter letter) {
        navigate(context, letter.getId(), false);
    }

    public static void navigate(Context context, String id, boolean animate) {
        Intent intent = new Intent(context, ActivityLearnLetter.class);
        if (!animate)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(EXTRA_ID_LETTER, id);
        context.startActivity(intent);
    }

    private SpannableStringBuilder makeSectionOfTextBold(String text, String[] constBold, String... textsToBold) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        String[] textsToCheck = new String[constBold.length + textsToBold.length];
        System.arraycopy(constBold, 0, textsToCheck, 0, constBold.length);
        System.arraycopy(textsToBold, 0, textsToCheck, constBold.length, textsToBold.length);

        for (String textToBold : textsToCheck) {
            if (textToBold.length() > 0 && !textToBold.trim().equals("")) {
                //for counting start/end indexes
                int startingIndex = text.indexOf(textToBold);
                int endingIndex = startingIndex + textToBold.length();

                if (startingIndex >= 0 && endingIndex >= 0)
                    builder.setSpan(new StyleSpan(Typeface.BOLD), startingIndex, endingIndex, 0);
            }
        }

        return builder;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ManagerData.instance.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    public void goToPrevious(View view) {
        navigate(this, ManagerData.instance.previousFiltered(letter.getId()));
    }

    public void goToNext(View view) {
        navigate(this, ManagerData.instance.nextFiltered(letter.getId()));
    }

    public void speech(View view) {
        textToSpeech.speak(letter.getChineseLetter());
    }


}