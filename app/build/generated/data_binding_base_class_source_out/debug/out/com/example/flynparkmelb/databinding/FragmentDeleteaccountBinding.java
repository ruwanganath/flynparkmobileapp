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

public final class FragmentDeleteaccountBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton appCompatButtondeleteaccountCancel;

  @NonNull
  public final AppCompatButton appCompatButtondeleteaccountYes;

  @NonNull
  public final TextView textDeleteAccount;

  private FragmentDeleteaccountBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton appCompatButtondeleteaccountCancel,
      @NonNull AppCompatButton appCompatButtondeleteaccountYes,
      @NonNull TextView textDeleteAccount) {
    this.rootView = rootView;
    this.appCompatButtondeleteaccountCancel = appCompatButtondeleteaccountCancel;
    this.appCompatButtondeleteaccountYes = appCompatButtondeleteaccountYes;
    this.textDeleteAccount = textDeleteAccount;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDeleteaccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDeleteaccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_deleteaccount, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDeleteaccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appCompatButtondeleteaccountCancel;
      AppCompatButton appCompatButtondeleteaccountCancel = rootView.findViewById(id);
      if (appCompatButtondeleteaccountCancel == null) {
        break missingId;
      }

      id = R.id.appCompatButtondeleteaccountYes;
      AppCompatButton appCompatButtondeleteaccountYes = rootView.findViewById(id);
      if (appCompatButtondeleteaccountYes == null) {
        break missingId;
      }

      id = R.id.textDeleteAccount;
      TextView textDeleteAccount = rootView.findViewById(id);
      if (textDeleteAccount == null) {
        break missingId;
      }

      return new FragmentDeleteaccountBinding((ConstraintLayout) rootView,
          appCompatButtondeleteaccountCancel, appCompatButtondeleteaccountYes, textDeleteAccount);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
