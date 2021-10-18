package com.shmuel.chinesecomponents.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

/**
 * Created by shmuel on 16/01/2018.
 */

public class Letter implements Parcelable
{
    private boolean component;
    private boolean word;
    @Hsk
    private int hsk;
    private String id;
    private String chineseLetter;
    private String chinesePic;
    private String summary;
    private String pinyin;
    private String meaning;
    private String[] parts;
    private String storyMeaning;
    private String[] storyMeaningBold;
    private String storyPronunciation;
    private String[] storyPronunciationBold;
    private String shape;
    private String nextId;
    private String previousId;

    public Letter(boolean component,boolean word, @Hsk int hsk, String id, String chineseLetter,
                  String pinyin, String meaning, String[] parts, String storyMeaning,
                  String[] storyMeaningBold, String storyPronunciation,
                  String[] storyPronunciationBold, String shape, String chinesePic, String summary)
    {
        this.component = component;
        this.hsk = hsk;
        this.word=word;
        this.id = id;
        this.chineseLetter = chineseLetter;
        this.pinyin = pinyin;
        this.meaning = meaning;
        this.parts = parts;
        this.storyMeaning = storyMeaning;
        this.storyMeaningBold = storyMeaningBold;
        this.storyPronunciation = storyPronunciation;
        this.storyPronunciationBold = storyPronunciationBold;
        this.shape = shape;
        this.chinesePic = chinesePic;
        this.summary = summary;
    }

    protected Letter(Parcel in)
    {
        id = in.readString();
        chineseLetter = in.readString();
        pinyin = in.readString();
        meaning = in.readString();
        parts = in.createStringArray();
        storyMeaning = in.readString();
        storyMeaningBold = in.createStringArray();
        storyPronunciation = in.readString();
        storyPronunciationBold = in.createStringArray();
        shape = in.readString();
        //noinspection WrongConstant
        hsk = in.readInt();
        component = in.readByte() == 1;
        word = in.readByte() == 1;
        previousId = in.readString();
        nextId = in.readString();
        chinesePic = in.readString();
        summary = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(id);
        dest.writeString(chineseLetter);
        dest.writeString(pinyin);
        dest.writeString(meaning);
        dest.writeStringArray(parts);
        dest.writeString(storyMeaning);
        dest.writeStringArray(storyMeaningBold);
        dest.writeString(storyPronunciation);
        dest.writeStringArray(storyPronunciationBold);
        dest.writeString(shape);
        dest.writeInt(hsk);
        dest.writeByte((byte) (component ? 1 : 0));
        dest.writeByte((byte) (word ? 1 : 0));
        dest.writeString(previousId);
        dest.writeString(nextId);
        dest.writeString(chinesePic);
        dest.writeString(summary);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Letter> CREATOR = new Creator<Letter>()
    {
        @Override
        public Letter createFromParcel(Parcel in)
        {
            return new Letter(in);
        }

        @Override
        public Letter[] newArray(int size)
        {
            return new Letter[size];
        }
    };

    public String getChineseLetter()
    {
        return chineseLetter;
    }

    public void setChineseLetter(String chineseLetter)
    {
        this.chineseLetter = chineseLetter;
    }

    public String getPinyin()
    {
        return pinyin;
    }

    public void setPinyin(String pinyin)
    {
        this.pinyin = pinyin;
    }

    public String getMeaning()
    {
        return meaning;
    }

    public void setMeaning(String meaning)
    {
        this.meaning = meaning;
    }

    public String getStoryMeaning()
    {
        return storyMeaning;
    }

    public void setStoryMeaning(String storyMeaning)
    {
        this.storyMeaning = storyMeaning;
    }

    public String[] getStoryMeaningBold()
    {
        return storyMeaningBold;
    }

    public void setStoryMeaningBold(String[] storyMeaningBold)
    {
        this.storyMeaningBold = storyMeaningBold;
    }

    public String getStoryPronunciation()
    {
        return storyPronunciation;
    }

    public void setStoryPronunciation(String storyPronunciation)
    {
        this.storyPronunciation = storyPronunciation;
    }

    public String[] getStoryPronunciationBold()
    {
        return storyPronunciationBold;
    }

    public void setStoryPronunciationBold(String[] storyPronunciationBold)
    {
        this.storyPronunciationBold = storyPronunciationBold;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setParts(String[] parts)
    {
        this.parts = parts;
    }

    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public String[] getParts()
    {
        return parts;
    }

    public String getShape()
    {
        return shape;
    }

    public boolean isComponent()
    {
        return component;
    }

    public void setComponent(boolean component)
    {
        this.component = component;
    }

    @Hsk
    public int getHsk()
    {
        return hsk;
    }

    public void setHsk(@Hsk int hsk)
    {
        this.hsk = hsk;
    }

    public String getNextId()
    {
        return nextId;
    }

    public void setNextId(String nextId)
    {
        this.nextId = nextId;
    }

    public String getPreviousId()
    {
        return previousId;
    }

    public void setPreviousId(String previousId)
    {
        this.previousId = previousId;
    }

    public String getChinesePic()
    {
        return chinesePic;
    }

    public void setChinesePic(String chinesePic)
    {
        this.chinesePic = chinesePic;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public boolean isWord() {
        return word;
    }

    public void setWord(boolean word) {
        this.word = word;
    }
}
