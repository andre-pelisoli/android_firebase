package br.com.pelisoli.android_firebase.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.adapter.ViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    ViewPagerAdapter mViewPagerAdapter;

    private static final int NUMBER_TABS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), NUMBER_TABS);
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.pager_title_shopping_lists));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.pager_title_meals));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
