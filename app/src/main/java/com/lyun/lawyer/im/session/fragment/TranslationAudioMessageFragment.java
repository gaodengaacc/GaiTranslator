package com.lyun.lawyer.im.session.fragment;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyun.lawyer.R;
import com.lyun.lawyer.databinding.FragmentTranslationAudioMessageBinding;
import com.lyun.lawyer.viewmodel.TranslationAudioMessageViewModel;
import com.lyun.utils.GlideUtils;
import com.netease.nim.uikit.common.fragment.TFragment;
import com.netease.nim.uikit.common.ui.imageview.CircleImageView;

/**
 * Created by ZHAOWEIWEI on 2017/3/3.
 */

public class TranslationAudioMessageFragment extends TFragment {

    private TranslationAudioMessageViewModel mViewModel;
    private View rootView;

    private String userName;
    private String targetLanguage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_translation_audio_message, container, false);
        ViewDataBinding viewDataBinding = DataBindingUtil.bind(rootView);
        FragmentTranslationAudioMessageBinding translationAudioMessageBinding = (FragmentTranslationAudioMessageBinding) viewDataBinding;
        mViewModel = new TranslationAudioMessageViewModel().setPropertyChangeListener(getActivity());
        translationAudioMessageBinding.setMvvm(mViewModel);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.targetLanguage.set(targetLanguage);
        mViewModel.userName.set(userName);
    }

    public void onServiceTimeChanged(String time) {
        mViewModel.translationTime.set(time);
    }

    public void setUserName(String name) {
        this.userName = name;
        if (mViewModel != null) {
            mViewModel.userName.set(name);
        }
    }

    public void setAvatar(String avatar) {
        if (mViewModel != null) {
            mViewModel.translatorAvatar.set(avatar);
        }
    }

    public void setTranslatorTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
        if (mViewModel != null) {
            mViewModel.targetLanguage.set(targetLanguage);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(CircleImageView imageView, String url) {
        Context context = imageView.getContext();
        GlideUtils.showImage(imageView, url);
    }
}
