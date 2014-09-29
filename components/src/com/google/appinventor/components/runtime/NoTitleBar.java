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


@DesignerComponent(version = YaVersion.CLOCK_COMPONENT_VERSION,
    description = "" +
    "",
    category = ComponentCategory.BASIC,
    nonVisible = true,
    iconName = "images/notitlebar.png")
@SimpleObject
public final class NoTitleBar extends AndroidNonvisibleComponent
    implements Component, OnStopListener, OnResumeListener, OnDestroyListener,
               Deleteable {

  private boolean onScreen = false;
 
  public NoTitleBar(ComponentContainer container) {
    super(container.$form());

    // Set up listeners
    /*
	form.registerForOnResume(this);
    form.registerForOnStop(this);
    form.registerForOnDestroy(this);
	*/
    if (form instanceof ReplForm) {
      // In REPL, if this component was added to the project after the onResume call occurred,
      // then onScreen would be false, but the REPL app is, in fact, on screen.
      onScreen = true;
    }
  }

  // Only the above constructor should be used in practice.
  public NoTitleBar() {
    super(null);
    // To allow testing
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
