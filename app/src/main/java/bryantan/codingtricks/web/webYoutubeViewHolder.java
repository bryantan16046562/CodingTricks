package bryantan.codingtricks.web;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.youtube.player.YouTubeThumbnailView;
import bryantan.codingtricks.R;

public class webYoutubeViewHolder extends RecyclerView.ViewHolder{
    public YouTubeThumbnailView webvideoThumbnailImageView;
    public CardView webyoutubeCardView;

    public webYoutubeViewHolder(View itemView) {
        super(itemView);
        webvideoThumbnailImageView = itemView.findViewById(R.id.web_video_thumbnail_image_view);
        webyoutubeCardView = itemView.findViewById(R.id.web_youtube_row_card_view);

    }
}
