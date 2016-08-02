package test.doosik.com.fakeeventbus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import test.doosik.com.fakeeventbus.activities.FirstActivity;

public class MainActivity extends AppCompatActivity {


    public static final String LOGTAG = "FAKE_EBUS";
    public static final String EVENT_RECEIVE_METHOD_NAME = "onEventReceive";

    Button postBtn;
    Button moveToFirstActivityBtn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        final List<String> list = new ArrayList<>();
        list.add("Receive");
        list.add("fake event bus");
        postBtn = (Button)findViewById(R.id.button);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FakeEventBus.getInstance().post(new EventData(list));
            }
        });

        moveToFirstActivityBtn = (Button)findViewById(R.id.button2);
        moveToFirstActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FirstActivity.class);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                context.startActivity(intent);
            }
        });

    }
}
