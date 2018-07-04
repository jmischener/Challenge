package ar.com.mercadolibre.challenge.webservices;

import android.content.Context;
import android.content.ContextWrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.dto.Item;
import ar.com.mercadolibre.challenge.dto.Question;
import ar.com.mercadolibre.challenge.dto.Result;
import ar.com.mercadolibre.challenge.dto.User;
import ar.com.mercadolibre.challenge.exceptions.ConnectionFailedException;
import ar.com.mercadolibre.challenge.listeners.ResponseWSListener;
import ar.com.mercadolibre.challenge.listeners.ResponseWSListenerWithPaging;
import ar.com.mercadolibre.challenge.adapters.DoubleTypeAdapter;
import ar.com.mercadolibre.challenge.adapters.IntTypeAdapter;
import ar.com.mercadolibre.challenge.utilities.NetworkUtil;
import cz.msebera.android.httpclient.Header;

public class RestCallsWS extends ContextWrapper {

    public RestCallsWS(Context base) { super(base); }

    public void search(final String searchText, final int offset, final ResponseWSListenerWithPaging<Result[]> listener) throws Exception {
        try {
            // revisa conexion
            if (!NetworkUtil.isConnected(this)) throw new ConnectionFailedException(this.getApplicationContext());

            RequestParams params = new RequestParams("q", searchText, "offset", offset);
            RestClientWS.get(getBaseContext(),  URLClientWS.SEARCH_URL, params, new JsonHttpResponseHandler() {

                public void onStart() {
                    listener.onStart();
                }

                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String json = response.getString("results");
                        Gson g = new GsonBuilder()
                                .registerTypeAdapter(int.class, new IntTypeAdapter())
                                .registerTypeAdapter(Integer.class, new IntTypeAdapter())
                                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                                .create();
                        Result[] ret = g.fromJson(json, Result[].class);
                        listener.onSuccess(ret, response.getJSONObject("paging").getInt("offset"), response.getJSONObject("paging").getInt("total"));
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    try {
                        String a = responseString;
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                public void onRetry(int retryNo) {
                    int countRetry = getBaseContext().getResources().getInteger(R.integer.count_retry_connection);
                    if (retryNo == countRetry) {
                        listener.onFailure(new Exception("timeout"));
                    }
                }
            });
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    public void getItem(final String itemID, final ResponseWSListener<Item> listener) throws Exception {
        try {
            // revisa conexion
            if (!NetworkUtil.isConnected(this)) throw new ConnectionFailedException(this.getApplicationContext());

            RestClientWS.get(getBaseContext(),  URLClientWS.ITEM_URL + itemID, null, new JsonHttpResponseHandler() {

                public void onStart() {
                    listener.onStart();
                }

                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String json = response.toString();
                        Gson g = new GsonBuilder()
                                .registerTypeAdapter(int.class, new IntTypeAdapter())
                                .registerTypeAdapter(Integer.class, new IntTypeAdapter())
                                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                                .create();

                        Item ret = g.fromJson(json, Item.class);
                        listener.onSuccess(ret);
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    try {
                        String a = responseString;
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                public void onRetry(int retryNo) {
                    int countRetry = getBaseContext().getResources().getInteger(R.integer.count_retry_connection);
                    if (retryNo == countRetry) {
                        listener.onFailure(new Exception("timeout"));
                    }
                }
            });
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    public void getUser(final String userID, final ResponseWSListener<User> listener) throws Exception {
        try {
            // revisa conexion
            if (!NetworkUtil.isConnected(this)) throw new ConnectionFailedException(this.getApplicationContext());

            RestClientWS.get(getBaseContext(),  URLClientWS.USER_URL + userID, null, new JsonHttpResponseHandler() {

                public void onStart() {
                    listener.onStart();
                }

                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String json = response.toString();
                        Gson g = new GsonBuilder()
                                .registerTypeAdapter(int.class, new IntTypeAdapter())
                                .registerTypeAdapter(Integer.class, new IntTypeAdapter())
                                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                                .create();
                        User ret = g.fromJson(json, User.class);
                        listener.onSuccess(ret);
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    try {
                        String a = responseString;
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                public void onRetry(int retryNo) {
                    int countRetry = getBaseContext().getResources().getInteger(R.integer.count_retry_connection);
                    if (retryNo == countRetry) {
                        listener.onFailure(new Exception("timeout"));
                    }
                }
            });
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    public void getDescriptionItem(final String itemID, final ResponseWSListener<String> listener) throws Exception {
        try {
            // revisa conexion
            if (!NetworkUtil.isConnected(this)) throw new ConnectionFailedException(this.getApplicationContext());

            RestClientWS.get(getBaseContext(),  URLClientWS.ITEM_URL + itemID + "/description", null, new JsonHttpResponseHandler() {

                public void onStart() {
                    listener.onStart();
                }

                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String ret = response.getString("plain_text");
                        listener.onSuccess(ret);
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    try {
                        String a = responseString;
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                public void onRetry(int retryNo) {
                    int countRetry = getBaseContext().getResources().getInteger(R.integer.count_retry_connection);
                    if (retryNo == countRetry) {
                        listener.onFailure(new Exception("timeout"));
                    }
                }
            });
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    public void getQuestionsItem(final String itemID, final ResponseWSListener<Question[]> listener) throws Exception {
        try {
            // revisa conexion
            if (!NetworkUtil.isConnected(this)) throw new ConnectionFailedException(this.getApplicationContext());

            RequestParams params = new RequestParams("item", itemID);
            RestClientWS.get(getBaseContext(),  URLClientWS.QUESTIONS_URL, params, new JsonHttpResponseHandler() {

                public void onStart() {
                    listener.onStart();
                }

                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    switch(statusCode) {
                        case 500:
                            listener.onFailure(new Exception("500"));
                            break;
                        default:
                            listener.onFailure(new Exception(throwable.getMessage()));
                            break;
                    }
                }

                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        String json = response.getString("questions");
                        Gson g = new GsonBuilder()
                                .registerTypeAdapter(int.class, new IntTypeAdapter())
                                .registerTypeAdapter(Integer.class, new IntTypeAdapter())
                                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                                .create();
                        Question[] ret = g.fromJson(json, Question[].class);
                        listener.onSuccess(ret);
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    try {
                        String a = responseString;
                    } catch (Exception e) {
                        listener.onFailure(e);
                    }
                }

                public void onRetry(int retryNo) {
                    int countRetry = getBaseContext().getResources().getInteger(R.integer.count_retry_connection);
                    if (retryNo == countRetry) {
                        listener.onFailure(new Exception("timeout"));
                    }
                }
            });
        }
        catch(Exception ex) {
            throw ex;
        }
    }

}
