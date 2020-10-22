package com.androidnik.itaydi_nav;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabsPageAdapter extends FragmentStatePagerAdapter {


    String[] tabarray = new String[]{"One", "Two", "Three"};

    Integer tabnumber = 3;


    public TabsPageAdapter(FragmentManager fm) {

        super(fm);

    }


    @Override

    public CharSequence getPageTitle(int position) {

        return tabarray[position];

    }


    @Override

    public Fragment getItem(int position) {


        switch (position) {

            case 0:

                FirstFragment one1 = new FirstFragment();

                return one1;

            case 1:

                SecondFragment two2 = new SecondFragment();

                return two2;

            case 2:

                ThirdFragment three3 = new ThirdFragment();

                return three3;

           /* case 3:

                four four4 = new four();

                return four4;

            case 4:

                five five5 = new five();

                return five5;

            case 5:

                six six6 = new six();

                return six6;*/
        }

        return null;

    }



    @Override

    public int getCount() {

        return tabnumber;

    }

}