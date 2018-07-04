package ar.com.mercadolibre.challenge.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.adapters.QuestionAdapter;
import ar.com.mercadolibre.challenge.dto.Question;
import ar.com.mercadolibre.challenge.listeners.EmptyListener;

public class QuestionFragment extends MCFragment<EmptyListener> {

    private QuestionAdapter _adapter;
    private RecyclerView _recycler;
    private Question[] _data = null;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        // Obtener el Recycler
        _recycler = getActivity().findViewById(R.id.listQuestion);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        _recycler.setLayoutManager(linearLayoutManager);

        // crea un nuevo adaptador
        _adapter = new QuestionAdapter(_data);
        _recycler.setAdapter(_adapter);
    }


    /***************************************
     *            DATA METHODS
     ***************************************/
    public void setData(Question[] data) {
        if(data == null) {
            data = new Question[0];
        }
        _data = data;

        //manda update al adapter
        if (_adapter != null) {
            _adapter.setData(_data);
            _adapter.notifyDataSetChanged();
        }

    }
}
