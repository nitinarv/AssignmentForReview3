package session.hackevent.mygola.com.mygola.view.mainscreen;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nitinraj.arvind on 7/7/2015.
 */
public class ExampleAdapter extends CursorAdapter {

    private List<String> items;

    private TextView text;

    public ExampleAdapter(Context context, Cursor cursor, List<String> items) {

        super(context, cursor, false);

        this.items = items;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if(text!=null && items!=null
                )
            text.setText(items.get(cursor.getPosition()));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        view.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        text = (TextView) view.findViewById(android.R.id.text1);

        return view;

    }

}