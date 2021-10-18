package com.shmuel.chinesecomponents.data;

import com.shmuel.chinesecomponents.beans.Hsk;
import com.shmuel.chinesecomponents.beans.Letter;

/**
 * Created by shmuel on 19/01/2018.
 */

public class FilterData
{
    private FilterData()
    {
    }

    public final static FilterData instance = new FilterData();
    @Hsk
    private int hskLevel = Hsk.NO_HSK;
    private boolean onlyComponent;
    private boolean onlyWord;

    public boolean keep(Letter letter)
    {
        if (letter.getHsk() > hskLevel)
            return false;
        if (onlyComponent && letter.isComponent())
            return false;
        if (onlyWord && "".equals(letter.getMeaning()))
            return false;
        return true;
    }

    public int getHskLevel()
    {
        return hskLevel;
    }

    public void setHskLevel(int hskLevel)
    {
        this.hskLevel = hskLevel;
    }

    public boolean isOnlyComponent()
    {
        return onlyComponent;
    }

    public void setOnlyComponent(boolean onlyComponent)
    {
        this.onlyComponent = onlyComponent;
    }

    public boolean isOnlyWord()
    {
        return onlyWord;
    }

    public void setOnlyWord(boolean onlyWord)
    {
        this.onlyWord = onlyWord;
    }
}
