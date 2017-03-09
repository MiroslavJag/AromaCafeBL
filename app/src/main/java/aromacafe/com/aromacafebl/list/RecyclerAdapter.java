package aromacafe.com.aromacafebl.list;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import aromacafe.com.aromacafebl.R;
import aromacafe.com.aromacafebl.extras.MySingleton;

/**
 * Created by m.jagodic on 9.3.2017..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListItems> list;
    private Context context;
    private ImageLoader imageLoader;
    private LayoutInflater layoutInflater;

    public RecyclerAdapter(List<ListItems> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.row_list_item, parent, false);
        ListRowViewHolder viewHolder = new ListRowViewHolder(view, context, list);
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ListRowViewHolder bindHolder = (ListRowViewHolder) holder;
        itemOnBindViewHolder(bindHolder, position);
    }

    private void itemOnBindViewHolder(ListRowViewHolder listRowViewHolder, int position) {
        ListItems listItems = list.get(position);
        listRowViewHolder.getLayoutPosition();

        imageLoader = MySingleton.getInstance(context).getImageLoader();
        listRowViewHolder.thumbnail.setImageUrl(listItems.getThumbnail(), imageLoader);
        listRowViewHolder.thumbnail.setDefaultImageResId(R.drawable.defaultimg_placeholder);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            listRowViewHolder.title.setText(Html.fromHtml(listItems.getTitle(), Html.FROM_HTML_MODE_LEGACY));
        }else{
            listRowViewHolder.title.setText(Html.fromHtml(listItems.getTitle()));
        }

    }

    public void clearAdapter(){
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return (null != list ? list.size() : 0);
    }
}
