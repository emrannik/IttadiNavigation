package com.androidnik.itaydi_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnik.itaydi_nav.adapter.YoutubeVideoAdapter;
import java.util.ArrayList;
import java.util.Collections;

public class ThirdFragment extends Fragment {

    private View view;
    private ArrayList<String> youtubeVideoArrayList;
    private ArrayList<String> youtubeVideoTitleArrayList;

    private FragmentActivity myContext;
    private static final String TAG = ThirdFragment.class.getSimpleName();

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_first, container, false);

        getActivity().setTitle(R.string.nav_3);

        generateDummyVideoList();

        //
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 3. create an adapter

        //get the video id array from strings.xml
        final YoutubeVideoAdapter mAdapter = new YoutubeVideoAdapter(myContext,youtubeVideoArrayList,youtubeVideoTitleArrayList);

        // display GlidLayout with 3 column//
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),3));
        //end
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        //set click event
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }



    public void generateDummyVideoList() {
        youtubeVideoArrayList = new ArrayList<>();
        youtubeVideoTitleArrayList = new ArrayList<>();

        //get the video id array from strings.xml
        String[] videoIDArray = getResources().getStringArray(R.array.inspire);
        // String[] videoIDArray1 = getResources().getStringArray(R.array.video_title_array);

        //add all videos to array list
        Collections.addAll(youtubeVideoArrayList, videoIDArray);
        //  Collections.addAll(youtubeVideoTitleArrayList, videoIDArray1);
    }

}
