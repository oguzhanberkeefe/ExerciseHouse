package com.example.exercisehouse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercisehouse.adapters.MainAdapter;
import com.example.exercisehouse.adapters.MainBean;
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
    private String[] str = {"En Çok Satılanlar", "Online Eğitim","Beslenme Programı","anrenman programı"};



    /**
     * 需要定位的地方，从小到大排列，需要和tab对应起来，长度一样
     */

    private int[] str1 = {0, 8, 27, 38, 59  };
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
                Toast.makeText(MainActivity.this, "backarrow", Toast.LENGTH_SHORT).show();
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

        manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        adapter = new MainAdapter(list);
        recyclerView.setAdapter(adapter);


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


        //En Çok Satılanlar
        list.add(new MainBean("Deneme","Online Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme2","Beslenme",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme3","Medikal Egezersiz",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme4","bire bir ders",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme5","Supplement",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme6","Supplement",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme7","Supplement",R.drawable.ozanardakoc));



        //Sıcaklar
        list.add(new MainBean("Deneme8","Online Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Online Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Online Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));



        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));

        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme8","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme9","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme10","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme11","Uzaktan Eğitim",R.drawable.ozanardakoc));
        list.add(new MainBean("Deneme12","Uzaktan Eğitim",R.drawable.ozanardakoc));



    }


}