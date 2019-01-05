package bryantan.codingtricks;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bryantan.codingtricks.android.droidActivity;
import bryantan.codingtricks.ios.iosActivity;
import bryantan.codingtricks.web.WebActivity;

public class HomeFragment extends Fragment {
    CardView web;
    CardView droid;
    CardView ios;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        web = (CardView) view.findViewById(R.id.websiteId);
        droid = (CardView) view.findViewById(R.id.androidId);
        ios = (CardView) view.findViewById(R.id.iosId);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(getActivity(), WebActivity.class);
                startActivity(webIntent);
            }
        });
        droid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent droidIntent = new Intent(getActivity(), droidActivity.class);
                startActivity(droidIntent);
            }
        });
        ios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iosIntent = new Intent(getActivity(), iosActivity.class);
                startActivity(iosIntent);
            }
        });

        return view;
    }
}