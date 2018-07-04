package ar.com.mercadolibre.challenge.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dto.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{
    private Question[] _data;

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        public CardView card;
        public TextView question;
        public TextView answer;

        public QuestionViewHolder(View v) {
            super(v);

            card = v.findViewById(R.id.card_result_item);
            question = v.findViewById(R.id.txtQuestion);
            answer = v.findViewById(R.id.txtAnswer);
        }
    }

    public QuestionAdapter(Question[] data) {
        _data = data;
    }

    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_question, parent, false);
        return new QuestionAdapter.QuestionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.QuestionViewHolder v, final int i) {
        try {
            final Question item = _data[i];

            v.question.setText(item.getText());

            // answer
            if (item.getAnswer() != null) {
                v.answer.setText(item.getAnswer().getText());
            }
            else v.answer.setText(" ");
        }
        catch(Exception ignored) {}
    }

    @Override
    public int getItemCount() {
        return _data.length;
    }

    public void setData(Question[] data) {
        _data = data;
    }
}
