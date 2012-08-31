package com.kotikan.blogposts.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.kotikan.blogposts.R;
import com.kotikan.blogposts.views.RangeEditText;

public class MyActivity extends SherlockActivity {

    private RangeEditText viewWithAttributes;
    private RangeEditText viewWithoutAttributes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewWithAttributes = (RangeEditText) findViewById(R.id.range_edit_with_attributes_set);
        viewWithoutAttributes = (RangeEditText) findViewById(R.id.range_edit_without_attributes_set);
        setTitle("Test");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        createMenuItemForView(menu, viewWithoutAttributes, "withoutXml", "toast view without xml attributes");
        createMenuItemForView(menu, viewWithAttributes, "withXml", "toast view with xml attributes");

        return true;
    }

    private void createMenuItemForView(Menu menu, final RangeEditText rangeEditText, String buttonTitle, String longPressHelpText) {
        final MenuItem menuItem = menu.add(longPressHelpText);
        final Button button = new Button(this);
        button.setText(buttonTitle);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toastReport(rangeEditText.getCharsAllowedMinimum(), rangeEditText.getCharsAllowedMaximum(), rangeEditText.isValid());
            }
        });
        menuItem.setActionView(button);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    private void toastReport(int min, int max, boolean isValid) {
        Toast.makeText(this, String.format("minimum: %d & maximum: %d isValid: %s", min, max, isValid), Toast.LENGTH_SHORT).show();
    }
}
