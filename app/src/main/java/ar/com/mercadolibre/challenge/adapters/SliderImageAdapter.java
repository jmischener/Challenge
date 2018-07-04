package ar.com.mercadolibre.challenge.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dto.PicturesItem;
import ar.com.mercadolibre.challenge.utilities.ImageTransformationUtil;

public class SliderImageAdapter  extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Activity activity;
    private List<PicturesItem> imageSliderList;

    public SliderImageAdapter(Activity activity, List<PicturesItem> pictureList) {
        try {
            this.activity = activity;
            this.imageSliderList = pictureList;
        }
        catch(Exception ignored) { }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_slider, container, false);

        try {
            TextView txtCantImage = view.findViewById(R.id.txtCantImage);
            String text = view.getResources().getString(R.string.txt_cant_image).replace("%position%", String.valueOf(position+1));

            txtCantImage.setText(text.replace("%total%", String.valueOf(imageSliderList.size())));

            ImageView im_slider = view.findViewById(R.id.im_slider);
            Picasso.get()
                    .load(Uri.parse(imageSliderList.get(position).getUrl()))
                    .error(R.drawable.photo_result_empty)
                    .into(im_slider);

            container.addView(view);
        }
        catch(Exception ignored) { }

        return view;
    }


    @Override
    public int getCount() {
        return imageSliderList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}