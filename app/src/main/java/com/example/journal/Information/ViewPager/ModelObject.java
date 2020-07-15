package com.example.journal.Information.ViewPager;

import com.example.journal.R;

public enum ModelObject {
    RED(1, R.layout.infolayout1),
    BLUE(2, R.layout.infolayout2),
    GREEN(3,R.layout.infolayout3);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
