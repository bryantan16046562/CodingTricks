package bryantan.codingtricks.web.javascript;
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

public class javascriptActivity extends AppCompatActivity {
    private static final String TAG = "javascript Activity";

    private RecyclerView jsrecyclerView;
    //youtube player fragment
    private YouTubePlayerSupportFragment jsyouTubePlayerFragment;
    private ArrayList<String> jsyoutubeVideoArrayList;

    //youtube player to play video when new video selected
    private YouTubePlayer jsyouTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javascript);
        generateDummyVideoList();
        initializeYoutubePlayer();
        setUpRecyclerView();
        populateRecyclerView();
    }
    /**
     * initialize youtube player via Fragment and get instance of YoutubePlayer
     */
    private void initializeYoutubePlayer() {
        jsyouTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.jsplayerfragment);
        if (jsyouTubePlayerFragment == null)
            return;
        jsyouTubePlayerFragment.initialize(Constants.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    jsyouTubePlayer = player;
                    //set the player style default
                    jsyouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    //cue the 1st video by default
                    jsyouTubePlayer.cueVideo(jsyoutubeVideoArrayList.get(0));
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
        jsrecyclerView = findViewById(R.id.jsrecyclerview);
        jsrecyclerView.setHasFixedSize(true);
        //Horizontal direction recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        jsrecyclerView.setLayoutManager(linearLayoutManager);
    }
    /**
     * populate the recycler view and implement the click event here
     */
    private void populateRecyclerView() {
        final webYoutubeVideoAdapter adapter = new webYoutubeVideoAdapter(this, jsyoutubeVideoArrayList);
        jsrecyclerView.setAdapter(adapter);
        //set click event
        jsrecyclerView.addOnItemTouchListener(new webRecyclerViewOnClickListener(this, new webRecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (jsyouTubePlayerFragment != null && jsyouTubePlayer != null) {
                    //update selected position
                    adapter.setSelectedPosition(position);
                    //load selected video
                    jsyouTubePlayer.cueVideo(jsyoutubeVideoArrayList.get(position));
                }
            }
        }));
    }
    /**
     * method to generate dummy array list of videos
     */
    private void generateDummyVideoList() {
        jsyoutubeVideoArrayList = new ArrayList<>();

        //get the video id array from strings.xml
        String[] videojsIDArray = getResources().getStringArray(R.array.jsvideosarray);

        //add all videos to array list
        Collections.addAll(jsyoutubeVideoArrayList, videojsIDArray);
    }
}
