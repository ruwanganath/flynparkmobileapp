// Generated by view binder compiler. Do not edit!
package com.example.flynparkmelb.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.flynparkmelb.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySplashScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imageViewLogo;

  @NonNull
  public final ConstraintLayout relativeLayout;

  @NonNull
  public final TextView textViewTextMotto;

  private ActivitySplashScreenBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageView imageViewLogo, @NonNull ConstraintLayout relativeLayout,
      @NonNull TextView textViewTextMotto) {
    this.rootView = rootView;
    this.imageViewLogo = imageViewLogo;
    this.relativeLayout = relativeLayout;
    this.textViewTextMotto = textViewTextMotto;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySplashScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySplashScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_splash_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySplashScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageViewLogo;
      ImageView imageViewLogo = rootView.findViewById(id);
      if (imageViewLogo == null) {
        break missingId;
      }

      ConstraintLayout relativeLayout = (ConstraintLayout) rootView;

      id = R.id.textViewTextMotto;
      TextView textViewTextMotto = rootView.findViewById(id);
      if (textViewTextMotto == null) {
        break missingId;
      }

      return new ActivitySplashScreenBinding((ConstraintLayout) rootView, imageViewLogo,
          relativeLayout, textViewTextMotto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}