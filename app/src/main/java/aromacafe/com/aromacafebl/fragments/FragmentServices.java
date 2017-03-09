package aromacafe.com.aromacafebl.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import aromacafe.com.aromacafebl.R;
import aromacafe.com.aromacafebl.extras.StripHtml;
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




        return rootView;
    }

}
