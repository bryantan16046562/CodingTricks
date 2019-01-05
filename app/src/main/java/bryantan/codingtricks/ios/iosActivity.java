package bryantan.codingtricks.ios;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import bryantan.codingtricks.R;

public class iosActivity extends AppCompatActivity {
    private RecyclerView iosrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ios);
        setUpRecyclerView();
        populateRecyclerView();
    }

    /**
     * setup the recyclerview here
     */
    private void setUpRecyclerView() {
        iosrecyclerView = findViewById(R.id.iosrecyclerview);
        iosrecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        iosrecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * populate the recyclerview and implement the click event here
     */
    private void populateRecyclerView() {
        final ArrayList<iosYoutubeVideoModel> iosyoutubeVideoModelArrayList = generateDummyVideoList();
        iosYoutubeVideoAdapter adapter = new iosYoutubeVideoAdapter(this, iosyoutubeVideoModelArrayList);
        iosrecyclerView.setAdapter(adapter);

        //set click event
        iosrecyclerView.addOnItemTouchListener(new iosRecyclerViewOnClickListener(this, new iosRecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //start youtube player activity by passing selected video id via intent
                startActivity(new Intent(iosActivity.this, iosYoutubePlayerActivity.class)
                        .putExtra("iosvideo_id", iosyoutubeVideoModelArrayList.get(position).getIosvideoId()));
            }
        }));
    }
    /**
     * method to generate dummy array list of videos
     *
     * @return
     */
    private ArrayList<iosYoutubeVideoModel> generateDummyVideoList() {
        ArrayList<iosYoutubeVideoModel> iosyoutubeVideoModelArrayList = new ArrayList<>();

        //get the video id array, title array and duration array from strings.xml
        String[] videoiosIDArray = getResources().getStringArray(R.array.video_iosid_array);
        String[] videoiosTitleArray = getResources().getStringArray(R.array.video_iostitle_array);
        String[] videoiosDurationArray = getResources().getStringArray(R.array.video_iosduration_array);

        //loop through all items and add them to arraylist
        for (int i = 0; i < videoiosIDArray.length; i++) {
            iosYoutubeVideoModel youtubeVideoModel = new iosYoutubeVideoModel();
            youtubeVideoModel.setIosvideoId(videoiosIDArray[i]);
            youtubeVideoModel.setIostitle(videoiosTitleArray[i]);
            youtubeVideoModel.setIosduration(videoiosDurationArray[i]);

            iosyoutubeVideoModelArrayList.add(youtubeVideoModel);
        }

        return iosyoutubeVideoModelArrayList;
    }
}
