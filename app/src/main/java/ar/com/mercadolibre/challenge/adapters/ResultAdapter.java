package ar.com.mercadolibre.challenge.adapters;

import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dto.Result;
import ar.com.mercadolibre.challenge.listeners.ResultListener;
import ar.com.mercadolibre.challenge.utilities.NumberUtilities;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder>{
    private Result[] _data;
    private ResultListener _listener;

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public TextView name;
        public TextView price;
        public TextView originalPrice;
        public TextView ultStock;
        public TextView txtFreeShipping;
        public ImageView imgFreeShipping;
        public TextView txtRate;
        public ImageView imgRate;
        public ImageView photo;

        public ResultViewHolder(View v) {
            super(v);

            card = v.findViewById(R.id.card_result_item);
            name = v.findViewById(R.id.txtItemName);
            price = v.findViewById(R.id.txtItemPrice);
            originalPrice = v.findViewById(R.id.txtItemOriginalPrice);
            ultStock = v.findViewById(R.id.txtUltStock);
            imgRate = v.findViewById(R.id.imgRate);
            txtRate = v.findViewById(R.id.txtRate);
            txtFreeShipping = v.findViewById(R.id.txtFreeShipping);
            imgFreeShipping = v.findViewById(R.id.imgFreeShipping);
            photo = v.findViewById(R.id.imgItemPhoto);
        }
    }

    public ResultAdapter(Result[] data, ResultListener listener) {
        _data = data;
        _listener = listener;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_result, parent, false);
        return new ResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder v, final int i) {
        try {
            final Result item = _data[i];

            v.name.setText(item.getTitle());
            v.price.setText("$ " + NumberUtilities.setFormat(item.getPrice().toString()));

            // original price
            if (item.getOriginalPrice() != null) {
                v.originalPrice.setVisibility(View.VISIBLE);
                v.originalPrice.setText("$ " + NumberUtilities.setFormat(item.getOriginalPrice().toString()));
                v.originalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                v.originalPrice.setVisibility(View.GONE);
            }

            // ult stock
            if (item.getAvailableQuantity() <= 5) {
                v.ultStock.setVisibility(View.VISIBLE);
                v.ultStock.setText(v.card.getResources().getString(R.string.txt_ult_stock).replace("%cant%", String.valueOf(item.getAvailableQuantity())));
            } else {
                v.ultStock.setVisibility(View.GONE);
            }

            // 12 cuotas
            if (item.getInstallments() != null && item.getInstallments().getRate() == 0) {
                v.imgRate.setVisibility(View.VISIBLE);
                v.txtRate.setVisibility(View.VISIBLE);
                v.txtRate.setText(v.card.getResources().getString(R.string.txt_cant_rate).replace("%cant%", String.valueOf(item.getInstallments().getQuantity())));
            } else {
                v.imgRate.setVisibility(View.GONE);
                v.txtRate.setVisibility(View.GONE);
            }

            // envio gratis
            if (item.getShipping() != null && item.getShipping().isFreeShipping()) {
                v.txtFreeShipping.setVisibility(View.VISIBLE);
                v.imgFreeShipping.setVisibility(View.VISIBLE);
            } else {
                v.txtFreeShipping.setVisibility(View.GONE);
                v.imgFreeShipping.setVisibility(View.GONE);
            }

            // carga imagen
            try {
                Picasso.get()
                        .load(item.getThumbnail())
                        .centerCrop()
                        .fit()
                        .into(v.photo);

            } catch (Exception ignored) { }

            // setea click
            v.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (_listener != null) _listener.onItemClick(item);
                }
            });
        }
        catch(Exception ignored){}
    }

    @Override
    public int getItemCount() {
        return _data.length;
    }

    public void setData(Result[] data) {
        _data = data;
    }
}
