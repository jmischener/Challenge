package ar.com.mercadolibre.challenge.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.adapters.SearchAdapter;
import ar.com.mercadolibre.challenge.dto.SearchHistory;
import ar.com.mercadolibre.challenge.exceptions.UndefinedException;
import ar.com.mercadolibre.challenge.listeners.EmptyListener;
import ar.com.mercadolibre.challenge.listeners.SearchListener;

public class SearchFragment  extends MCFragment<SearchListener> {
    private SearchAdapter _adapter;
    private RecyclerView _recycler;
    private RelativeLayout _rlEmpty;
    private List<SearchHistory> _data = null;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        try {
            // Obtener el Recycler
            _recycler = getActivity().findViewById(R.id.listSearch);
            _rlEmpty = getActivity().findViewById(R.id.rlEmpty);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            _recycler.setLayoutManager(linearLayoutManager);

            // crea un nuevo adaptador
            _adapter = new SearchAdapter(_data, _listener);
            _recycler.setAdapter(_adapter);

            // si hay data, manda update al adapter
            if(_data.size()>0) {
                _recycler.setVisibility(View.VISIBLE);
                _rlEmpty.setVisibility(View.GONE);
            }
            else { // si no hay datos, muestra leyenda
                _recycler.setVisibility(View.GONE);
                _rlEmpty.setVisibility(View.VISIBLE);
            }
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(getActivity()).getMessage());
        }
    }


    /***************************************
     *            DATA METHODS
     ***************************************/
    public void setData(List<SearchHistory> data) {
        try {
            _data = data;

            //manda update al adapter
            if (_adapter != null) {
                _adapter.setData(_data);
                _adapter.notifyDataSetChanged();

                // si hay data, manda update al adapter
                if(_data.size()>0) {
                    _recycler.setVisibility(View.VISIBLE);
                    _rlEmpty.setVisibility(View.GONE);
                }
                else { // si no hay datos, muestra leyenda
                    _recycler.setVisibility(View.GONE);
                    _rlEmpty.setVisibility(View.VISIBLE);
                }
            }
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(getActivity()).getMessage());
        }
    }
}
