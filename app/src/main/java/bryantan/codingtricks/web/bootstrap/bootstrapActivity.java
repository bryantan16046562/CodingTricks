package bryantan.codingtricks.web.bootstrap;

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

public class bootstrapActivity extends AppCompatActivity {
    private static final String TAG = "bootstrap Activity";

    private RecyclerView bsrecyclerView;
    //youtube player fragment
    private YouTubePlayerSupportFragment bsyouTubePlayerFragment;
    private ArrayList<String> bsyoutubeVideoArrayList;

    //youtube player to play video when new video selected
    private YouTubePlayer bsyouTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bootstrap);
        generateDummyVideoList();
        initializeYoutubePlayer();
        setUpRecyclerView();
        populateRecyclerView();
    }
    /**
     * initialize youtube player via Fragment and get instance of YoutubePlayer
     */
    private void initializeYoutubePlayer() {
        bsyouTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.bsplayerfragment);
        if (bsyouTubePlayerFragment == null)
            return;
        bsyouTubePlayerFragment.initialize(Constants.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    bsyouTubePlayer = player;
                    //set the player style default
                    bsyouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    //cue the 1st video by default
                    bsyouTubePlayer.cueVideo(bsyoutubeVideoArrayList.get(0));
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
        bsrecyclerView = findViewById(R.id.bsrecyclerview);
        bsrecyclerView.setHasFixedSize(true);
        //Horizontal direction recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bsrecyclerView.setLayoutManager(linearLayoutManager);
    }
    /**
     * populate the recycler view and implement the click event here
     */
    private void populateRecyclerView() {
        final webYoutubeVideoAdapter adapter = new webYoutubeVideoAdapter(this, bsyoutubeVideoArrayList);
        bsrecyclerView.setAdapter(adapter);
        //set click event
        bsrecyclerView.addOnItemTouchListener(new webRecyclerViewOnClickListener(this, new webRecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (bsyouTubePlayerFragment != null && bsyouTubePlayer != null) {
                    //update selected position
                    adapter.setSelectedPosition(position);
                    //load selected video
                    bsyouTubePlayer.cueVideo(bsyoutubeVideoArrayList.get(position));
                }
            }
        }));
    }
    /**
     * method to generate dummy array list of videos
     */
    private void generateDummyVideoList() {
        bsyoutubeVideoArrayList = new ArrayList<>();

        //get the video id array from strings.xml
        String[] videobsIDArray = getResources().getStringArray(R.array.bsvideosarray);

        //add all videos to array list
        Collections.addAll(bsyoutubeVideoArrayList, videobsIDArray);
    }
}
