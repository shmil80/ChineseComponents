package com.shmuel.chinesecomponents.data;

import android.content.Context;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.shmuel.chinesecomponents.beans.Hsk;
import com.shmuel.chinesecomponents.beans.Letter;


public class DataSource
{
    private final Context context;

    public DataSource(Context context)
    {
        this.context = context;
    }

    public List<Letter> getLetters()
    {
        List<Letter> result = new ArrayList<>();
        try
        {
            String jsonText = loadJSONFromAsset();
            JSONArray array = new JSONArray(jsonText);
            Letter last = null;
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject jsonLetter = array.getJSONObject(i);
                Letter letter = new Letter(
                        jsonLetter.getBoolean("component"),
                        jsonLetter.getBoolean("word"),
                        Hsk.HSK1,
                        //jsonLetter.getInt("hsk"),
                        jsonLetter.getString("id"),
                        jsonLetter.getString("chinese_letter"),
                        jsonLetter.getString("pinyin"),
                        jsonLetter.getString("meaning"),
                        getArray(jsonLetter.getJSONArray("parts")),
                        jsonLetter.getString("story_meaning"),
                        getArray(jsonLetter.getJSONArray("story_meaning_bold")),
                        jsonLetter.getString("story_pronounciation"),
                        getArray(jsonLetter.getJSONArray("story_pronounciation_bold")),
                        jsonLetter.getString("shape"),
                        jsonLetter.getString("chinese_pic"),
                        jsonLetter.getString("summary"));
                if (last != null)
                {
                    last.setNextId(letter.getId());
                    letter.setPreviousId(last.getId());
                }
                last = letter;
                result.add(letter);
            }
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    private String[] getArray(JSONArray json) throws JSONException
    {
        String[] result = new String[json.length()];
        for (int i = 0; i < json.length(); i++)
            result[i] = json.getString(i);
        return result;
    }


    @Nullable
    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    private String loadJSONFromAsset() throws IOException
    {
        InputStream inputStream = null;
        try
        {
            inputStream = context.getAssets().open("letters.json");
            byte[] buffer = new byte[inputStream.available()];
            if (inputStream.read(buffer) < 0)
                return null;
            return new String(buffer, "UTF-8");
        }
        finally
        {
            if (inputStream != null)
                inputStream.close();
        }
    }

}
