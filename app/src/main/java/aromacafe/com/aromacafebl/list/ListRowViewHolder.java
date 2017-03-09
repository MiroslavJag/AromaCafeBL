package aromacafe.com.aromacafebl.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import aromacafe.com.aromacafebl.R;

/**
 * Created by m.jagodic on 9.3.2017..
 */

public class ListRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public NetworkImageView thumbnail;
    public TextView title;
    public String url;
    public LinearLayout linearLayout;

    Context context;
    List<ListItems> list = new ArrayList<ListItems>();

    public ListRowViewHolder(View view, Context context, List<ListItems> list) {
        super(view);
        this.context = context;
        this.list = list;

        this.thumbnail = (NetworkImageView) view.findViewById(R.id.imageView);
        this.title = (TextView) view.findViewById(R.id.titleItem);
        this.linearLayout = (LinearLayout) view.findViewById(R.id.linLayoutFirstItem);

        view.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int position = getAdapterPosition();
        ListItems listPosition = list.get(position);
        int listSize = list.size();
        Toast.makeText(context, "Item clicked at id: " + listPosition, Toast.LENGTH_SHORT).show();
    }
}
