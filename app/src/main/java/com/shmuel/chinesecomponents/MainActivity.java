package com.shmuel.chinesecomponents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.shmuel.chinesecomponents.data.ManagerData;
import com.shmuel.chinesecomponents.logic.SaveLesson;

public class MainActivity extends AppCompatActivity
{


    private AdapterSpinnerLetters adapterSpinnerLetters;
    private Spinner spinner_letters;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
        spinner_letters = (Spinner) findViewById(R.id.spinner_letters);
        adapterSpinnerLetters= new AdapterSpinnerLetters(this,ManagerData.instance.getLetters());
        spinner_letters.setAdapter(adapterSpinnerLetters);
        SaveLesson saveLesson=new SaveLesson(this);
        spinner_letters.setSelection(adapterSpinnerLetters.getPositionById(saveLesson.getLetter()));

    }

    private void initApp()
    {
        ManagerData.instance.init(this);
    }

    public void submit(View view)
    {
        ActivityLearnLetter.navigate(this,adapterSpinnerLetters.getItem(spinner_letters.getSelectedItemPosition()),true);
    }
}
