package com.shmuel.chinesecomponents.logic;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by shmuel on 21/01/2018.
 */

public class SaveLesson
{
    private static final String CURRENT_LETTER = "letter";
    private static final String CATEGORY_STORAGE = "userDetails";
    private final SharedPreferences sharedPreferences;

    public SaveLesson(Context context)
    {
        sharedPreferences = context.getSharedPreferences(CATEGORY_STORAGE, MODE_PRIVATE);

    }

    public void setLetter(String id)
    {

        // "UserDetails" = Category Name inside the XML file.
        // MODE_PRIVATE = Only our app can read those values. This is the only mode supported.

        // With the editor we are going to write data:
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CURRENT_LETTER, id);
        // Save:
        editor.apply();
    }

    public String getLetter()
    {
        return sharedPreferences.getString(CURRENT_LETTER, "1");
    }

}
