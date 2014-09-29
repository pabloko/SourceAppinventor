// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the MIT License https://raw.github.com/mit-cml/app-inventor/master/mitlicense.txt

package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.TimerInternal;

import com.google.android.gms.ads.*;


@DesignerComponent(version = YaVersion.CLOCK_COMPONENT_VERSION,
    description = "" +
    "",
    category = ComponentCategory.BASIC,
    nonVisible = true,
    iconName = "images/admob.png")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
@UsesLibraries(libraries = "admob.jar")
public final class AdMobIntersitial extends AndroidNonvisibleComponent
    implements Component, OnStopListener, OnResumeListener, OnDestroyListener,
               Deleteable {

  private boolean onScreen = false;
  private static InterstitialAd interstitial;

  /**
   * Creates a new admob intersitial component.
   *
   * @param container ignored (because this is a non-visible component)
   */
  public AdMobIntersitial(ComponentContainer container) {
    super(container.$form());

    // Set up listeners
    form.registerForOnResume(this);
    form.registerForOnStop(this);
    form.registerForOnDestroy(this);

    if (form instanceof ReplForm) {
      // In REPL, if this component was added to the project after the onResume call occurred,
      // then onScreen would be false, but the REPL app is, in fact, on screen.
      onScreen = true;
    }
  }

  // Only the above constructor should be used in practice.
  public AdMobIntersitial() {
    super(null);
    // To allow testing
  }

  public String gid="";
  
  @DesignerProperty(
      editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
      defaultValue = "")
  @SimpleProperty
  public void AdUnitID(String adunitid) {
    gid=adunitid;
	//aqui parece que se crea la fiesta
	// Create the interstitial.
    interstitial = new InterstitialAd(form.$context());
	interstitial.setAdUnitId(adunitid);
	AdRequest adRequest = new AdRequest.Builder().build();

    // Begin loading your interstitial.
    interstitial.loadAd(adRequest);
  }

  

  @SimpleFunction(description = "Show intersitial ad")
  public static void ShowIntersitialAd() {
    if (interstitial.isLoaded()) {
      interstitial.show();
    }
  }
  
  @SimpleFunction(description = "Check if a Intersitial AD is loaded")
  public static boolean IsIntersitialAdLoaded() {
    return interstitial.isLoaded();
  }


  @Override
  public void onStop() {
    onScreen = false;
  }

  @Override
  public void onResume() {
    onScreen = true;
  }

  @Override
  public void onDestroy() {

  }

  @Override
  public void onDelete() {

  }
}
