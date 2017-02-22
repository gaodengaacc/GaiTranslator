package com.lyun.lawyer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lyun.lawyer.R;
import com.lyun.lawyer.databinding.FragmentTranslatorGrabLayoutBinding;
import com.lyun.lawyer.viewmodel.TranslatorMainViewModel;
import com.lyun.library.mvvm.view.fragment.MvvmFragment;

/**
 * @author Gordon
 * @since 2017/1/20
 * do(翻译主页抢单页面)
 */

public class TranslatorMainFragment extends MvvmFragment<FragmentTranslatorGrabLayoutBinding,TranslatorMainViewModel> {
    public TranslatorMainFragment() {
    }
    public static TranslatorMainFragment newInstance() {
        TranslatorMainFragment fragment = new TranslatorMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    protected TranslatorMainViewModel createViewModel() {
        return new TranslatorMainViewModel();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_translator_grab_layout;
    }
}
