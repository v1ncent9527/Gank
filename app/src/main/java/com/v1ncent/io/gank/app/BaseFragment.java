package com.v1ncent.io.gank.app;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.v1ncent.io.gank.utils.toast.Toasty;

/**
 * Created by v1ncent on 2017/4/11.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private Toast toast;

    @Override
    public void onClick(View view) {
        onClickListener(view);
    }

    public abstract void onClickListener(View v);

    /**
     * toast INFO
     *
     * @param info
     */
    public void showInfo(String info) {
        if (toast != null) {
            toast.show();
        } else {
            toast = Toasty.info(getActivity().getApplicationContext(), info, Toast.LENGTH_SHORT, true);
            toast.show();
        }
    }

    /**
     * toast SUCCESS
     *
     * @param success
     */
    public void showSuccess(String success) {
        if (toast != null) {
            toast.show();
        } else {
            toast = Toasty.success(getActivity().getApplicationContext(), success, Toast.LENGTH_SHORT, true);
            toast.show();
        }
    }

    /**
     * toast ERROR
     *
     * @param error
     */
    public void showError(String error) {
        if (toast != null) {
            toast.show();
        } else {
            toast = Toasty.error(getActivity().getApplicationContext(), error, Toast.LENGTH_SHORT, true);
            toast.show();
        }
    }
}
