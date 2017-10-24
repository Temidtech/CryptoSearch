package com.swiftsynq.charis.cryptosearch.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.gson.internal.LinkedTreeMap;
import com.squareup.otto.Subscribe;
import com.swiftsynq.charis.cryptosearch.Adapters.CurrencyExAdapter;
import com.swiftsynq.charis.cryptosearch.Events.CoinsServerEvent;
import com.swiftsynq.charis.cryptosearch.Events.CoinsErrorEvent;
import com.swiftsynq.charis.cryptosearch.Events.ExRateErrorEvent;
import com.swiftsynq.charis.cryptosearch.Infrastructures.BusProvider;
import com.swiftsynq.charis.cryptosearch.Infrastructures.Communicator;
import com.swiftsynq.charis.cryptosearch.Models.Conversion;
import com.swiftsynq.charis.cryptosearch.Models.CryptoCoins;
import com.swiftsynq.charis.cryptosearch.R;
import com.swiftsynq.charis.cryptosearch.Util.Constant;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private LayoutInflater inflater;
    private CurrencyExAdapter adapter;
    private List<Conversion> currencyList;
    private Conversion conversion;
    List<CryptoCoins> cryptoCoinsList;
    String CoinSelected;
    String WorldCurrSelected;
    private View vi;
    /*@BindView(R.id.toolbar)
    Toolbar toolbar;*/
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.homelayout)
    LinearLayout homelayout;
    @BindView(R.id.avilyt)
    LinearLayout avilyt;
    @BindView(R.id.addlyt)
    LinearLayout addlyt;
    @BindView(R.id.addiconimg)
    ImageView addiconimg;
    @BindView(R.id.Bthumbnail)
    ImageView Bthumbnail;
    @BindView(R.id.BcoinName)
    TextView BcoinName;
    @BindView(R.id.BcoinFullName)
    TextView BcoinFullName;
    @BindView(R.id.EThumbnail)
    ImageView EThumbnail;
    @BindView(R.id.EcoinSymbol)
    TextView EcoinSymbol;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.nointernetlyt)
    LinearLayout nointernetlyt;
    AVLoadingIndicatorView dialogavi;
    LinearLayout dialogavilyt;
    @BindView(R.id.EcoinFullName)
    TextView EcoinFullName;
    Button currencyratebtn;
    @BindView(R.id.tryagainbtn)
    Button tryagainbtn;
    @BindView(R.id.WCitemcard)
    CardView WCitemcard;
    @BindView(R.id.BTitemcard)
    CardView BTitemcard;
    private MaterialStyledDialog materialStyledDialog;
    private AlertDialog.Builder alertBox;
    private Communicator communicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instatiate Communicator
        communicator = new Communicator();

        //Inflater
        inflater = (LayoutInflater)getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Dependency Injection for View
        ButterKnife.bind(this);
        setUpUI();

        //Fetch Coins from server
        GetCoins();
    }
    private void setUpUI()
    {
        alertBox=new AlertDialog.Builder(MainActivity.this);
        //ToolBar Setup
       /* setSupportActionBar(toolbar);*/
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        //setup RecyclerView
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        currencyList = new ArrayList<>();
        adapter = new CurrencyExAdapter(this, currencyList,addlyt,recyclerView);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialStyledDialog= new MaterialStyledDialog.Builder(MainActivity.this)
                        .setDescription("Currency Exchange Rate")
                        .setStyle(Style.HEADER_WITH_ICON)
                        .setHeaderColor(R.color.colorPrimaryDark)
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setCustomView(dialogview())
                        .setTitle(R.string.app_name)
                        .withDialogAnimation(true)
                        .show();
            }
        });
        WCitemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialStyledDialog= new MaterialStyledDialog.Builder(MainActivity.this)
                        .setDescription("Currency Exchange Rate")
                        .setStyle(Style.HEADER_WITH_ICON)
                        .setHeaderColor(R.color.colorPrimaryDark)
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setTitle(R.string.app_name)
                        .setCustomView(dialogview())
                        .withDialogAnimation(true)
                        .show();
            }
        });
        BTitemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialStyledDialog= new MaterialStyledDialog.Builder(MainActivity.this)
                        .setDescription("Currency Exchange Rate")
                        .setStyle(Style.HEADER_WITH_ICON)
                        .setHeaderColor(R.color.colorPrimaryDark)
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setCustomView(dialogview())
                        .setTitle(R.string.app_name)
                        .withDialogAnimation(true)
                        .show();
            }
        });
        tryagainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nointernetlyt.setVisibility(View.GONE);
                GetCoins();
            }
        });
        addiconimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialStyledDialog= new MaterialStyledDialog.Builder(MainActivity.this)
                        .setDescription("Currency Exchange Rate")
                        .setStyle(Style.HEADER_WITH_ICON)
                        .setHeaderColor(R.color.colorPrimaryDark)
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setCustomView(dialogview())
                        .setTitle(R.string.app_name)
                        .withDialogAnimation(true)
                        .show();
            }
        });
    }
    private void prepareConverion(Conversion conversion) {
        recyclerView.setVisibility(View.VISIBLE);
        homelayout.setVisibility(View.GONE);
        currencyList.add(conversion);
        adapter.notifyDataSetChanged();
    }
    private void GetCoins(){
        avilyt.setVisibility(View.VISIBLE);
        avi.smoothToShow();
        communicator.CoinsList();
    }
    private void FindExRate(String fsym,String tsym){
        currencyratebtn.setVisibility(View.GONE);
        dialogavilyt.setVisibility(View.VISIBLE);
        dialogavi.smoothToShow();
        communicator.FindExRate(fsym,tsym);;
    }
    private View dialogview()
    {
        vi=inflater.inflate(R.layout.customdialog, null);

         currencyratebtn=(Button)vi.findViewById(R.id.currencyratebtn);
         dialogavi=(AVLoadingIndicatorView)vi.findViewById(R.id.dialogavi);
        dialogavilyt=(LinearLayout) vi.findViewById(R.id.dialogavilyt);
        final Spinner cryptospinner=(Spinner)vi.findViewById(R.id.cryptospinner) ;
        final Spinner worldcurrspinner=(Spinner)vi.findViewById(R.id.currencyspinner) ;
        ArrayAdapter<CharSequence>crptoadapter=ArrayAdapter.createFromResource(this,R.array.CryptoCurrencies,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence>worldcurradapter=ArrayAdapter.createFromResource(this,R.array.worldcurrencies,android.R.layout.simple_spinner_item);
        crptoadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        worldcurradapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cryptospinner.setAdapter(crptoadapter);
        worldcurrspinner.setAdapter(worldcurradapter);
        currencyratebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoinSelected=cryptospinner.getSelectedItem().toString();
                WorldCurrSelected=worldcurrspinner.getSelectedItem().toString();
                FindExRate(cryptospinner.getSelectedItem().toString(),worldcurrspinner.getSelectedItem().toString());
            }
        });
        return vi;
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    @Override
    public void onResume(){
        super.onResume();
        BusProvider.getInstance().register(MainActivity.this);
    }

    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(MainActivity.this);
    }
    @Subscribe
    public void onServerEvent(CoinsServerEvent serverEvent)
    {

        LinkedTreeMap linkedTreeMap = (LinkedTreeMap)serverEvent.getServerResponse().getData();
        LinkedTreeMap BTCResult = null;
        LinkedTreeMap ETHResult=null;
        try{
             BTCResult = (LinkedTreeMap) linkedTreeMap.get("BTC");
             ETHResult = (LinkedTreeMap) linkedTreeMap.get("ETH");
        }catch (Exception e)
        {
            Log.d("LinkedTreeMap Error",e.getMessage());
        }

        if(BTCResult!=null&&ETHResult!=null)
        {
            avilyt.setVisibility(View.GONE);
            homelayout.setVisibility(View.VISIBLE);
            avi.smoothToHide();
            fab.setVisibility(View.VISIBLE);
            cryptoCoinsList=new ArrayList<>();
            BcoinFullName.setText(BTCResult.get("FullName").toString());
            BcoinName.setText(BTCResult.get("Symbol").toString());
            EcoinFullName.setText(ETHResult.get("FullName").toString());
            EcoinSymbol.setText(ETHResult.get("Symbol").toString());
            cryptoCoinsList.add(new CryptoCoins(BTCResult.get("Symbol").toString(),BTCResult.get("ImageUrl").toString(),BTCResult.get("FullName").toString()));
            cryptoCoinsList.add(new CryptoCoins(ETHResult.get("Symbol").toString(),ETHResult.get("ImageUrl").toString(),ETHResult.get("FullName").toString()));
            Glide.with(getBaseContext()).load(Constant.IMAGE_BASE_URL+cryptoCoinsList.get(1).getImageUrl().toString()).into(EThumbnail);
            Glide.with(getBaseContext()).load(Constant.IMAGE_BASE_URL+cryptoCoinsList.get(0).getImageUrl().toString()).into(Bthumbnail);
        }
        else
        {


        }

    }
    @Subscribe
    public void onServerEvent(Object serverEvent)
    {
        try
        {
            LinkedTreeMap linkedTreeMap = (LinkedTreeMap)serverEvent;
            switch ( CoinSelected)
            {
                case "BTC":
                    conversion = new Conversion(cryptoCoinsList.get(0).getImageUrl(),WorldCurrSelected,linkedTreeMap.get(WorldCurrSelected).toString(), CoinSelected);
                    break;
                case "ETH":
                    conversion = new Conversion(cryptoCoinsList.get(1).getImageUrl(), WorldCurrSelected, linkedTreeMap.get(WorldCurrSelected).toString(), CoinSelected);
                    break;
                default:

            }
            prepareConverion(conversion);
            materialStyledDialog.dismiss();

        }catch (Exception e)
        {

        }

    }


    @Subscribe
    public void onErrorEvent(CoinsErrorEvent coinsErrorEvent){
        if(coinsErrorEvent.getErrorCode()==4)
        {
            avi.setVisibility(View.GONE);
            nointernetlyt.setVisibility(View.VISIBLE);
            avi.smoothToHide();
        }

       /* Toast.makeText(this,""+coinsErrorEvent.getErrorMsg(),Toast.LENGTH_LONG).show();*/
    }
    @Subscribe
    public void onExRateErrorEvent(ExRateErrorEvent exRateErrorEvent){
        if(exRateErrorEvent.getErrorCode()==2)
        {
            currencyratebtn.setVisibility(View.VISIBLE);
            dialogavilyt.setVisibility(View.GONE);
            Toast.makeText(this, exRateErrorEvent.getErrorMsg(),Toast.LENGTH_LONG).show();
        }
       /* Toast.makeText(this,""+coinsErrorEvent.getErrorMsg(),Toast.LENGTH_LONG).show();*/
    }

}
