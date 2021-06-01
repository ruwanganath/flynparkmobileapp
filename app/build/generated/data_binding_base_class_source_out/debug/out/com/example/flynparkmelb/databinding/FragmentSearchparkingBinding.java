// Generated by view binder compiler. Do not edit!
package com.example.flynparkmelb.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.flynparkmelb.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSearchparkingBinding implements ViewBinding {
  @NonNull
  private final SlidingUpPanelLayout rootView;

  @NonNull
  public final TextView bayId;

  @NonNull
  public final TextView desc1;

  @NonNull
  public final TextView desc2;

  @NonNull
  public final LinearLayout dragView;

  @NonNull
  public final TextView name;

  @NonNull
  public final TextView rate;

  @NonNull
  public final SlidingUpPanelLayout searchParkingFragment;

  @NonNull
  public final TextView tinfo;

  @NonNull
  public final TextView trate;

  @NonNull
  public final TextView type;

  private FragmentSearchparkingBinding(@NonNull SlidingUpPanelLayout rootView,
      @NonNull TextView bayId, @NonNull TextView desc1, @NonNull TextView desc2,
      @NonNull LinearLayout dragView, @NonNull TextView name, @NonNull TextView rate,
      @NonNull SlidingUpPanelLayout searchParkingFragment, @NonNull TextView tinfo,
      @NonNull TextView trate, @NonNull TextView type) {
    this.rootView = rootView;
    this.bayId = bayId;
    this.desc1 = desc1;
    this.desc2 = desc2;
    this.dragView = dragView;
    this.name = name;
    this.rate = rate;
    this.searchParkingFragment = searchParkingFragment;
    this.tinfo = tinfo;
    this.trate = trate;
    this.type = type;
  }

  @Override
  @NonNull
  public SlidingUpPanelLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSearchparkingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSearchparkingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_searchparking, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSearchparkingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bayId;
      TextView bayId = rootView.findViewById(id);
      if (bayId == null) {
        break missingId;
      }

      id = R.id.desc1;
      TextView desc1 = rootView.findViewById(id);
      if (desc1 == null) {
        break missingId;
      }

      id = R.id.desc2;
      TextView desc2 = rootView.findViewById(id);
      if (desc2 == null) {
        break missingId;
      }

      id = R.id.dragView;
      LinearLayout dragView = rootView.findViewById(id);
      if (dragView == null) {
        break missingId;
      }

      id = R.id.name;
      TextView name = rootView.findViewById(id);
      if (name == null) {
        break missingId;
      }

      id = R.id.rate;
      TextView rate = rootView.findViewById(id);
      if (rate == null) {
        break missingId;
      }

      SlidingUpPanelLayout searchParkingFragment = (SlidingUpPanelLayout) rootView;

      id = R.id.tinfo;
      TextView tinfo = rootView.findViewById(id);
      if (tinfo == null) {
        break missingId;
      }

      id = R.id.trate;
      TextView trate = rootView.findViewById(id);
      if (trate == null) {
        break missingId;
      }

      id = R.id.type;
      TextView type = rootView.findViewById(id);
      if (type == null) {
        break missingId;
      }

      return new FragmentSearchparkingBinding((SlidingUpPanelLayout) rootView, bayId, desc1, desc2,
          dragView, name, rate, searchParkingFragment, tinfo, trate, type);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}