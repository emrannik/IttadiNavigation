package com.androidnik.itaydi_nav.adapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnik.itaydi_nav.R;
import com.androidnik.itaydi_nav.YoutubePlayerActivity;
import com.androidnik.itaydi_nav.api.Constants;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.util.ArrayList;

/**
 * Created by nik on 10/11/19.
 */
public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeViewHolder> {
    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<String> youtubeVideoModelArrayList;

    private ArrayList<String> youtubeVideoTitleArrayList;
    private int selectedPosition = 0;

    public YoutubeVideoAdapter(Context context, ArrayList<String> youtubeVideoModelArrayList,ArrayList<String>youtubeVideoTitleArrayList) {
        this.context = context;
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;
        this.youtubeVideoTitleArrayList=youtubeVideoTitleArrayList;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.youtube_video_custom_layout, parent, false);

        return new YoutubeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final YoutubeViewHolder holder, final int position) {
        //final String youtubeVideoModel = youtubeVideoModelArrayList.get(position);

//        holder.videoTitle.setText(youtubeVideoTitleArrayList.get(position));

        /*  initialize the thumbnail image view , we need to pass Developer Key */

        //holder.videoThumbnailImageView1.
        holder.videoThumbnailImageView.initialize(Constants.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(youtubeVideoModelArrayList.get(position));
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
                //holder.videoThumbnailImageView1.inti

            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e(TAG, "Youtube Initialization Failure");

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start youtube player activity by passing selected video id via intent
                Intent intent=new Intent(v.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("video_id",youtubeVideoModelArrayList.get(position));
               v.getContext().startActivity(intent);

                //Toast.makeText(v.getContext(),"Clicked"+position, Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoModelArrayList != null ? youtubeVideoModelArrayList.size() : 0;

    }



    public static class YoutubeViewHolder  extends RecyclerView.ViewHolder{
        public YouTubeThumbnailView videoThumbnailImageView;
        public CardView youtubeCardView,videoThumbnailImageView1;
        public LinearLayout linearLayout;
        public ImageView likeImageView,shareImageView;

        public YoutubeViewHolder(View itemView) {
            super(itemView);
            videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
           //videoThumbnailImageView1 = itemView.findViewById(R.id.video_thumbnail_image_view1);
            youtubeCardView = itemView.findViewById(R.id.youtube_row_card_view);
            //videoTitle = itemView.findViewById(R.id.video_title_label);
            linearLayout=itemView.findViewById(R.id.linear_layout);
            /*likeImageView = (ImageView) itemView.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) itemView.findViewById(R.id.shareImageView);

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id = (int)likeImageView.getTag();
                    if( id == R.drawable.ic_like){

                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);
                        Toast.makeText(v.getContext()," added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(v.getContext()," removed from favourites",Toast.LENGTH_SHORT).show();

                    }
                }
            });

            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +(videoThumbnailImageView.getId())+v.getResources().getResourceEntryName((int)videoThumbnailImageView.getTag()));

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    v.getContext().startActivity(Intent.createChooser(shareIntent, v.getResources().getText(R.string.send_to)));
                }
            });*/


        }
    }
}
