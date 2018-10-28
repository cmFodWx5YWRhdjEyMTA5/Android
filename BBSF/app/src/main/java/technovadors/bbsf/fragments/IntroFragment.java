package technovadors.bbsf.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import technovadors.bbsf.R;

public class IntroFragment extends Fragment {


    ArrayList<Integer> images=new ArrayList();
    String[] titles,descriptions;

    TextView header,description;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        header=view.findViewById(R.id.header);
        image=view.findViewById(R.id.image);
        description=view.findViewById(R.id.description);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        images.add(R.drawable.s1);
        images.add(R.drawable.s2);
        images.add(R.drawable.s3);
        titles= getResources().getStringArray(R.array.intro_titles);
        descriptions= getResources().getStringArray(R.array.intro_description);

        int position=getArguments().getInt("position");
        header.setText(titles[position]);
        description.setText(descriptions[position]);
        Picasso.get().load(images.get(position)).resize(300,300).centerInside().into(image);

    }
}
