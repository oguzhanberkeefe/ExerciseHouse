package com.example.exercisehouse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.exercisehouse.adapters.MainAdapter;
import com.example.exercisehouse.adapters.MainBean;
import com.example.exercisehouse.urunler.AntrenmanDetay;
import com.example.exercisehouse.urunler.BeslenmeDetay;
import com.example.exercisehouse.urunler.BireBirDetay;
import com.example.exercisehouse.urunler.EvdeEgzersizDetay;
import com.example.exercisehouse.urunler.MedikalDetay;
import com.example.exercisehouse.urunler.OnlineDetay;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView backarrow,menu;
    TextView title;


    private List<MainBean> list;
    private MainAdapter adapter;
    private RecyclerView recyclerView;

    private LinearLayoutManager manager;
    private TabLayout tabLayout;

    //tab的标签
    private String[] str = {"Hepsi", "Online Eğitim Programı","Beslenme Programı","Antrenman Programı","Medikal Egzersiz Programı","Evde Egzersiz Programı","Bire Bir Özel Ders"};



    /**
     * 需要定位的地方，从小到大排列，需要和tab对应起来，长度一样
     */

    private int[] str1 = {0 , 6 , 7 , 8 , 9 , 10 , 11  };
    private boolean isScrolled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backarrow = findViewById(R.id.backarrow);
        menu = findViewById(R.id.menu);
        title = findViewById(R.id.toolbar_title);
        initData();
        init();
        initTab();

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    private void initTab() {
        for (int i = 0; i < str.length; i++) {
            //插入tab标签
            tabLayout.addTab(tabLayout.newTab().setText(str[i]));
        }
        //标签页可以滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (!isScrolled) {
                    //滑动时不能点击,
                    //第一个参数是指定的位置，锚点
                    // 第二个参数表示 Item 移动到第一项后跟 RecyclerView 上边界或下边界之间的距离（默认是 0）
                    manager.scrollToPositionWithOffset(str1[pos], 0);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }




    private void init() {
        recyclerView = findViewById(R.id.mian_recy);
        tabLayout = findViewById(R.id.main_tab);

        manager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(manager);
        adapter = new MainAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {

                switch (position)
                {

                    case 0:
                        startActivity(new Intent(MainActivity.this, OnlineDetay.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, BeslenmeDetay.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, MedikalDetay.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, AntrenmanDetay.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, EvdeEgzersizDetay.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, BireBirDetay.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, OnlineDetay.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, BeslenmeDetay.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, AntrenmanDetay.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, MedikalDetay.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, EvdeEgzersizDetay.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, BireBirDetay.class));
                        break;
                    default:
                        break;

                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，12都是滑动
                if (newState == 0) {
                    isScrolled = false;
                } else {
                    isScrolled = true;
                }
                setMsg("isScrolled" + isScrolled + "--newState=" + newState);
            }

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法
                if (isScrolled) {
                    int top = manager.findFirstVisibleItemPosition();
                    int bottom = manager.findLastVisibleItemPosition();

                    int pos = 0;
                    if (bottom == list.size() - 1) {
                        //先判断滑到底部，tab定位到最后一个
                        pos = str1.length - 1;
                    } else if (top == str1[str1.length - 1]) {
                        //如果top等于指定的位置，对应到tab即可，
                        pos = str1[str1.length - 1];
                    } else {
                        //循环遍历，需要比较i+1的位置，所以循环长度要减1，
                        //  如果 i<top<i+1,  那么tab应该定位到i位置的字符，不管是向上还是向下滑动
                        for (int i = 0; i < str1.length - 1; i++) {
                            if (top == str1[i]) {
                                pos = i;
                                break;
                            } else if (top > str1[i] && top < str1[i + 1]) {
                                pos = i;
                                break;
                            }
                        }
                    }

                    //设置tab滑动到第pos个
                    tabLayout.setScrollPosition(pos, 0f, true);
                }

            }
        });

    }

    public static void setMsg(String str) {
        Log.i("tab", str);
    }

    private void initData() {
        list = new ArrayList<>();


        //Hepsi
        list.add(new MainBean("Online Eğitim Programı","Online Eğitim Programı",R.drawable.online));
        list.add(new MainBean("Beslenme Programı","Beslenme Programı",R.drawable.beslenme));
        list.add(new MainBean("Medikal Egezersiz","Medikal Egezersiz Programı",R.drawable.medikal));
        list.add(new MainBean("Antrenman Programı","Antrenman Programı",R.drawable.antrenman));
        list.add(new MainBean("Evde Egzersiz Programı","Evde Egzersiz Programı",R.drawable.evde));
        list.add(new MainBean("Bire Bir Özel Ders","Bire Bir Özel Ders",R.drawable.ozanardakoc));



        // Online Eğitim Programı
        list.add(new MainBean("Online Eğitim Programı","Online Eğitim Programı",R.drawable.online));

        // Beslenme Programı
        list.add(new MainBean("Beslenme Programı","Beslenme Programı",R.drawable.beslenme));

        // Antrenman Programı
        list.add(new MainBean("Antrenman Programı","Antrenman Programı",R.drawable.antrenman));

        // Medikal Egzersiz Programı
        list.add(new MainBean("Medikal Egzersiz Programı","Medikal Egzersiz Programı",R.drawable.medikal));

        // Evde Egzersiz Programı
        list.add(new MainBean("Evde Egzersiz Programı","Evde Egzersiz Programı",R.drawable.evde));

        // Bire Bir Özel Ders
        list.add(new MainBean("Bire Bir Özel Ders","Bire Bir Özel Ders",R.drawable.ozanardakoc));


    }


}