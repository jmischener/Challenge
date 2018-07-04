package ar.com.mercadolibre.challenge.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dal.SearchHistoryDAL;
import ar.com.mercadolibre.challenge.dto.Result;
import ar.com.mercadolibre.challenge.dto.SearchHistory;
import ar.com.mercadolibre.challenge.dto.SearchHistoryItem;
import ar.com.mercadolibre.challenge.exceptions.ConnectionFailedException;
import ar.com.mercadolibre.challenge.exceptions.DefinedException;
import ar.com.mercadolibre.challenge.exceptions.UndefinedException;
import ar.com.mercadolibre.challenge.fragments.ResultFragment;
import ar.com.mercadolibre.challenge.listeners.ResponseWSListenerWithPaging;
import ar.com.mercadolibre.challenge.listeners.ResultListener;
import ar.com.mercadolibre.challenge.utilities.ActivitiesUtil;
import ar.com.mercadolibre.challenge.utilities.NetworkUtil;
import ar.com.mercadolibre.challenge.utilities.NumberUtilities;
import ar.com.mercadolibre.challenge.webservices.RestCallsWS;

public class ResultActivity extends MCActivity implements ResultListener {
    private ResultFragment frgListResult;
    private String title;
    private SearchView searchView;
    private Result[] listResult;
    private List<SearchHistory> listSearch;
    private SearchHistory search;
    private int iTotal;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_result);

            // almacena busqueda
            title = getIntent().getExtras().getString("searchText");
            addHistorySearch();

            // setea titulo de busqueda
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // obtiene busqueda
            searchInSite(title, 0);
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
        searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint(this.getResources().getString(R.string.txt_searchview));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.isEmpty()) {
                    searchView.clearFocus();
                    moveToNext(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery(title, false);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setListInFragment() {
        try {
            // setea fragmento de listado
            Fragment fragment = new ResultFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frmListResult, fragment);
            transaction.commit();

            // setea listener para saber cuando hace click
            frgListResult = (ResultFragment) fragment;

            frgListResult.setData(listResult);
            frgListResult.setListener(this);
        }
        catch(Exception ex) {
           throw ex;
        }
    }


    /***************************************
     *            DATA METHODS
     ***************************************/
    private void getHistorySearch() {
        try {
            listSearch = SearchHistoryDAL.getInstance(this).GetAll();
            listSearch = new ArrayList<>(listSearch);
        }
        catch(Exception e) {
            throw e;
        }
    }

    private void addHistorySearch() {
        try {
            getHistorySearch();

            search = new SearchHistory();
            search.setSearchQuery(title);

            // busca busqueda repetida
            for(SearchHistory s : listSearch) {
                // si es igual
                if(s.getSearchQuery().toUpperCase().equals(title.toUpperCase())) {
                    search = s;
                    listSearch.remove(s);
                    listSearch.add(0, s);
                    return;
                }
            }

            listSearch.add(0,search);
            if(listSearch.size() > 10) {
                listSearch.remove(10);
            }
        }
        catch(Exception e) {
            throw e;
        }
    }

    private void addHistorySearchItem(Result item) {
        try {
            SearchHistoryItem searchItem = new SearchHistoryItem();
            searchItem.setId(item.getId());
            searchItem.setPrice(NumberUtilities.setFormat(item.getPrice().toString()));
            searchItem.setTitle(item.getTitle());
            searchItem.setURLImage(item.getThumbnail());
            search.getItems().add(0, searchItem);

            // elemento repetido
            for (int i = 1; i < search.getItems().size(); i++) {
                if (search.getItems().get(i).getId().equals(item.getId())) {
                    search.getItems().remove(i);
                }
            }

            // limite de 10
            if (search.getItems().size() > 10) {
                search.getItems().remove(10);
            }

            // guarda actualizacion
            SearchHistoryDAL.getInstance(this).Save(listSearch);
        }
        catch(Exception e) {
            throw e;
        }
    }

    private void setResultList(Result[] list) {
        listResult = list;

        try {
            // si ya se encuentra creado el fragment, update
            if (frgListResult != null) {
                setDataUpdaterFragment();
            } else { // sino crear
                setListInFragment();
            }
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    private void setDataUpdaterFragment() {
        frgListResult.setUpdateData(listResult);
    }


    /***************************************
     *          WEB SERVICE METHODS
     ***************************************/
    private void searchInSite(String query, int offset) {
        RestCallsWS rest = new RestCallsWS(getBaseContext());
        try {
            rest.search(query, offset, new ResponseWSListenerWithPaging<Result[]>() {
                @Override
                public void onStart() { }

                @Override
                public void onSuccess(Result[] result, int offset, int total) {
                    iTotal = total;
                    setResultList(result);
                }

                @Override
                public void onFailure(Exception e) {
                    undefinedFailedEvent(e.getMessage());
                }
            });
        } catch(ConnectionFailedException e) {
            withoutConnectionEvent();
        } catch (Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }


    /***************************************
     *          EVENT METHODS
     ***************************************/
    @Override
    public void onItemClick(Result item) {
        try {
            if (item != null) {
                addHistorySearchItem(item);

                if(NetworkUtil.isConnected(this)) {
                    ActivitiesUtil.moveToWithParameters(this, ItemActivity.class, false, "itemID", item.getId(), "title", title);
                }
                else {
                    withoutConnectionEvent();
                }
            }
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    @Override
    public void onLoadMoreData(int offset) {
        try {
            // si todavia hay elementos que mostrar
            if(offset<iTotal) searchInSite(title, offset);
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    public void moveToNext(String query) {
        if(NetworkUtil.isConnected(this)) {
            ActivitiesUtil.moveToWithParameters(this, ResultActivity.class, false, "searchText", query);
        }
        else {
            withoutConnectionEvent();
        }
    }

}
