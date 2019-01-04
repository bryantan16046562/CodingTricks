package bryantan.codingtricks.web.jquery;
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

public class jqueryActivity extends AppCompatActivity {
    private static final String TAG = "jQuery Activity";

    private RecyclerView jqrecyclerView;
    //youtube player fragment
    private YouTubePlayerSupportFragment jqyouTubePlayerFragment;
    private ArrayList<String> jqyoutubeVideoArrayList;

    //youtube player to play video when new video selected
    private YouTubePlayer jqyouTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jquery);
        generateDummyVideoList();
        initializeYoutubePlayer();
        setUpRecyclerView();
        populateRecyclerView();
    }
    /**
     * initialize youtube player via Fragment and get instance of YoutubePlayer
     */
    private void initializeYoutubePlayer() {
        jqyouTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.jqplayerfragment);
        if (jqyouTubePlayerFragment == null)
            return;
        jqyouTubePlayerFragment.initialize(Constants.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    jqyouTubePlayer = player;
                    //set the player style default
                    jqyouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    //cue the 1st video by default
                    jqyouTubePlayer.cueVideo(jqyoutubeVideoArrayList.get(0));
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
        jqrecyclerView = findViewById(R.id.jqrecyclerview);
        jqrecyclerView.setHasFixedSize(true);
        //Horizontal direction recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        jqrecyclerView.setLayoutManager(linearLayoutManager);
    }
    /**
     * populate the recycler view and implement the click event here
     */
    private void populateRecyclerView() {
        final webYoutubeVideoAdapter adapter = new webYoutubeVideoAdapter(this, jqyoutubeVideoArrayList);
        jqrecyclerView.setAdapter(adapter);
        //set click event
        jqrecyclerView.addOnItemTouchListener(new webRecyclerViewOnClickListener(this, new webRecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (jqyouTubePlayerFragment != null && jqyouTubePlayer != null) {
                    //update selected position
                    adapter.setSelectedPosition(position);
                    //load selected video
                    jqyouTubePlayer.cueVideo(jqyoutubeVideoArrayList.get(position));
                }
            }
        }));
    }
    /**
     * method to generate dummy array list of videos
     */
    private void generateDummyVideoList() {
        jqyoutubeVideoArrayList = new ArrayList<>();

        //get the video id array from strings.xml
        String[] videojqIDArray = getResources().getStringArray(R.array.jqvideosarray);

        //add all videos to array list
        Collections.addAll(jqyoutubeVideoArrayList, videojqIDArray);
    }
}
