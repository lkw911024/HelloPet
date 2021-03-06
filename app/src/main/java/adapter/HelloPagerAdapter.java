package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import layout.Home;
import layout.Report;
import layout.Shelter;

/**
 * Created by ael on 2017. 10. 10..
 */

public class HelloPagerAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUMBER = 3;

    public HelloPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return Home.newInstance();
            case 1:
                return Report.newInstance();
            case 2:
                return Shelter.newInstance();
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
                return "홈";
            case 1:
                return "실종/보호";
            case 2:
                return "보호소";
            default:
                return null;
        }
    }
    /*
        각 프레그먼트의 타이틀 설정
     */
}
