package com.masaibar.listviewlikelinesample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 30 ; i++){
            messages.add(new Message(i, "hoge " + i));
        }

        MessageAdapter adapter = new MessageAdapter(this, 0, messages);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    private class MessageAdapter extends ArrayAdapter<Message> {
        private LayoutInflater mInflater;

        public MessageAdapter(Context context, int resource, List<Message> messages) {
            super(context, resource, messages);
            mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView != null) {
                view = convertView;
            } else {
                view = mInflater.inflate(R.layout.list_item, parent, false);
            }

            Message message = getItem(position);

            ((TextView) view.findViewById(R.id.text_message)).setText(message.getMessage());
            ((TextView) view.findViewById(R.id.text_date)).setText(String.valueOf(message.getDate()));

            view.findViewById(R.id.frame_message).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(ListActivity.this, "item " + position + "long clicked!!!!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            return view;
        }
    }

    private class Message {
        private long date;
        private String message;

        public Message(long date, String message) {
            this.date = date;
            this.message = message;
        }

        public long getDate() {
            return date;
        }

        public String getMessage() {
            return message;
        }
    }
}
