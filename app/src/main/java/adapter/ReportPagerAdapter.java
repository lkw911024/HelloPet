package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import layout.Disappear;
import layout.Protect;

/**
 * Created by ael on 2017. 10. 13..
 */

public class ReportPagerAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 2;

    public ReportPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return Disappear.newInstance();
            case 1:
                return Protect.newInstance();
            default:
                return null;
        }
    }
    /*
        각각의 프레그먼트가 선택 됐을때 프레그먼트의 객체를 생성하여 전달
     */

    @Override
    public int getCount()
    {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position)
        {
            case 0:
                return "실종";
            case 1:
                return "보호/목격";
            default:
                return null;
        }
    }
    /*
        각 프레그먼트의 타이틀 설정
     */
}
