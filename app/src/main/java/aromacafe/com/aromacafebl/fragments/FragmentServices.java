package aromacafe.com.aromacafebl.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import aromacafe.com.aromacafebl.MainActivity;
import aromacafe.com.aromacafebl.R;
import aromacafe.com.aromacafebl.extras.StripHtml;
import aromacafe.com.aromacafebl.interfaces.Constants;
import aromacafe.com.aromacafebl.interfaces.Keys;
import aromacafe.com.aromacafebl.list.ListItems;
import aromacafe.com.aromacafebl.list.RecyclerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentServices extends Fragment {

    private List<ListItems> list = new ArrayList<ListItems>();
    private RecyclerView mRecyclerView;
    private RecyclerAdapter adapter;
    private RequestQueue queue;
    private Context context;

    private static final String TAG= "MyRecyclerList";
    private static final String STATE_POSTS = "state_posts";
    private static final String url = "http://aromacafe.ba/api/get_posts/?post_type=services";

    private String urlString;
    private String actionBarTitle;

    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeContainer;
    private StripHtml stripHtml = new StripHtml();


    public FragmentServices() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBarTitle = getArguments().getString(Keys.KEY_ACTION_BAR_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.fragment_services, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //urlString = getArguments().getString(Keys.KEY_URL);

        //rotate screen saved content
        if (savedInstanceState !=null){
            list = savedInstanceState.getParcelableArrayList(STATE_POSTS);
            adapter = new RecyclerAdapter(context, list); //context a trebalo bi getActivity
            mRecyclerView.setAdapter(adapter);
        }else{
            updateList(MainActivity.fragSubreddit);
        }

        return rootView;
    }


    public void updateList(String stringURL) {

        adapter = new RecyclerAdapter(getActivity(), list);
        mRecyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        adapter.clearAdapter();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,(String)null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                if(response !=null || response.length() > 0){
                    try {
                        JSONArray arrayPosts = response.getJSONArray(Keys.KEY_POSTS);
                        for(int i =0; i <arrayPosts.length(); i++){
                            JSONObject currentPost = arrayPosts.getJSONObject(i);

                            //Default values
                            long id = -1;
                            int j = 0;
                            String title = Constants.NA;
                            String thumbnail = Constants.NA;

                            ListItems items = new ListItems();

                            if (currentPost.has(Keys.KEY_ID) && !currentPost.isNull(Keys.KEY_ID)) {
                                id = currentPost.getLong(Keys.KEY_ID);
                            }
                            if (currentPost.has(Keys.KEY_TITLE) && !currentPost.isNull(Keys.KEY_TITLE)){
                                title = currentPost.getString(Keys.KEY_TITLE);
                            }
                            //thumbnail object
                            JSONObject objectThumb = currentPost.getJSONObject(Keys.KEY_THUMBNAIL);
                            JSONObject objectFullThumbSize = objectThumb.getJSONObject(Keys.KEY_IMAGE_FULL);
                            if (objectFullThumbSize.has(Keys.KEY_IMAGE_URL) && !objectFullThumbSize.isNull(Keys.KEY_IMAGE_URL)){
                                thumbnail = objectFullThumbSize.getString(Keys.KEY_IMAGE_URL);
                            }

                            items.setId(id);
                            items.setTitle(title);
                            items.setThumbnail(thumbnail);
                            if (id != -1 && !title.equals(Constants.NA)){
                                list.add(items);
                            }
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error" + error.getMessage());

            }
        });

        queue.add(jsonObjectRequest);
    }

}
