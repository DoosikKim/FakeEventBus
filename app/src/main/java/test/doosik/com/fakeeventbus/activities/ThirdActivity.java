package test.doosik.com.fakeeventbus.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import test.doosik.com.fakeeventbus.EventData;
import test.doosik.com.fakeeventbus.FakeEventBus;
import test.doosik.com.fakeeventbus.MainActivity;
import test.doosik.com.fakeeventbus.R;

/**
 * Created by dskim98 on 16. 8. 2..
 */
public class ThirdActivity extends Activity {

    Context context;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        context = this;
        button = (Button)findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                context.startActivity(intent);
            }
        });

        FakeEventBus.getInstance().register(this);

    }

    public void onEventReceive(EventData eData) {
        List<String> list = eData.getList();
        Log.e(MainActivity.LOGTAG, "[ Third Activity ]");
        for(String str : list) {
            Log.e(MainActivity.LOGTAG, str);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FakeEventBus.getInstance().unregister(this);
    }
}
