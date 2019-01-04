package bryantan.codingtricks.web;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.util.ArrayList;

import bryantan.codingtricks.Constants;
import bryantan.codingtricks.R;

public class webYoutubeVideoAdapter extends RecyclerView.Adapter<webYoutubeViewHolder> {
    private static final String TAG = webYoutubeVideoAdapter.class.getSimpleName();
    private Context webcontext;
    private ArrayList<String> webyoutubeVideoModelArrayList;

    //position to check which position is selected
    private int selectedPosition = 0;

    public webYoutubeVideoAdapter(Context context, ArrayList<String> youtubeVideoModelArrayList) {
        this.webcontext = context;
        this.webyoutubeVideoModelArrayList = youtubeVideoModelArrayList;
    }
    @Override
    public webYoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.web_youtube_video_custom_layout, parent, false);
        return new webYoutubeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(webYoutubeViewHolder holder, final int position) {
        //if selected position is equal to that mean view is selected so change the cardview color
        if (selectedPosition == position) {
            holder.webyoutubeCardView.setCardBackgroundColor(ContextCompat.getColor(webcontext, R.color.colorPrimary));
        } else {
            //if selected position is not equal to that mean view is not selected so change the cardview color to white back again
            holder.webyoutubeCardView.setCardBackgroundColor(ContextCompat.getColor(webcontext, android.R.color.white));
        }
        /*  initialize the thumbnail image view , we need to pass Developer Key */
        holder.webvideoThumbnailImageView.initialize(Constants.API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(webyoutubeVideoModelArrayList.get(position));
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }
                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }
            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure");

            }
        });
    }
    @Override
    public int getItemCount() {
        return webyoutubeVideoModelArrayList != null ? webyoutubeVideoModelArrayList.size() : 0;
    }
    /**
     * method the change the selected position when item clicked
     * @param selectedPosition
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        //when item selected notify the adapter
        notifyDataSetChanged();
    }
}
