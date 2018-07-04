package ar.com.mercadolibre.challenge.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ar.com.mercadolibre.challenge.R;
import ar.com.mercadolibre.challenge.adapters.SliderImageAdapter;
import ar.com.mercadolibre.challenge.dto.Item;
import ar.com.mercadolibre.challenge.dto.Question;
import ar.com.mercadolibre.challenge.dto.User;
import ar.com.mercadolibre.challenge.exceptions.ConnectionFailedException;
import ar.com.mercadolibre.challenge.exceptions.UndefinedException;
import ar.com.mercadolibre.challenge.fragments.DetailFragment;
import ar.com.mercadolibre.challenge.fragments.QuestionFragment;
import ar.com.mercadolibre.challenge.listeners.ResponseWSListener;
import ar.com.mercadolibre.challenge.webservices.RestCallsWS;

public class ItemActivity extends MCActivity {
    private String itemID = "";
    private Item product;
    private Question[] listQuestions;
    private User seller;
    private TabLayout tabLayout;
    private ViewPager viewPagerImageSlider;
    private SliderImageAdapter sliderImageAdapter;
    private QuestionFragment frgListQuestion;
    private DetailFragment frgDetailItem;

    /***************************************
     *            GUI METHODS
     ***************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        try {
            // setea location

            // setea titulo de producto
            String title = getIntent().getExtras().getString("title");

            // obtiene item id
            itemID = getIntent().getExtras().getString("itemID");

            // fragments
            createFragments();

            // obtiene item
            getItem(itemID);

            // setea titulo
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //tabs
            setTabs();

        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menuBuy:
                Toast.makeText(this, R.string.txt_buy_button, Toast.LENGTH_LONG).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTabs() {
        try {
            // setea tabs
            tabLayout = findViewById(R.id.tabs);
            tabLayout.addTab(tabLayout.newTab().setText(R.string.txt_tab_description));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.txt_tab_question));

            // setea listener para detectar click
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()) {
                        case 0:
                            // descripcion
                            setDetailInFragment();
                            break;
                        case 1:
                            // preguntas y respuestas
                            setQuestionInFragment();
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) { }

                @Override
                public void onTabReselected(TabLayout.Tab tab) { }
            });

            // setea tab default
            setDetailInFragment();
        }
        catch(Exception e) {
            throw e;
        }
    }

    private void createFragments() {
        try {
            // setea fragmento de descripion
            Fragment fragment = new DetailFragment();
            frgDetailItem = (DetailFragment) fragment;

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frmListItem, fragment);
            transaction.commit();

            // setea fragment de preguntas
            Fragment fragment1 = new QuestionFragment();
            frgListQuestion = (QuestionFragment) fragment1;
        }
        catch(Exception e) {
            throw e;
        }
    }

    private void setDetailInFragment() {
        try {
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.frmListItem, frgDetailItem);
            t.commit();

            frgDetailItem.setData(product, seller, getResources());
        }
        catch(Exception e) {
            throw e;
        }
    }

    private void setQuestionInFragment() {
        try {
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.frmListItem, frgListQuestion);
            frgListQuestion.setData(listQuestions);
            t.commit();
        }
        catch(Exception e) { throw e; }
    }

    private void configureSliderPagerAdapter() {
        try {
            viewPagerImageSlider = findViewById(R.id.viewImagePaper);
            sliderImageAdapter = new SliderImageAdapter(this, product.getPictures());
            viewPagerImageSlider.setAdapter(sliderImageAdapter);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void setItemInfo() {
        try {
            configureSliderPagerAdapter();
            frgDetailItem.setData(product, seller, getResources());
            //distanceToProduct();
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    private void setDescriptionInfo() {
        try {
            frgDetailItem.setData(product, seller, getResources());
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    private void setQuestionsInfo() {
        try {
            if(listQuestions!=null && listQuestions.length>0) {
                if(tabLayout.getTabCount()==1) {
                    tabLayout.addTab(tabLayout.newTab().setText(R.string.txt_tab_question));
                }
            }
            else {
                tabLayout.removeTabAt(1);
            }
            frgListQuestion.setData(listQuestions);
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    private void setUserInfo() {
        try {
            frgDetailItem.setData(product, seller, getResources());
        }
        catch(Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }


    /***************************************
     *          WEB SERVICE METHODS
     ***************************************/
    private void getItem(String itemID) {
        RestCallsWS rest = new RestCallsWS(getBaseContext());
        try {
            rest.getItem(itemID, new ResponseWSListener<Item>() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(Item item) {
                    product = item;
                    setItemInfo();
                    searchMoreData();
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

    private void searchMoreData() {
        try {
            getUser();
            getDescriptionItem();
            getQuestionsItem();
        } catch (Exception e) {
            undefinedFailedEvent(e.getMessage());
        }
    }

    private void getDescriptionItem() {
        RestCallsWS rest = new RestCallsWS(getBaseContext());
        try {
            rest.getDescriptionItem(product.getId(), new ResponseWSListener<String>() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(String desc) {
                    product.setDescription(desc + "");
                    setDescriptionInfo();
                }

                @Override
                public void onFailure(Exception e) {
                    product.setDescription("");
                }
            });
        } catch(ConnectionFailedException e) {
            withoutConnectionEvent();
        } catch (Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    private void getQuestionsItem() {
        RestCallsWS rest = new RestCallsWS(getBaseContext());
        try {
            rest.getQuestionsItem(product.getId(), new ResponseWSListener<Question[]>() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(Question[] questions) {
                    listQuestions = questions;
                    setQuestionsInfo();
                }

                @Override
                public void onFailure(Exception e) {
                    listQuestions = new Question[0];
                    setQuestionsInfo();
                }
            });
        } catch(ConnectionFailedException e) {
            withoutConnectionEvent();
        } catch (Exception e) {
            undefinedFailedEvent(new UndefinedException(this).getMessage());
        }
    }

    private void getUser() {
        RestCallsWS rest = new RestCallsWS(getBaseContext());
        try {
            rest.getUser(String.valueOf(product.getSellerId()), new ResponseWSListener<User>() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(User user) {
                    seller = user;
                    setUserInfo();
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
     *          LOCATION METHODS
     ***************************************/

    /*private float distanceToProduct() {
        try {

             Location locationB = new Location("Product");
            locationB.setLatitude(product.getGeolocation().getLatitude());
            locationB.setLongitude(product.getGeolocation().getLongitude());

            //float distance = locationActual.distanceTo(locationB);
            return distance;
        }
        catch(Exception ignored) {}
        return -1;
    }*/
}
