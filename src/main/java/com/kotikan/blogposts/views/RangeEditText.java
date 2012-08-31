package com.kotikan.blogposts.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;
import com.kotikan.blogposts.R;

/**
 * Applies a limit (min / max) to the input allowed
 * This will not enforce the limit (ie by blocking more characters from being added)
 * but it will return false to the 'isValid' check
 * <p/>
 * Created: 31/08/2012
 *
 * @author roberthewitt
 * @since 1.0.0
 */
public class RangeEditText extends EditText {

    private static final int DEFAULT_MINIMUM = 4;
    private static final int DEFAULT_MAXIMUM = 10;

    private int charsAllowedMinimum;
    private int charsAllowedMaximum;

    public void setCharsAllowedMaximum(int charsAllowedMaximum) {
        this.charsAllowedMaximum = charsAllowedMaximum;
    }

    public void setCharsAllowedMinimum(int charsAllowedMinimum) {
        this.charsAllowedMinimum = charsAllowedMinimum;
    }

    public int getCharsAllowedMaximum() {
        return charsAllowedMaximum;
    }

    public int getCharsAllowedMinimum() {
        return charsAllowedMinimum;
    }

    public RangeEditText(Context context) {
        super(context);
    }

    public RangeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        loadStateFromAttrs(attrs);
    }

    private void loadStateFromAttrs(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return; // quick exit
        }

        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(attributeSet, R.styleable.RangeEditText);
            charsAllowedMinimum = a.getInt(R.styleable.RangeEditText_charInputMinimum, DEFAULT_MINIMUM);
            charsAllowedMaximum = a.getInt(R.styleable.RangeEditText_charInputMaximum, DEFAULT_MAXIMUM);
        } finally {
            if (a != null) {
                a.recycle(); // ensure this is always called
            }
        }
    }

    /**
     * checks if the current text contained within this view is within the range specified
     *
     * @return
     */
    public boolean isValid() {
        final Editable text = getText();
        if (text == null) {
            return false;
        }

        // todo - allow lower or upper bounds to not be explicitly set.

        final int length = text.length();
        return length >= charsAllowedMinimum && length <= charsAllowedMaximum;
    }
}
