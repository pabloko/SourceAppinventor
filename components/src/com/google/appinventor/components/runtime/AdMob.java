// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the MIT License https://raw.github.com/mit-cml/app-inventor/master/mitlicense.txt

package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.annotations.UsesLibraries;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
//import com.google.ads.*;
//HUPDATE
import com.google.android.gms.ads.*;

import java.io.IOException;
import android.widget.LinearLayout;

/**
 * Component for displaying images and animations.
 *
 */
@DesignerComponent(version = YaVersion.IMAGE_COMPONENT_VERSION,
    category = ComponentCategory.BASIC,
    description = "Component for displaying images.  The picture to display, " +
    "and other aspects of the Image's appearance, can be specified in the " +
    "Designer or in the Blocks Editor.")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
@UsesLibraries(libraries = "admob.jar")
public final class AdMob extends AndroidViewComponent {
  
  private final LinearLayout superview;
  private AdView view;
  
  
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING, defaultValue = "")
  @SimpleProperty()
  public void ID(String ID) { 
  //HUPDATE
  //     view = new AdView(container.$context(), AdSize.BANNER, ID);
		view = new AdView(container.$context());
		//view.setAdSize(AdSize.BANNER);
		view.setAdUnitId(ID);
  
       //superview.addView(view);
       //view.loadAd(new AdRequest());
  }
  
 @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING, defaultValue = "")
  @SimpleProperty()
  public void Size(String SIZE) { 
		view.setAdSize(AdSize.BANNER);
		//DISPLAY NAW
       superview.addView(view);
       //view.loadAd(new AdRequest());
       AdRequest adRequest = new AdRequest.Builder().build();

   // Start loading the ad in the background.
       view.loadAd(adRequest);
  }  
  
 /**
   * Creates a new AdMob component.
   *
   * @param container  container, component will be placed in
   */
  public AdMob(ComponentContainer container) {
    super(container);
    //view = new AdView(container.$context(), AdSize.BANNER, "a195766848864512");
	superview = new LinearLayout(container.$context());
    // Adds the component to its designated container
    container.$add(this);
	//view.loadAd(new AdRequest());
  }

  
  @Override
  public View getView() {
    return superview;
  }
  
}
