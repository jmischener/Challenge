package ar.com.mercadolibre.challenge.dal;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.com.mercadolibre.challenge.dto.SearchHistory;

public class SearchHistoryDAL  extends ContextWrapper {

    private static SearchHistoryDAL sInstance = null;

    private SearchHistoryDAL(Context base)
    {
        super(base);
    }
    public static SearchHistoryDAL getInstance(Context base) {
        if (sInstance == null) {
            sInstance = new SearchHistoryDAL(base);
        }
        return sInstance;
    }

    public void Save(List<SearchHistory> listSearches) {
        try {
            Gson g = new Gson();
            SharedPreferences prefe = getSharedPreferences("MCData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefe.edit();

            String json = g.toJson(listSearches);
            editor.putString("search_history", json);

            // confirma cambios
            editor.commit();

        }
        catch (Exception ignored) { }
    }

    public List<SearchHistory> GetAll() {
        try {
            List<SearchHistory> ret;
            String json;
            Gson g = new Gson();

            SharedPreferences prefe = getSharedPreferences("MCData", Context.MODE_PRIVATE);

            // obtiene busquedas
            json = prefe.getString("search_history", "").trim();
            ret = Arrays.asList(g.fromJson(json, SearchHistory[].class));
            return ret;
        }
        catch(Exception ignored) { return new ArrayList<>(); }
    }
}
