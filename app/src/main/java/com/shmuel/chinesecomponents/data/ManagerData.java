package com.shmuel.chinesecomponents.data;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shmuel.chinesecomponents.beans.Letter;

/**
 * Created by shmuel on 16/01/2018.
 */

public class ManagerData
{
    private static final String KEY_LETTERS_STORED = "letters";
    private  Letter first;
    private final Map<String, Letter> letters = new HashMap<>();
    public final static ManagerData instance = new ManagerData();

    private ManagerData()
    {

    }

    public void init(Context context)
    {
        DataSource dataSource = new DataSource(context);
        init(dataSource.getLetters());
    }

    private void init(List<Letter> source)
    {
        first=source.get(0);
        for (Letter letter : source)
        {
            letters.put(letter.getId(), letter);
        }
    }

    public void saveInstanceState(Bundle outState)
    {
        if(true)
            return;
        Log.d("shmuel", "letters save");
        outState.putParcelableArrayList(KEY_LETTERS_STORED, new ArrayList<Parcelable>(letters.values()));
        letters.clear();
        Log.d("shmuel", "end save letters");
    }

    public void restoreInstanceState(Bundle savedInstanceState)
    {
        if (savedInstanceState == null||true)
            return;
        letters.clear();
        //noinspection ConstantConditions
        for (Parcelable par : savedInstanceState.getParcelableArrayList(KEY_LETTERS_STORED))
        {
            Letter letter = (Letter) par;
            letters.put(letter.getId(), letter);
        }
        Log.d("shmuel", "session restore");
    }

    public Letter get(String id)
    {
        if (id == null)
            return null;
        return letters.get(id);
    }

    public Letter next(String currentId)
    {
        Letter current = get(currentId);
        if (current == null)
            return null;
        else
            return get(current.getNextId());
    }

    public Letter previous(String currentId)
    {
        Letter current = get(currentId);
        if (current == null)
            return null;
        else
            return get(current.getPreviousId());
    }

    public Letter previousFiltered(String currentId)
    {
        Letter previous = get(currentId);
        if (previous == null)
            return null;
        do
        {
            previous = get(previous.getPreviousId());
        } while (previous != null && !FilterData.instance.keep(previous));
        return previous;
    }
    public Letter nextFiltered(String currentId)
    {
        Letter next = get(currentId);
        if (next == null)
            return null;
        do
        {
            next = get(next.getNextId());
        } while (next != null && !FilterData.instance.keep(next));
        return next;
    }

    public List<Letter> getLetters()
    {
        List<Letter> result=new ArrayList<>();
        Letter current=first;
        while (current!=null)
        {
            if (FilterData.instance.keep(current))
                result.add(current);
            current=get(current.getNextId());
        }
        return result;
    }
}
