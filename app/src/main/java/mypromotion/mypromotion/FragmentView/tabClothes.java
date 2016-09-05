package mypromotion.mypromotion.FragmentView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mypromotion.mypromotion.R;

/**
 * Created by Administrator on 9/1/2016.
 */
public class tabClothes extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fm_clothes, container, false);
    }
}
