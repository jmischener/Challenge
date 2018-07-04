package ar.com.mercadolibre.challenge.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dal.SearchHistoryDAL;
import ar.com.mercadolibre.challenge.dto.SearchHistory;
import ar.com.mercadolibre.challenge.dto.SearchHistoryItem;
import ar.com.mercadolibre.challenge.exceptions.UndefinedException;
import ar.com.mercadolibre.challenge.fragments.SearchFragment;
import ar.com.mercadolibre.challenge.listeners.SearchListener;
import ar.com.mercadolibre.challenge.utilities.ActivitiesUtil;
import ar.com.mercadolibre.challenge.utilities.NetworkUtil;

public class SearchActivity extends MCActivity implements SearchListener {
    private SearchFragment frgListSearch;
    private List<SearchHistory> listSearch;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_search);

            initialize();

            //checkGPSPermissionGranted();
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            initialize();
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) searchViewItem.getActionView();

        searchViewAndroidActionBar.setQueryHint(this.getResources().getString(R.string.txt_searchview));

        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.isEmpty()) {
                    searchViewAndroidActionBar.clearFocus();
                    moveToNext(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void initialize() {
        try {
            getSearchHistory();

            // setea fragmento de listado
            Fragment fragment = new SearchFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frmListSearch, fragment);
            transaction.commit();

            // setea listener para saber cuando hace click
            frgListSearch = (SearchFragment) fragment;

            frgListSearch.setData(listSearch);
            frgListSearch.setListener(this);
        }
        catch(Exception ex) {
            throw ex;
        }
    }


    /***************************************
     *            EVENT METHODS
     ***************************************/
    public void moveToNext(String query) {
        if(NetworkUtil.isConnected(this)) {
            SearchHistoryDAL.getInstance(this).Save(listSearch);
            ActivitiesUtil.moveToWithParameters(this, ResultActivity.class, false, "searchText", query);
        }
        else {
            withoutConnectionEvent();
        }
    }

    @Override
    public void onItemClick(String title, SearchHistoryItem item) {
        if(NetworkUtil.isConnected(this)) {
            ActivitiesUtil.moveToWithParameters(this, ItemActivity.class, false, "itemID", item.getId(), "title", title);
        }
        else {
            withoutConnectionEvent();
        }
    }

    @Override
    public void onDeleteSearch(String title) {
        try {
            // busca busqueda a eliminar
            for(SearchHistory s: listSearch) {
                // si es igual
                if(s.getSearchQuery().toUpperCase().equals(title.toUpperCase())) {
                    listSearch.remove(s);

                    // guarda actualizacion
                    SearchHistoryDAL.getInstance(this).Save(listSearch);

                    frgListSearch.setData(listSearch);
                    return;
                }
            }
        }
        catch(Exception ignored) {throw ignored;}
    }

    /***************************************
     *            DATA METHODS
     ***************************************/
    private void getSearchHistory() {
        try {
            listSearch = SearchHistoryDAL.getInstance(this).GetAll();
            listSearch = new ArrayList<>(listSearch);
        }
        catch(Exception e) {
            listSearch = new ArrayList<>();
            SearchHistoryDAL.getInstance(this).Save(listSearch);
        }
    }

}
