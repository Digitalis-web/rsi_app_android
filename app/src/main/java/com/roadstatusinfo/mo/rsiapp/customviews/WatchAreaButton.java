package com.roadstatusinfo.mo.rsiapp.customviews;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import com.roadstatusinfo.mo.rsiapp.R;
import com.roadstatusinfo.mo.rsiapp.datamanaging.StorageManager;

/**
 * Created by mo on 08/11/17.
 */

public class WatchAreaButton extends android.support.v7.widget.AppCompatButton implements AppCompatButton.OnClickListener {
    public final String TAG = "WatchAreaButton";

    private Context context;
    private String areaID = "";
    private boolean isAddButton = true; // if the button should add or remove the area from watched areas

    public WatchAreaButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOnClickListener(this);
    }

    public void init(String areaID){
        this.areaID = areaID;

        isAddButton = !StorageManager.areaIsWatched(areaID);

        // If the area is watched, the color of the button will be changed
        int bgColor;
        String text = "";
        if(!isAddButton){
            bgColor = ResourcesCompat.getColor(context.getResources(), R.color.colorYellow, null);
            text = "Sluta bevaka område";
        }
        else {
            bgColor = ResourcesCompat.getColor(context.getResources(), R.color.colorBlue, null);
            text = "Bevaka område";

        }
        setBackgroundColor(bgColor);
        setText(text);

    }

    @Override
    public void onClick(View view) {
        if(isAddButton) {
            StorageManager.addWatchedArea(areaID);
        }
        else {
            StorageManager.removeWatchedArea(areaID);
        }

        init(areaID);
    }
}
