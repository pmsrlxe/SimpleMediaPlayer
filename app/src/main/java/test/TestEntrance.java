package test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import test.seek.TestSeekTo;

/**
 * Test入口
 */
public class TestEntrance extends Activity {
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(ll);
        setContentView(scrollView);


        //add test here
        add(TestSeekTo.class);
    }

    private void add(final Class<TestSeekTo> clazz) {
        Button button = new Button(this);
        button.setText(clazz.getSimpleName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestEntrance.this, clazz));
            }
        });
        ll.addView(button);
    }

}
