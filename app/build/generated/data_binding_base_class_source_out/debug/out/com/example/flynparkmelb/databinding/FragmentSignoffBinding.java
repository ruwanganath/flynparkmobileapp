// Generated by view binder compiler. Do not edit!
package com.example.flynparkmelb.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.flynparkmelb.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSignoffBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton appCompatButtonsignoffCancel;

  @NonNull
  public final AppCompatButton appCompatButtonsignoffYes;

  @NonNull
  public final TextView textSignoff;

  private FragmentSignoffBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton appCompatButtonsignoffCancel,
      @NonNull AppCompatButton appCompatButtonsignoffYes, @NonNull TextView textSignoff) {
    this.rootView = rootView;
    this.appCompatButtonsignoffCancel = appCompatButtonsignoffCancel;
    this.appCompatButtonsignoffYes = appCompatButtonsignoffYes;
    this.textSignoff = textSignoff;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSignoffBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSignoffBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_signoff, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSignoffBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appCompatButtonsignoffCancel;
      AppCompatButton appCompatButtonsignoffCancel = rootView.findViewById(id);
      if (appCompatButtonsignoffCancel == null) {
        break missingId;
      }

      id = R.id.appCompatButtonsignoffYes;
      AppCompatButton appCompatButtonsignoffYes = rootView.findViewById(id);
      if (appCompatButtonsignoffYes == null) {
        break missingId;
      }

      id = R.id.textSignoff;
      TextView textSignoff = rootView.findViewById(id);
      if (textSignoff == null) {
        break missingId;
      }

      return new FragmentSignoffBinding((ConstraintLayout) rootView, appCompatButtonsignoffCancel,
          appCompatButtonsignoffYes, textSignoff);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
