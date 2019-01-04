package bryantan.codingtricks.web.css;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import bryantan.codingtricks.R;
import bryantan.codingtricks.Constants;
import bryantan.codingtricks.web.webRecyclerViewOnClickListener;
import bryantan.codingtricks.web.webYoutubeVideoAdapter;
import java.util.ArrayList;
import java.util.Collections;

public class cssActivity extends AppCompatActivity {
    private static final String TAG = "Css Activity";

    private RecyclerView cssrecyclerView;
    //youtube player fragment
    private YouTubePlayerSupportFragment cssyouTubePlayerFragment;
    private ArrayList<String> cssyoutubeVideoArrayList;

    //youtube player to play video when new video selected
    private YouTubePlayer cssyouTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css);
        generateDummyVideoList();
        initializeYoutubePlayer();
        setUpRecyclerView();
        populateRecyclerView();
    }
    /**
     * initialize youtube player via Fragment and get instance of YoutubePlayer
     */
    private void initializeYoutubePlayer() {
        cssyouTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.cssplayerfragment);
        if (cssyouTubePlayerFragment == null)
            return;
        cssyouTubePlayerFragment.initialize(Constants.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    cssyouTubePlayer = player;
                    //set the player style default
                    cssyouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    //cue the 1st video by default
                    cssyouTubePlayer.cueVideo(cssyoutubeVideoArrayList.get(0));
                }
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }
    /**
     * setup the recycler view here
     */
    private void setUpRecyclerView() {
        cssrecyclerView = findViewById(R.id.cssrecyclerview);
        cssrecyclerView.setHasFixedSize(true);
        //Horizontal direction recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        cssrecyclerView.setLayoutManager(linearLayoutManager);
    }
    /**
     * populate the recycler view and implement the click event here
     */
    private void populateRecyclerView() {
        final webYoutubeVideoAdapter adapter = new webYoutubeVideoAdapter(this, cssyoutubeVideoArrayList);
        cssrecyclerView.setAdapter(adapter);
        //set click event
        cssrecyclerView.addOnItemTouchListener(new webRecyclerViewOnClickListener(this, new webRecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (cssyouTubePlayerFragment != null && cssyouTubePlayer != null) {
                    //update selected position
                    adapter.setSelectedPosition(position);
                    //load selected video
                    cssyouTubePlayer.cueVideo(cssyoutubeVideoArrayList.get(position));
                }
            }
        }));
    }
    /**
     * method to generate dummy array list of videos
     */
    private void generateDummyVideoList() {
        cssyoutubeVideoArrayList = new ArrayList<>();

        //get the video id array from strings.xml
        String[] videocssIDArray = getResources().getStringArray(R.array.htmlcssvideosarray);

        //add all videos to array list
        Collections.addAll(cssyoutubeVideoArrayList, videocssIDArray);
    }
}
