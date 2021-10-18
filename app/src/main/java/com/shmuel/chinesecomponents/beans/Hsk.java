package com.shmuel.chinesecomponents.beans;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.shmuel.chinesecomponents.beans.Hsk.*;

/**
 * Created by shmuel on 19/01/2018.
 */
@IntDef({HSK1, HSK2, HSK3, HSK4, HSK5, HSK6, NO_HSK})
@Retention(RetentionPolicy.SOURCE)
public @interface Hsk
{
    static final int HSK1=1,HSK2=2,HSK3=3,HSK4=4,HSK5=5,HSK6=6,NO_HSK=100;
}
