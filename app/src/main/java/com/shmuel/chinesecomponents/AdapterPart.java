package com.shmuel.chinesecomponents;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.shmuel.chinesecomponents.beans.Letter;

/**
 * Created by shmuel on 20/01/2018.
 */
class AdapterPart extends ArrayAdapter<Letter>
{
    private final LayoutInflater inflater;

    public AdapterPart(Activity context, List<Letter> objects)
    {
        super(context, 0, objects);
        this.inflater = context.getLayoutInflater();
    }


    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {

        final Letter part = getItem(position);
        final ViewGroup layout;
        if (convertView != null)
            layout = (ViewGroup) convertView;
        else
        {
            layout = (ViewGroup) inflater.inflate(R.layout.line_part, null); // null = not in use.
            layout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ActivityLearnLetter.navigate(getContext(), part);
                }
            });

        }
        TextView letter_part = (TextView) layout.findViewById(R.id.letter_part);
        TextView plus_part = (TextView) layout.findViewById(R.id.plus_part);
        TextView summary_part = (TextView) layout.findViewById(R.id.summary_part);
        ImageView image_part = (ImageView) layout.findViewById(R.id.image_part);
        letter_part.setText(part.getChineseLetter());
        plus_part.setVisibility(position == 0 ? View.INVISIBLE : View.VISIBLE);
        summary_part.setText(part.getSummary());
        if ("".equals(part.getChineseLetter()))
            image_part.setImageResource(getContext().getResources().getIdentifier(part.getChinesePic(), "drawable", getContext().getPackageName()));
        return layout;
    }

    public void addToLayout(ViewGroup layoutParts)
    {
        for (int i = 0; i < getCount(); i++)
            layoutParts.addView(getView(i,null,layoutParts));
    }
}
