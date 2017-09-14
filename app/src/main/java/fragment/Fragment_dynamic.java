package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lakalaka.test1.R;

/**
 * Created by lakalaka on 2017/9/13/0013.
 */

public class Fragment_dynamic extends Fragment{
    public Fragment_dynamic(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dynamic,container,false);
        return view;
    }
}
