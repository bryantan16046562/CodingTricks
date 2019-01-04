package bryantan.codingtricks.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bryantan.codingtricks.R;
import bryantan.codingtricks.web.bootstrap.bootstrapActivity;
import bryantan.codingtricks.web.css.cssActivity;
import bryantan.codingtricks.web.javascript.javascriptActivity;
import bryantan.codingtricks.web.jquery.jqueryActivity;

public class WebFragment extends Fragment {
    CardView css;
    CardView jqueryy;
    CardView js;
    CardView bootstrapp;
    public WebFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        css = (CardView) view.findViewById(R.id.HTMLCSSId);
        jqueryy = (CardView) view.findViewById(R.id.jqueryId);
        js = (CardView) view.findViewById(R.id.javascriptId);
        bootstrapp = (CardView) view.findViewById(R.id.bootstrapId);

        css.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cssIntent = new Intent(getActivity(), cssActivity.class);
                startActivity(cssIntent);
            }
        });
        jqueryy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jqueryIntent = new Intent(getActivity(), jqueryActivity.class);
                startActivity(jqueryIntent);
            }
        });
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jsIntent = new Intent(getActivity(), javascriptActivity.class);
                startActivity(jsIntent);
            }
        });
        bootstrapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bsIntent = new Intent(getActivity(), bootstrapActivity.class);
                startActivity(bsIntent);
            }
        });
        return view;
    }
}