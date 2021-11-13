package com.hgrk.moviedbuts.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.hgrk.moviedbuts.R;

import com.hgrk.moviedbuts.adapter.UpComingAdapter;

import com.hgrk.moviedbuts.model.Upcoming;
import com.hgrk.moviedbuts.viewmodel.MovieViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link up_coming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class up_coming extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LinearLayoutManager lm;
    boolean scroll = false;
    ProgressBar LoadingIcon;
    UpComingAdapter adapter;
    int page=1;
    int current_items, itemcount, scrolleditem,first_item;
    public up_coming() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment up_coming.
     */
    // TODO: Rename and change types and number of parameters
    public static up_coming newInstance(String param1, String param2) {
        up_coming fragment = new up_coming();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private RecyclerView rv_up_coming;
    private MovieViewModel view_model;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        rv_up_coming=view.findViewById(R.id.rv_up_coming);
        view_model=new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getUpComing(page);
        lm = new LinearLayoutManager(getActivity());
        LoadingIcon = view.findViewById(R.id.loadingicon);
        adapter =new UpComingAdapter(getActivity());
        view_model.getResultUpComing().observe(getActivity(), showUpComing);
        rv_up_coming.setLayoutManager(lm);
        rv_up_coming.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    scroll = true;
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                current_items = lm.getChildCount();
                itemcount = lm.getItemCount();
                scrolleditem = lm.findFirstVisibleItemPosition();
                first_item = lm.findFirstCompletelyVisibleItemPosition();
                if ((current_items + scrolleditem >= itemcount) && scroll) {
                    scroll = false;
                    Log.v("", "Terakir");
                    page = page + 1;
                    LoadingIcon.setVisibility(View.VISIBLE);
                    view_model.getUpComing(page);
                    view_model.getResultUpComing().observe(getActivity(), new Observer<List<Upcoming.Results>>() {
                        @Override
                        public void onChanged(List<Upcoming.Results> list) {
                            LoadingIcon.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();
                        }

                    });
                }
            }

    });


        return view;
    }

    private Observer<List<Upcoming.Results>> showUpComing=new Observer<List<Upcoming.Results>>(){

        @Override
        public void onChanged(List<Upcoming.Results> upcoming) {
            LoadingIcon.setVisibility(View.INVISIBLE);
            adapter.setListUpcoming(upcoming);
            rv_up_coming.setAdapter(adapter);
        }
    };
}