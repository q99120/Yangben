package com.mihua.yangben.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mihua.yangben.R;
import com.mihua.yangben.ui.adapter.TestArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SolventSetFm extends Fragment {
    String TAG = "SolventSetFm";
    @BindView(R.id.action_bar_spinner)
    Spinner spinner;
    @BindView(R.id.view_funan)
    View view_funan;
    @BindView(R.id.view_kqs)
    View view_kqs;
    @BindView(R.id.view_shourou)
    View view_shourou;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solvent, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView: " + "2222");
        initClick();
        return view;
    }

    private void initClick() {
        String[] curs = new String[]{"呋喃四项", "孔雀石绿", "瘦肉精类"};
        TestArrayAdapter adapter = new TestArrayAdapter(getActivity(), curs);

        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    view_funan.setVisibility(View.VISIBLE);
                    view_shourou.setVisibility(View.INVISIBLE);
                    view_kqs.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "onItemClick: " + "付楠楠");
                } else if (i == 1) {
                    view_funan.setVisibility(View.INVISIBLE);
                    view_shourou.setVisibility(View.INVISIBLE);
                    view_kqs.setVisibility(View.VISIBLE);
                    Log.e(TAG, "onItemClick: " + "孔雀石");
                } else if (i == 2) {
                    view_funan.setVisibility(View.INVISIBLE);
                    view_shourou.setVisibility(View.VISIBLE);
                    view_kqs.setVisibility(View.INVISIBLE);
                    Log.e(TAG, "onItemClick: " + "瘦肉精");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: " + "2222");
        super.onAttach(context);
    }


    @Override
    public void onResume() {
        Log.e(TAG, "onResume: " + "1111");
        super.onResume();
    }
}
