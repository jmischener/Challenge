package ar.com.mercadolibre.challenge.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Layout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dto.PicturesItem;
import ar.com.mercadolibre.challenge.dto.SearchHistory;
import ar.com.mercadolibre.challenge.dto.SearchHistoryItem;
import ar.com.mercadolibre.challenge.listeners.SearchListener;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private List<SearchHistory> _data;
    private SearchListener _listener;

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public LinearLayout gallery;
        public ImageView close;
        public TextView searchTitle;

        public SearchViewHolder(View v) {
            super(v);

            card = v.findViewById(R.id.card_search_item);
            gallery = v.findViewById(R.id.llGallerySearch);
            searchTitle = v.findViewById(R.id.txtSearchTitle);
            close = v.findViewById(R.id.imgClose);
        }
    }

    public SearchAdapter(List<SearchHistory> data, SearchListener listener) {
        _data = data;
        _listener = listener;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_search, parent, false);
        return new SearchAdapter.SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.SearchViewHolder v, final int i) {
        try {
            final SearchHistory search = _data.get(i);

            // setea titulo
            v.searchTitle.setText(search.getSearchQuery());

            // setea foto
            v.gallery.removeAllViews();
            for (final SearchHistoryItem searchItem : search.getItems()) {
                // crea layout con imagen, titulo y precio
                View layout = insertImage(v.card.getContext(), searchItem);

                // setea formato de efecto de click
                TypedValue outValue = new TypedValue();
                v.card.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                layout.setBackgroundResource(outValue.resourceId);

                // agrega layout al horizontalscroll
                v.gallery.addView(layout);

                // setea click en el elemento
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (_listener != null)
                            _listener.onItemClick(search.getSearchQuery(), searchItem);
                    }
                });

                // setea close
                v.close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (_listener != null)
                            _listener.onDeleteSearch(search.getSearchQuery());
                    }
                });
            }
        }
        catch(Exception e) {}
    }

    private View insertImage(Context context, final SearchHistoryItem search){
        LinearLayout layout = new LinearLayout(context.getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(350, 350));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(context.getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView txtTitleItem = new TextView(context.getApplicationContext());
        txtTitleItem.setLayoutParams(new LinearLayout.LayoutParams(300, ViewPager.LayoutParams.WRAP_CONTENT));
        txtTitleItem.setPadding(0,3,0,2);
        txtTitleItem.setTextAppearance(context, R.style.MC_Theme_Search_Item_Title);
        txtTitleItem.setGravity(Gravity.CENTER_HORIZONTAL);
        txtTitleItem.setSingleLine(false);
        txtTitleItem.setEllipsize(TextUtils.TruncateAt.END);
        txtTitleItem.setLines(2);
        txtTitleItem.setText(search.getTitle());

        TextView txtPriceItem = new TextView(context.getApplicationContext());
        txtPriceItem.setLayoutParams(new LinearLayout.LayoutParams(300, ViewPager.LayoutParams.WRAP_CONTENT));
        txtPriceItem.setPadding(0,3,0,2);
        txtPriceItem.setTextAppearance(context, R.style.MC_Theme_Search_Item_Price);
        txtPriceItem.setGravity(Gravity.CENTER_HORIZONTAL);
        txtPriceItem.setText("$ " + search.getPrice());

        Picasso.get()
                .load(search.getURLImage())
                .fit()
                .error(R.drawable.photo_result_empty)
                .into(imageView);

        layout.addView(imageView);
        layout.addView(txtTitleItem);
        layout.addView(txtPriceItem);
        return layout;
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public void setData(List<SearchHistory> data) {
        _data = data;
    }
}
