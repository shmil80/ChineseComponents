package com.shmuel.chinesecomponents;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.shmuel.chinesecomponents.beans.Letter;

/**
 * Created by shmuel on 20/01/2018.
 */

class AdapterSpinnerLetters extends ArrayAdapter<Letter>
{
    private final LayoutInflater inflater;


    public AdapterSpinnerLetters(Activity context, List<Letter> letters)
    {
        super(context, R.layout.line_spinner, letters);
        this.inflater = context.getLayoutInflater();
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {

        final Letter part = getItem(position);
        final TextView layout;
        if (convertView != null)
            layout = (TextView) convertView;
        else
            layout = (TextView) inflater.inflate(R.layout.line_spinner, null); // null = not in use.
        layout.setText(part.getId() + " " + part.getChineseLetter() + "-" + part.getMeaning());
        return layout;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return getView(position, convertView, parent);
    }

    public int getPositionById(String id)
    {
        for (int i = 0; i < getCount(); i++)
            if (getItem(i).getId().equals(id))
                return i;
        return 0;
    }
}
