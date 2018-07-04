package ar.com.mercadolibre.challenge.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.adapters.ResultAdapter;
import ar.com.mercadolibre.challenge.dto.Result;
import ar.com.mercadolibre.challenge.listeners.EndlessRecyclerViewScrollListener;
import ar.com.mercadolibre.challenge.listeners.ResultListener;

public class ResultFragment extends MCFragment<ResultListener> {

    private EndlessRecyclerViewScrollListener scrollListener;
    private ResultAdapter _adapter;
    private RecyclerView _recycler;
    private RelativeLayout _rlEmpty;
    private Result[] _data = null;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        // Obtener el Recycler
        _recycler = getActivity().findViewById(R.id.listResult);
        _rlEmpty = getActivity().findViewById(R.id.rlEmpty);

        // setea endless scroll
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        _recycler.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(totalItemsCount);
            }
        };
        // agrega scroll endless
        _recycler.addOnScrollListener(scrollListener);

        // obtener empty list
        //empty = getActivity().findViewById(R.id.empty_view_clases);
        _recycler.setHasFixedSize(true);

        // crea un nuevo adaptador
        _adapter = new ResultAdapter(_data, _listener);
        _recycler.setAdapter(_adapter);

        // si hay data, manda update al adapter
        if(_data.length>0) {
            _recycler.setVisibility(View.VISIBLE);
            _rlEmpty.setVisibility(View.GONE);
        }
        else { // si no hay datos, muestra leyenda
            _recycler.setVisibility(View.GONE);
            _rlEmpty.setVisibility(View.VISIBLE);
        }
    }


    /***************************************
     *            EVENT METHODS
     ***************************************/
    public void loadNextDataFromApi(int offset) {
       if(_listener!=null) {
           _listener.onLoadMoreData(offset);
       }
    }


    /***************************************
     *            DATA METHODS
     ***************************************/
    public void setData(Result[] data) {
        _data = data;
        //manda update al adapter
        if (_adapter != null) {
            _adapter.setData(_data);
            _adapter.notifyDataSetChanged();

            // si hay data, manda update al adapter
            if(_data.length>0) {
                _recycler.setVisibility(View.VISIBLE);
                _rlEmpty.setVisibility(View.GONE);
            }
            else { // si no hay datos, muestra leyenda
                _recycler.setVisibility(View.GONE);
                _rlEmpty.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setUpdateData(Result[] data) {
        Result[] dataFinal = new Result[_data.length + data.length];
        int i=0;
        for (Result r:_data) {
            dataFinal[i] = r;
            i++;
        }
        for (Result r:data) {
            dataFinal[i] = r;
            i++;
        }

        _data = dataFinal;

        //manda update al adapter
        if (_adapter != null) {
            _adapter.setData(_data);
            _adapter.notifyDataSetChanged();

            // si hay data, manda update al adapter
            if(_data.length>0) {
                _recycler.setVisibility(View.VISIBLE);
                _rlEmpty.setVisibility(View.GONE);
            }
            else { // si no hay datos, muestra leyenda
                _recycler.setVisibility(View.GONE);
                _rlEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}

