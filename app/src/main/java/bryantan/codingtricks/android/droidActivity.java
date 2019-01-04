package bryantan.codingtricks.android;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import bryantan.codingtricks.R;

public class droidActivity extends AppCompatActivity {
    private RecyclerView droidrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid);
        setUpRecyclerView();
        populateRecyclerView();

    }

    /**
     * setup the recyclerview here
     */
    private void setUpRecyclerView() {
        droidrecyclerView = findViewById(R.id.droidrecyclerview);
        droidrecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        droidrecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * populate the recyclerview and implement the click event here
     */
    private void populateRecyclerView() {
        final ArrayList<droidYoutubeVideoModel> droidyoutubeVideoModelArrayList = generateDummyVideoList();
        droidYoutubeVideoAdapter adapter = new droidYoutubeVideoAdapter(this, droidyoutubeVideoModelArrayList);
        droidrecyclerView.setAdapter(adapter);

        //set click event
        droidrecyclerView.addOnItemTouchListener(new droidRecyclerViewOnClickListener(this, new droidRecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //start youtube player activity by passing selected video id via intent
                startActivity(new Intent(droidActivity.this, droidYoutubePlayerActivity.class)
                        .putExtra("droidvideo_id", droidyoutubeVideoModelArrayList.get(position).getDroidvideoId()));

            }
        }));
    }


    /**
     * method to generate dummy array list of videos
     *
     * @return
     */
    private ArrayList<droidYoutubeVideoModel> generateDummyVideoList() {
        ArrayList<droidYoutubeVideoModel> droidyoutubeVideoModelArrayList = new ArrayList<>();

        //get the video id array, title array and duration array from strings.xml
        String[] videodroidIDArray = getResources().getStringArray(R.array.video_androidid_array);
        String[] videodroidTitleArray = getResources().getStringArray(R.array.video_androidtitle_array);
        String[] videodroidDurationArray = getResources().getStringArray(R.array.video_androidduration_array);

        //loop through all items and add them to arraylist
        for (int i = 0; i < videodroidIDArray.length; i++) {
            droidYoutubeVideoModel youtubeVideoModel = new droidYoutubeVideoModel();
            youtubeVideoModel.setDroidvideoId(videodroidIDArray[i]);
            youtubeVideoModel.setDroidtitle(videodroidTitleArray[i]);
            youtubeVideoModel.setDroidduration(videodroidDurationArray[i]);

            droidyoutubeVideoModelArrayList.add(youtubeVideoModel);
        }

        return droidyoutubeVideoModelArrayList;
    }
}
