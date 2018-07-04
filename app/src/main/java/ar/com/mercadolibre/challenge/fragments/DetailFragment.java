package ar.com.mercadolibre.challenge.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dto.Item;
import ar.com.mercadolibre.challenge.dto.User;
import ar.com.mercadolibre.challenge.exceptions.UndefinedException;
import ar.com.mercadolibre.challenge.listeners.EmptyListener;
import ar.com.mercadolibre.challenge.utilities.NumberUtilities;

public class DetailFragment extends MCFragment<EmptyListener> {

    private Item _item = null;
    private User _seller = null;
    private TextView txtTitle;
    private TextView txtSellerName;
    private TextView txtPrice;
    private TextView txtOriginalPrice;
    private TextView txtCondition;
    private RelativeLayout llFreeShipping;
    private RelativeLayout llLocation;
    private TextView txtCantSold;
    private TextView txtCantAvailable;
    private TextView txtWarranty;
    private TextView txtDescription;
    private TextView txtLocation;
    private Resources _resource;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        try {
            setObjects();
            bindObjects();
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(getActivity()).getMessage());
        }

    }

    private void setObjects() {
        txtTitle = getActivity().findViewById(R.id.txtTitle);
        txtSellerName = getActivity().findViewById(R.id.txtSellerName);
        txtPrice = getActivity().findViewById(R.id.txtPrice);
        txtOriginalPrice = getActivity().findViewById(R.id.txtOriginalPrice);
        txtCondition = getActivity().findViewById(R.id.txtCondition);
        llFreeShipping = getActivity().findViewById(R.id.llFreeShipping);
        txtCantSold = getActivity().findViewById(R.id.txtCantSold);
        txtCantAvailable = getActivity().findViewById(R.id.txtCantAvailable);
        txtWarranty = getActivity().findViewById(R.id.txtWarranty);
        txtDescription = getActivity().findViewById(R.id.txtDescription);
        txtLocation = getActivity().findViewById(R.id.txtLocation);
        llLocation = getActivity().findViewById(R.id.llLocation);
    }

    private void bindObjects() {
        try {
            if (_item != null && _seller != null) {
                // titulo
                if (txtTitle != null) txtTitle.setText(_item.getTitle());

                // vendedor
                if (txtSellerName != null)
                    txtSellerName.setText(_resource.getString(R.string.txt_seller_by) + " " + _seller.getNickname());

                // precio
                if (txtPrice != null) txtPrice.setText("$ " + NumberUtilities.setFormat(_item.getPrice().toString()));
                if (txtOriginalPrice != null) {
                    // original price
                    if (_item.getOriginalPrice() != null) {
                        txtOriginalPrice.setVisibility(View.VISIBLE);
                        txtOriginalPrice.setText("$ " + NumberUtilities.setFormat(String.valueOf(_item.getOriginalPrice())));
                        txtOriginalPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        txtOriginalPrice.setVisibility(View.GONE);
                    }
                }

                // condition & warranty
                if (txtCondition != null && txtWarranty != null) {
                    if(_item.getCondition().equals("new")) {
                        txtCondition.setText(_resource.getString(R.string.txt_condition_new));
                    }
                    else {
                        txtCondition.setText(_resource.getString(R.string.txt_condition_used));
                    }

                    if(_item.getWarranty()== null || _item.getWarranty().isEmpty()) {
                        txtWarranty.setText(R.string.txt_no_warranty);
                    }
                    else {
                        txtWarranty.setText(_resource.getString(R.string.txt_warranty) + " " + _item.getWarranty());
                    }
                }

                // cantidades y garantia
                if(txtCantAvailable != null && txtCantSold != null) {

                    switch(_item.getSoldQuantity()) {
                        case 0:
                            txtCantSold.setText(_resource.getString(R.string.txt_cant_sold_no));
                            break;
                        case 1:
                            txtCantSold.setText(_resource.getString(R.string.txt_cant_sold_one).replace("%cant%", String.valueOf(_item.getSoldQuantity())));
                            break;
                        default:
                            txtCantSold.setText(_resource.getString(R.string.txt_cant_sold).replace("%cant%", String.valueOf(_item.getSoldQuantity())));
                    }

                    if(_item.getAvailableQuantity() > 5) {
                        txtCantAvailable.setText(_resource.getString(R.string.txt_cant_available).replace("%cant%", String.valueOf(_item.getSoldQuantity())));
                    }
                    else {
                        txtCantAvailable.setTextColor(Color.parseColor("#8f0000"));
                        txtCantAvailable.setText(_resource.getString(R.string.txt_ult_stock).replace("%cant%", String.valueOf(_item.getAvailableQuantity())));
                    }
                }

                // location
                if(_item.getGeolocation()!= null) {
                    llLocation.setVisibility(View.VISIBLE);

                    if(txtLocation != null) {
                        txtLocation.setText("Ver ubicación");

                        txtLocation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String url = "https://maps.google.com/?q=" + String.valueOf(_item.getGeolocation().getLatitude()) + "," + String.valueOf(_item.getGeolocation().getLongitude());
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }
                        });
                    }
                }
                else {
                    llLocation.setVisibility(View.GONE);
                }

                // free shipping
                if (_item.getShipping() != null && _item.getShipping().isFreeShipping()) {
                    if(llFreeShipping!=null) {
                        llFreeShipping.setVisibility(View.VISIBLE);
                    }
                } else {
                    llFreeShipping.setVisibility(View.GONE);
                }

                // descripcion
                if(txtDescription != null) {
                    txtDescription.setText(_item.getDescription());
                }
            }
        }
        catch(Exception e) {
            throw e;
        }
    }


    /***************************************
     *            DATA METHODS
     ***************************************/
    public void setData(Item item, User seller, Resources res) {
        _item = item;
        _seller = seller;
        _resource = res;

        bindObjects();
    }
}
